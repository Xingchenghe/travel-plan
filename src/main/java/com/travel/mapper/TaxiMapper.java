package com.travel.mapper;

import com.travel.bo.TaxiBo;
import com.travel.pojo.Taxi;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaxiMapper extends Mapper<Taxi> {
    @Select("select * from taxi natural join driver;")
    List<TaxiBo> queryAllTaxiBos();

    @Select("select car_no,driver_no,taxi_id,city_no,car_model,driver_name,driver_phone from taxi natural join driver natural join city where city_name=#{name};")
    List<TaxiBo> queryTaxiBosByCityName(String name);

    @Select("select * from taxi where car_no=#{carNo};")
    Taxi queryTaxiByCarNo(String carNo);
}
