package com.travel.controller;

import com.travel.bo.FlightBo;
import com.travel.pojo.Flight;
import com.travel.pojo.Order;
import com.travel.pojo.User;
import com.travel.service.*;
import com.travel.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    FlightService flightService;
    @Autowired
    TaxiService taxiService;
    @Autowired
    HotelService hotelService;
    @PostMapping("register")
    public ResponseEntity<Void> register(User user){
        User user1 = userService.queryUserByName(user.getUsername());
        if (user1 == null) {
            this.userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("login")
    public ResponseEntity<String> login(User user, HttpServletRequest request,HttpServletResponse response,@CookieValue(value = "token", required = false) String token){
        User user1 = userService.checkUser(user);
        if (user1 == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CookieUtils.writeCookie(response,"username",user.getUsername());
        CookieUtils.writeCookie(response,"password",user.getPassword());
        return ResponseEntity.ok(user1.getUserType().toString());
    }
    @GetMapping("/user/{username}/orders")
    public ResponseEntity<List<Order>> queryOrdersByName(@PathVariable("username")String name){
        User user = userService.queryUserByName(name);
        if (user==null)
            return ResponseEntity.badRequest().build();
        List<Order> orders = orderService.queryOrdersByName(name);
        if (CollectionUtils.isEmpty(orders))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("delete/{type}/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("type")Long type,@PathVariable("id")Long id){
        Order order=new Order();
        order.setItemType(type);
        order.setItemId(id);
        List<Order> orders = orderService.queryOrderByOrder(order);
        if (!CollectionUtils.isEmpty(orders))
            return ResponseEntity.badRequest().build();
        if (type==0){
            flightService.deleteFlightById(id);
        } else if (type == 1) {
            taxiService.deleteTaxiById(id);
        }else if (type==2){
            hotelService.deleteHotelById(id);
        }
        return ResponseEntity.ok().build();
    }
}
