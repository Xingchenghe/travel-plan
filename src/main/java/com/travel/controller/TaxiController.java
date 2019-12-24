package com.travel.controller;

import com.travel.bo.TaxiBo;
import com.travel.pojo.City;
import com.travel.pojo.Driver;
import com.travel.pojo.Taxi;
import com.travel.service.FlightService;
import com.travel.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class TaxiController {
    @Autowired
    TaxiService taxiService;
    @Autowired
    FlightService flightService;
    @GetMapping("taxi/all")
    public ResponseEntity<List<TaxiBo>> queryAllTaxiBos(){
        List<TaxiBo> taxiBos = taxiService.queryAllTaxiBos();
        taxiBos.removeIf(taxiBo -> taxiBo.getOrdered() != 0);
        if (CollectionUtils.isEmpty(taxiBos)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taxiBos);
    }
    @GetMapping("taxi/search/{city}")
    public ResponseEntity<List<TaxiBo>> queryTaxiBosByCityName(@PathVariable("city")String city){
        List<TaxiBo> taxiBos=taxiService.queryTaxiBosByCityName(city);
        if (CollectionUtils.isEmpty(taxiBos))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(taxiBos);
    }
    @PostMapping("taxi/update")
    @Transactional
    public ResponseEntity<Void> updateTaxi(TaxiBo taxiBo, @RequestParam("cityName")String cityName){
        Long taxiId = taxiBo.getTaxiId();
        Long driverNo = taxiBo.getDriverNo();
        Taxi taxi = taxiService.queryTaxiById(taxiId);
        Driver driver = taxiService.queryDriverByDriverNo(driverNo);
        if (!StringUtils.isEmpty(cityName)){
            City city = flightService.queryCityByName(cityName);
            if (city==null)
                return ResponseEntity.badRequest().build();
            taxi.setCityNo(city.getCityNo());
        }else {
            taxi.setCityNo(taxiBo.getCityNo());
        }
        String driverName = taxiBo.getDriverName();
        String driverPhone = taxiBo.getDriverPhone();
        String carNo = taxiBo.getCarNo();
        String carModel = taxiBo.getCarModel();
        if (taxi==null||driver==null||StringUtils.isEmpty(driverName)||StringUtils.isEmpty(driverPhone)||StringUtils.isEmpty(carNo)||StringUtils.isEmpty(carModel))
            return ResponseEntity.badRequest().build();
        driver.setDriverName(driverName);
        driver.setDriverPhone(driverPhone);
        taxi.setCarModel(carModel);
        taxi.setCarNo(carNo);
        taxiService.updateTaxi(taxi);
        taxiService.updateDriver(driver);
        return ResponseEntity.ok().build();
    }
    @PostMapping("taxi/add")
    @Transactional
    public ResponseEntity<Void> addTaxi(TaxiBo taxiBo,@RequestParam("cityName")String cityName){
        String driverName = taxiBo.getDriverName();
        String driverPhone = taxiBo.getDriverPhone();
        String carModel = taxiBo.getCarModel();
        String carNo = taxiBo.getCarNo();
        if (StringUtils.isEmpty(driverName)||StringUtils.isEmpty(driverPhone)||StringUtils.isEmpty(carModel)||StringUtils.isEmpty(carNo)||StringUtils.isEmpty(cityName))
            return ResponseEntity.badRequest().build();
        taxiBo.setOrdered(0L);
        City city = flightService.queryCityByName(cityName);
        if (city==null)
            return ResponseEntity.badRequest().build();
        taxiBo.setCityNo(city.getCityNo());
        Driver driver = taxiService.queryDriverByName(driverName);
        if (driver==null){
            driver=new Driver();
            driver.setDriverName(driverName);
            driver.setDriverPhone(driverPhone);
            taxiService.addDriver(driver);
            driver=taxiService.queryDriverByName(driverName);
        }
        taxiBo.setDriverNo(driver.getDriverNo());
        taxiBo.setCarNo(carNo);
        taxiBo.setCarModel(carModel);
        taxiService.addTaxi(taxiBo);
        return ResponseEntity.ok().build();
    }
}
