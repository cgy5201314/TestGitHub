package com.example.demo.DesignModel.yuanXin;

import lombok.Data;

//实现接口Cloneable
//重写clone方法，（浅克隆：都指向同一个数据，不会新增一份数据）
//调用clone方法克隆新对象
@Data
public class Video implements Cloneable{
    private String name;
    private String url;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
