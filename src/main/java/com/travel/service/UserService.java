package com.travel.service;

import com.travel.mapper.UserMapper;
import com.travel.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User checkUser(User user){
        return userMapper.selectOne(user);
    }

    public User queryUserByName(String name){
        User user=new User();
        user.setUsername(name);
        return userMapper.selectOne(user);
    }

    public void register(User user){
        user.setUserId(null);
        user.setUserType(null);
        userMapper.insertSelective(user);
    }
}
