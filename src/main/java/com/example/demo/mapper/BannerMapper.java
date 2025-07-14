package com.example.demo.mapper;

import com.example.demo.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("SELECT * FROM banner ORDER BY RAND() LIMIT 3")
    List<Banner> getBanner();
}
