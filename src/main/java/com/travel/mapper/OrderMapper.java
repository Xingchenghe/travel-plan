package com.travel.mapper;

import com.travel.pojo.Order;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    @Select("select order_id,item_type,user_id,create_time,item_id,item_own_spec from tb_order natural join user where username=#{name};")
    List<Order> queryOrdersByName(String name);
}
