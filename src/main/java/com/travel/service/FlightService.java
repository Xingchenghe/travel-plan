package com.travel.service;

import com.travel.bo.FlightBo;
import com.travel.mapper.AirlineMapper;
import com.travel.mapper.AirportMapper;
import com.travel.mapper.CityMapper;
import com.travel.mapper.FlightMapper;
import com.travel.pojo.Airline;
import com.travel.pojo.Airport;
import com.travel.pojo.City;
import com.travel.pojo.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    AirportMapper airportMapper;
    @Autowired
    AirlineMapper airlineMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    FlightMapper flightMapper;

    public Airport queryAirportById(Long airport_no){
        return this.airportMapper.selectByPrimaryKey(airport_no);
    }

    public Airport queryAirportByName(String name){
        Airport airport=new Airport();
        airport.setAirportName(name);
        return airportMapper.selectOne(airport);
    }

    public List<Airport> queryAllAirports(){
        return this.airportMapper.selectAll();
    }

    public Airline queryAirlineById(Long airline_no){
        return airlineMapper.selectByPrimaryKey(airline_no);
    }

    public Airline queryAirlineByName(String name){
        Airline airline=new Airline();
        airline.setAirlineName(name);
        return this.airlineMapper.selectOne(airline);
    }

    public List<Airline> queryAllAirlines(){
        return airlineMapper.selectAll();
    }

    public City queryCityById(Long city_id){
        return cityMapper.selectByPrimaryKey(city_id);
    }

    public City queryCityByName(String city){
        Example example=new Example(City.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("cityName",city);
        List<City> cities = cityMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cities))
            return cities.get(0);
        return null;
    }

    public Flight queryFlightByFlightNo(String flightNo){
        Example example=new Example(Flight.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("flightNo",flightNo);
        List<Flight> flights = flightMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(flights))
            return flights.get(0);
        return null;
    }

    public int updateFlight(Flight flight){
        return this.flightMapper.updateByPrimaryKey(flight);
    }

    public FlightBo queryFlightBoByFlightId(Long id){
        return this.flightMapper.selectFlightBoByFlightId(id);
    }

    public Flight queryFlightById(Long id){
        Flight flight=new Flight();
        flight.setFlightId(id);
        return this.flightMapper.selectByPrimaryKey(flight);
    }

    public void addFlight(Flight flight){
        this.flightMapper.insert(flight);
    }

    public List<FlightBo> queryFlightByStartAndDest(String start, String dest){
        List<FlightBo> flights = flightMapper.selectFlightsByStartAndDest(start, dest);
        return flights;
    }

    public List<City> queryAllCities(){
        return cityMapper.selectAll();
    }

    public List<Flight> queryAllFlights(){
        return flightMapper.selectAll();
    }

    public void deleteFlightById(Long id) {
        flightMapper.deleteByPrimaryKey(id);
    }
}
