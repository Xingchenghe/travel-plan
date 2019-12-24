package com.travel.controller;

import com.travel.bo.HotelBo;
import com.travel.bo.HotelBrandBo;
import com.travel.pojo.City;
import com.travel.pojo.Hotel;
import com.travel.pojo.HotelBrand;
import com.travel.service.FlightService;
import com.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin
@Controller
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    FlightService flightService;
    @GetMapping("hotel/all")
    public ResponseEntity<List<HotelBo>> queryAllHotelBos(){
        List<HotelBo> hotelBos = hotelService.queryAllHotelBos();
        hotelBos.removeIf(hotelBo -> hotelBo.getHotelRoomsLeft()<=0);
        if (CollectionUtils.isEmpty(hotelBos)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotelBos);
    }
    @GetMapping("hotelBrands")
    public ResponseEntity<List<HotelBrandBo>> queryAllHotelBrandBos(){
        List<HotelBrandBo> hotelBrandBos = hotelService.queryAllHotelBrandBos();
        if (CollectionUtils.isEmpty(hotelBrandBos)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotelBrandBos);
    }

    @PostMapping("hotel/search")
    public ResponseEntity<List<HotelBo>> queryHotelBosByCityNameAndBrandName(@RequestParam("cityName")String cityName, @RequestParam("brandName")String brandName){
        List<HotelBo> hotelBos = hotelService.queryHotelBosByCityNameANdBrandName(cityName, brandName);
        if (CollectionUtils.isEmpty(hotelBos)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotelBos);
    }

    @PostMapping("hotel/update")
    public ResponseEntity<Void> updateHotel(Hotel hotel){
        Hotel oldHotel = this.hotelService.queryHotelById(hotel.getHotelId());
        String hotelName = hotel.getHotelName();
        String hotelAddr = hotel.getHotelAddr();
        String hotelPhone = hotel.getHotelPhone();
        Long hotelRoomsLeft = hotel.getHotelRoomsLeft();
        Long hotelPrice = hotel.getHotelPrice();
        if (StringUtils.isEmpty(hotelName)||StringUtils.isEmpty(hotelAddr)||StringUtils.isEmpty(hotelPhone)||hotelRoomsLeft==null||hotelPrice==null){
            return ResponseEntity.badRequest().build();
        }
        oldHotel.setHotelName(hotelName);
        oldHotel.setHotelAddr(hotelAddr);
        oldHotel.setHotelPhone(hotelPhone);
        oldHotel.setHotelRoomsLeft(hotelRoomsLeft);
        oldHotel.setHotelPrice(hotelPrice);
        hotelService.updateHotel(oldHotel);
        return ResponseEntity.ok().build();
    }

    @PostMapping("hotel/add")
    public ResponseEntity<Void> addHotel(HotelBo hotelBo){
        String brandName = hotelBo.getBrandName();
        String cityName = hotelBo.getCityName();
        String hotelName = hotelBo.getHotelName();
        String hotelAddr = hotelBo.getHotelAddr();
        Long hotelRoomsLeft = hotelBo.getHotelRoomsLeft();
        String hotelPhone = hotelBo.getHotelPhone();
        Long hotelPrice = hotelBo.getHotelPrice();
        if (StringUtils.isEmpty(brandName)||StringUtils.isEmpty(cityName)||StringUtils.isEmpty(hotelName)||StringUtils.isEmpty(hotelAddr)
            ||hotelRoomsLeft==null||StringUtils.isEmpty(hotelPhone)||hotelPrice==null)
            return ResponseEntity.badRequest().build();
        HotelBrand hotelBrand = hotelService.queryHotelBrandByName(brandName);
        City city = flightService.queryCityByName(cityName);
        if (hotelBrand==null||city==null)
            return ResponseEntity.badRequest().build();
        hotelBo.setCityNo(city.getCityNo());
        hotelBo.setBrandNo(hotelBrand.getBrandNo());
        hotelService.addHotel(hotelBo);
        return ResponseEntity.ok().build();
    }
}
