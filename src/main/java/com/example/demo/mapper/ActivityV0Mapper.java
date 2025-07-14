package com.example.demo.mapper;

import com.example.demo.entity.ActivityV0;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityV0Mapper {
    List<ActivityV0> getActivityV0Mapper(int  id);
}
