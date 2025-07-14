package com.example.demo.DesignModel.aop;

public class Proxy implements Rant{
    private Hoster hoster;
    // 构造方法代理对象房东
    public Proxy(Hoster hoster)
    {
        this.hoster = hoster;
    }
    public Proxy(){};
    // 代理方法
    public void rent()
    {
        seeHouse();
        hoster.rent();
        heTong();
        fare();
    }
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    public void fare(){
        System.out.println("收中介费");
    }
    public void heTong(){
        System.out.println("签合同");
    }
}
