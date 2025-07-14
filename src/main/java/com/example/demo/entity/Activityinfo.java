package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activityinfo {
    private int id;
    private int activitynum;
    private int usernum;
    private int status;

    @Override
    public String toString() {
        return "Activityinfo{" +
                "id=" + id +
                ", activitynum=" + activitynum +
                ", usernum=" + usernum +
                ", status=" + status +
                '}';
    }
}
