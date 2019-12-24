package com.travel.mapper;

import com.travel.bo.FlightBo;
import com.travel.pojo.Flight;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FlightMapper extends Mapper<Flight> {
    @Select("with city_airport as ( select * from airport natural join city)\n" +
            "select flight_no,airline_no,airline_name,start_airport_no,ca1.airport_name 'start_airport_name',arrival_airport_no,ca2.airport_name 'arrival_airport_name',start_date,start_time,arrival_date,arrival_time,price\n" +
            "from flight natural join airline left join city_airport as ca1 on flight.start_airport_no=ca1.airport_no\n" +
            "    left join city_airport as ca2 on flight.arrival_airport_no=ca2.airport_no\n" +
            "where ca1.city_name=#{start} and ca2.city_name=#{dest};")
    List<FlightBo> selectFlightsByStartAndDest(String start, String dest);

    @Select("with city_airport as ( select * from airport natural join city)\n" +
            "select flight_no,airline_no,airline_name,start_airport_no,ca1.airport_name 'start_airport_name',arrival_airport_no,ca2.airport_name 'arrival_airport_name',start_date,start_time,arrival_date,arrival_time,price\n" +
            "from flight natural join airline left join city_airport as ca1 on flight.start_airport_no=ca1.airport_no\n" +
            "    left join city_airport as ca2 on flight.arrival_airport_no=ca2.airport_no where flight_id=#{id};")
    FlightBo selectFlightBoByFlightId(Long id);
}
