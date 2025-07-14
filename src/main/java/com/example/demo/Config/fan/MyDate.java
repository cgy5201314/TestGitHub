package com.example.demo.Config.fan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyDate implements Comparable<MyDate>{
    private int year;
    private int month;
    private int day;

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(MyDate o) {
        if(this.year != o.year){
            return Integer.compare(this.year,o.year);
        }
        if(this.month != o.month){
            return Integer.compare(this.month,o.month);
        }
        return Integer.compare(this.day,o.day);
    }
}
