package com.travel.service;

import com.travel.mapper.OrderMapper;
import com.travel.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void insertOrder(Order order){
        orderMapper.insert(order);
    }

    public List<Order> queryOrdersByName(String name){
        return this.orderMapper.queryOrdersByName(name);
    }

    public Order queryOrderById(Long id){
        return orderMapper.selectByPrimaryKey(id);
    }

    public void deleteOrderById(Long id){
        orderMapper.deleteByPrimaryKey(id);
    }

    public List<Order> queryOrderByOrder(Order order){
        return orderMapper.select(order);
    }
}
