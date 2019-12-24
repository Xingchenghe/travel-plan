package com.travel.controller;

import com.travel.bo.CityBo;
import com.travel.bo.FlightBo;
import com.travel.pojo.Airline;
import com.travel.pojo.Airport;
import com.travel.pojo.City;
import com.travel.pojo.Flight;
import com.travel.service.FlightService;
import com.travel.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("flight/{airportNo}")
    public ResponseEntity<Airport> queryFlightById(@PathVariable("airportNo") Long airportNo){
        Airport airport = this.flightService.queryAirportById(airportNo);
        if (airport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airport);
    }
    @GetMapping("airline/{airlineNo}")
    public ResponseEntity<Airline> queryAirlineById(@PathVariable("airlineNo") Long airlineNo){
        Airline airline = this.flightService.queryAirlineById(airlineNo);
        if (airline == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airline);
    }
    @GetMapping("cities")
    public ResponseEntity<List<CityBo>> queryAllCities(){
        List<CityBo> cityBos=new ArrayList<>();
        List<City> cities = this.flightService.queryAllCities();
        cities.forEach(city -> {
            CityBo cityBo=new CityBo();
            cityBo.setValue(city.getCityName());
            cityBo.setCityNo(city.getCityNo());
            cityBo.setCityName(city.getCityName());
            cityBos.add(cityBo);
        });
        if (CollectionUtils.isEmpty(cityBos)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityBos);
    }
    @GetMapping("flight/search/{start}/{dest}")
    public ResponseEntity<List<FlightBo>> queryFlightBoByStartAndDest(@PathVariable("start")String start,@PathVariable("dest")String dest){
        List<FlightBo> flightBos = flightService.queryFlightByStartAndDest(start, dest);
        if (CollectionUtils.isEmpty(flightBos)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightBos);
    }

    @GetMapping("flight/all")
    public ResponseEntity<List<FlightBo>> queryAllFlights(){
        List<FlightBo> flightBos=new ArrayList<>();
        //查询所有Flight对象
        List<Flight> flights = flightService.queryAllFlights();
        //读取航司和机场数据
        List<Airport> airports = flightService.queryAllAirports();
        List<Airline> airlines=flightService.queryAllAirlines();
        HashMap<Long,String> airportMap=new HashMap();
        HashMap<Long,String> airlineMap=new HashMap();
        airports.forEach(airport -> {
            airportMap.put(airport.getAirportNo(),airport.getAirportName());
        });
        airlines.forEach(airline -> {
            airlineMap.put(airline.getAirlineNo(),airline.getAirlineName());
        });
        //包装对象为FlightBo
        flights.forEach(flight -> {
            FlightBo flightBo=new FlightBo();
            flightBo.setAirlineNo(flight.getAirlineNo());
            flightBo.setStartAirportNo(flight.getStartAirportNo());
            flightBo.setArrivalAirportNo(flight.getArrivalAirportNo());
            flightBo.setFlightNo(flight.getFlightNo());
            flightBo.setFlightId(flight.getFlightId());
            flightBo.setAirlineName(airlineMap.get(flight.getAirlineNo()));
            flightBo.setStartTime(flight.getStartTime());
            flightBo.setArrivalTime(flight.getArrivalTime());
            flightBo.setStartAirportName(airportMap.get(flight.getStartAirportNo()));
            flightBo.setArrivalAirportName(airportMap.get(flight.getArrivalAirportNo()));
            flightBo.setPrice(flight.getPrice());
            flightBos.add(flightBo);
        });
        if (CollectionUtils.isEmpty(flightBos)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightBos);
    }

    @PostMapping("flight/update")
    public ResponseEntity<Void> updateFlight(FlightBo flightBo){
        Flight flight1 = this.flightService.queryFlightById(flightBo.getFlightId());
        Airport startAirport = this.flightService.queryAirportByName(flightBo.getStartAirportName());
        Airport destAirport = this.flightService.queryAirportByName(flightBo.getArrivalAirportName());
        Airline airline = this.flightService.queryAirlineByName(flightBo.getAirlineName());
        if (flight1==null||startAirport==null||destAirport==null||airline==null){
            return ResponseEntity.badRequest().build();
        }
        flightBo.setStartAirportNo(startAirport.getAirportNo());
        flightBo.setArrivalAirportNo(destAirport.getAirportNo());
        flightBo.setAirlineNo(airline.getAirlineNo());
        if (flightService.updateFlight(flightBo)>0){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("flight/add")
    public ResponseEntity<Void> addFlight(FlightBo flightBo){
        Airport startAirport = this.flightService.queryAirportByName(flightBo.getStartAirportName());
        Airport arrivalAirport = this.flightService.queryAirportByName(flightBo.getArrivalAirportName());
        Airline airline = this.flightService.queryAirlineByName(flightBo.getAirlineName());
        String flightNo = flightBo.getFlightNo();
        Long price = flightBo.getPrice();
        if (startAirport==null||arrivalAirport==null||airline==null|| StringUtils.isEmpty(flightBo)||StringUtils.isEmpty(flightNo)||price==null)
            return ResponseEntity.badRequest().build();
        flightBo.setStartAirportNo(startAirport.getAirportNo());
        flightBo.setArrivalAirportNo(arrivalAirport.getAirportNo());
        flightBo.setAirlineNo(airline.getAirlineNo());
        this.flightService.addFlight(flightBo);
        return ResponseEntity.ok().build();
    }
}
