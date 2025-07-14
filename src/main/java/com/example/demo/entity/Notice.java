package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    private int id;
    private String title;
    private String content;
    private String time;
    private String noticetu;
    private int deleted;
    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", noticetu='" + noticetu + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
