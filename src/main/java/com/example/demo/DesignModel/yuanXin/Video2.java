package com.example.demo.DesignModel.yuanXin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//实现接口Cloneable
//重写clone方法，变成深度克隆
//调用clone方法克隆新对象
@Data
public class Video2 implements Cloneable{
    private String name;
    private String url;
    @Override
    protected Object clone() throws CloneNotSupportedException{
        Video2 video= (Video2) super.clone();
        video.name = new String(this.name);  // 深度克隆 name 字段
        video.url = new String(this.url);    // 深度克隆 url 字段
        return video;
    }



    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
