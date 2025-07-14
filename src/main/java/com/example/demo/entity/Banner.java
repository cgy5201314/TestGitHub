package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
  private  int id;
  private  String luntu;

    @Override
    public String toString() {
        return "banner{" +
                "id=" + id +
                ", luntu='" + luntu + '\'' +
                '}';
    }
}
