package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
    private int id;
    private String activity;
    private String content;
    private String time;
    private String avatar;
    private int deleted;
    private int paytype;
    private int pay;
    private int publishman;  //发布人id
    private int doman;//
    private int domans;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deleted=" + deleted +
                ", pay='" + pay + '\'' +
                ", paytype=" + paytype +
                ", publishman='" + publishman + '\'' +
                ", doman=" + doman +
                ", domans=" + domans +
                '}';
    }
}
