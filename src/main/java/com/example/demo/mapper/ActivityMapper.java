package com.example.demo.mapper;

import com.example.demo.entity.Activity;
import com.example.demo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    int deleteById(int id);
    List<Activity> getAllActivity();
    //活动完成领取pay
    Activity selectById(int id);
    //活动信息修改，
    int update(@Param("aid") int aid,@Param("doman") int doman);
}
