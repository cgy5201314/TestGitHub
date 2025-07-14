package com.example.demo.mapper;

import com.example.demo.entity.Activityinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ActivityinfoMapper {
    Activityinfo selectById(int id);
    int update(@Param("aid") int aid, @Param("uid") int uid);
    int insert(@Param("aid") int aid, @Param("uid") int uid);
    Activityinfo selectInfo(@Param("aid") int aid, @Param("uid") int uid);
}
