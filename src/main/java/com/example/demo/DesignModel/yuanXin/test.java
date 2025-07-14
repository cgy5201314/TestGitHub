package com.example.demo.DesignModel.yuanXin;

public class test {
    public static void main(String[] args) throws Exception{
        Video2 video=new Video2();//创建一个视频
        video.setName("java");
        video.setUrl("www.baidu.com");
        System.out.println(video);
        Video2 video2=(Video2)video.clone();//克隆一个视频对象
        System.out.println(video2);
        System.out.println("-------");
        video.setName("python");
        video.setUrl("www.google.com");
        System.out.println(video);
        System.out.println(video2);

    }
}
