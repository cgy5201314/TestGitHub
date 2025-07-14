package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collecter implements Serializable {
    private int id;
    private String avatar;  //采集的头像
    private String name;    //采集的名字
    private String time;   //采集的时间
    private int area;   //采集的区域id
    public String facetoken;  //采集的token
    private int deleted;
    private int uid;//采集者的信息
    @Override
    public String toString(){
        return "Collection{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", area=" + area +
                ", facetoken='" + facetoken + '\'' +
                ", deleted='" + deleted + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
