package com.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.bo.FlightBo;
import com.travel.pojo.*;
import com.travel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@CrossOrigin
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    FlightService flightService;
    @Autowired
    TaxiService taxiService;
    @Autowired
    HotelService hotelService;
    @PostMapping("order")
    @Transactional
    public ResponseEntity<Void> saveOrder(Order order, @RequestParam("username")String username,@RequestParam("item_info")String item_info){
        User user = userService.queryUserByName(username);
        if (user==null){
            return ResponseEntity.badRequest().build();
        }
        order.setUserId(user.getUserId());
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));

        if (order.getItemType()==0){
            JSONObject jsonObject=JSONObject.parseObject(item_info);
            Map<String, Object> map = new LinkedHashMap<>(jsonObject);
            Flight flight = flightService.queryFlightByFlightNo((String) map.get("flightNo"));
            if (flight==null)
                return ResponseEntity.badRequest().build();
            FlightBo flightBo = flightService.queryFlightBoByFlightId(flight.getFlightId());
            map.put("flightNo",flightBo.getFlightNo());
            map.put("airlineName",flightBo.getAirlineName());
            map.put("startAirportName",flightBo.getStartAirportName());
            map.put("arrivalAirportName",flightBo.getArrivalAirportName());
            JSONObject jsonObject1 = new JSONObject(map);
            order.setItemId(flight.getFlightId());
            order.setItemOwnSpec(jsonObject1.toString());
            orderService.insertOrder(order);
            return ResponseEntity.ok().build();
        }else if (order.getItemType()==1){
            JSONObject jsonObject=JSONObject.parseObject(item_info);
            Map<String, Object> map = new HashMap<>(jsonObject);
            Taxi taxi = taxiService.queryTaxiByCarNo((String) map.get("carNo"));
            if (taxi==null||taxi.getOrdered()==1)
                return ResponseEntity.badRequest().build();
            order.setItemId(taxi.getTaxiId());
            order.setItemOwnSpec(item_info);
            taxi.setOrdered(1L);
            taxiService.updateTaxi(taxi);
            orderService.insertOrder(order);
            return ResponseEntity.ok().build();
        }else if (order.getItemType()==2){
            JSONObject jsonObject=JSONObject.parseObject(item_info);
            Map<String, Object> map = new HashMap<>(jsonObject);
            Hotel hotel=hotelService.queryHotelByName((String) map.get("hotelName"));
            if (hotel==null||hotel.getHotelRoomsLeft()<=0)
                return ResponseEntity.badRequest().build();
            order.setItemId(hotel.getHotelId());
            order.setItemOwnSpec(item_info);
            hotel.setHotelRoomsLeft(hotel.getHotelRoomsLeft()-1);
            hotelService.updateHotel(hotel);
            orderService.insertOrder(order);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("unsubscribe/{orderId}")
    @Transactional
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId")Long orderId){
        Order order = orderService.queryOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        Long itemId = order.getItemId();
        Long itemType = order.getItemType();
        if (itemType==1){
            Taxi taxi = taxiService.queryTaxiById(itemId);
            taxi.setOrdered(0L);
            taxiService.updateTaxi(taxi);
        }else if (itemType==2){
            Hotel hotel = hotelService.queryHotelById(itemId);
            Long hotelRoomsLeft = hotel.getHotelRoomsLeft();
            hotel.setHotelRoomsLeft(++hotelRoomsLeft);
            hotelService.updateHotel(hotel);
        }
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
