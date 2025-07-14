package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area implements Serializable {
    private int id;
    private String aname;
    private String desc;
    private String usernum;

    @Override
    public String toString() {
        return "area{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                ", desc='" + desc + '\'' +
                ", usernum='" + usernum + '\'' +
                '}';
    }
}
