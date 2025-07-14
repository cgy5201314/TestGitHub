package com.example.demo.mapper;

import com.example.demo.entity.Collecter;
import com.example.demo.entity.CollecterWithArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CollecterMapper {
    List<Collecter> getAll();//获取某人所有的信息
    int deleteById(int id);
    int insert(Collecter collecter);
    Collecter getById(int id);


}
