package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper{
     User getUserById(int id);
     User getUserByPhone(String phone);
     int insert(User user);
     int updateById(User user);
     int updatePay(@Param("uid") int uid,@Param("num") int num);
}
