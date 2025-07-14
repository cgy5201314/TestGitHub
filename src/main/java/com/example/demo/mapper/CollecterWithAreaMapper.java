package com.example.demo.mapper;

import com.example.demo.entity.Collecter;
import com.example.demo.entity.CollecterWithArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollecterWithAreaMapper {
    List<CollecterWithArea> getAll(int id);
    List<CollecterWithArea> getDay(String day);//获取指定天数的
    List<CollecterWithArea> getToday();//获取今日的
}
