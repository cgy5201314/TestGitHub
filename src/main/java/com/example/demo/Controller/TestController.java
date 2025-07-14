package com.example.demo.Controller;

import com.example.demo.entity.Banner;
import com.example.demo.entity.Notice;
import com.example.demo.entity.User;
import com.example.demo.mapper.BannerMapper;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private BannerMapper bannerMapper;
    @GetMapping("/hello")
    public String hello() throws  Exception{
        System.out.println(dataSource.getConnection());
        return "hello world";
    }
    @GetMapping("/hello2")
    public User hello2() {
       User user=userMapper.getUserById(1);
        return user;
    }
    @GetMapping("/hello3")
    public Notice hello3() {
//        System.out.println(noticeMapper.getNotice());
        return  noticeMapper.getNotice();
    }
    @GetMapping("/banner")
    public List<Banner> hello4() {
        System.out.println(bannerMapper.getBanner());
        return  bannerMapper.getBanner();
    }
    @GetMapping("/all1")
    public List hello5() {
        List list= bannerMapper.getBanner();
        list.add(noticeMapper.getNotice());
//        System.out.println(list.subList(0, 3));//截取前三个轮播图数据
//        System.out.println(list.subList(list.size()-1, list.size() ));//截取最后一个数据
        return  list;
    }
}
