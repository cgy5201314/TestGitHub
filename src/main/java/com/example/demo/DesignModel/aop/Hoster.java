package com.example.demo.DesignModel.aop;

public class Hoster implements Rant{
    @Override
    public void rent(){
        System.out.println("我是房东我需要出租房子");
    }
}
