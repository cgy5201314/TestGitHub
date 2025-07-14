package com.example.demo.mapper;

import com.example.demo.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {
    List<Area> getAll(int usernum);
}
