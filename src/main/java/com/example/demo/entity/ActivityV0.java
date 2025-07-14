package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityV0 implements Serializable {
    private Integer activityId;
    private String activity;
    private String content;
    private String time;
    private String avatar;
    private Integer pay;
    private Integer paytype;
    private Integer publishman;
    private Integer doman;
    private Integer domans;
    private Integer status; // 来自 info 表或默认 0
    private Integer usernum; // 当前用户ID

    @Override
    public String toString() {
        return "ActivityV0{" +
                "activityId=" + activityId +
                ", activity='" + activity + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", avatar='" + avatar + '\'' +
                ", pay=" + pay +
                ", paytype=" + paytype +
                ", publishman=" + publishman +
                ", doman=" + doman +
                ", domans=" + domans +
                ", status=" + status +
                ", usernum=" + usernum +
                '}';
    }
}

