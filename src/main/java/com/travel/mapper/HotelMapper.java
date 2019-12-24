package com.travel.mapper;

import com.travel.bo.HotelBo;
import com.travel.pojo.Hotel;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HotelMapper extends Mapper<Hotel> {
    @Select("select * from hotel natural join hotel_brand natural join city;")
    List<HotelBo> queryAllHotelBos();
    @Select("select * from hotel natural join hotel_brand natural join city where hotel_id=#{id};")
    HotelBo queryHotelBoById(Long id);
}
