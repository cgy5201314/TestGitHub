package com.example.demo.mapper;

import com.example.demo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
//    @Select("SELECT * FROM notice ORDER BY RAND() LIMIT 1")
    Notice getNotice();
    List<Notice> getAllNotice();
    int deleteById(int id);
}
