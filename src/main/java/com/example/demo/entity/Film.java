package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film implements Serializable {
    private String id;
    private String title;
    private String content;
    private String img;

    private int deleted;
}
