package com.example.demo.entity;

import lombok.Data;

@Data
public class CollecterWithArea {
    private int id;  //采集的id
    private String avatar;  //采集的头像
    private String name;    //采集的名字
    private String time;   //采集的时间
    private int area;   //采集的区域id
    public String facetoken;  //采集的token
    private int deleted;
    private int uid;  //采集人的id

    private String aname;  //区域名
    private String desc;  //区域的描述
    private String usernum; //用户
}
