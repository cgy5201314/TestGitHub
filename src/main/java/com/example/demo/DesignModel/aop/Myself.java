package com.example.demo.DesignModel.aop;

import org.apache.catalina.Host;

public class Myself {
    public static void main(String[] args) {
        //直接找房东
//        Hoster host=new Hoster();
//        host.rent();
        //代理找房东

        Hoster host=new Hoster();
        Proxy proxy=new Proxy(host);
        proxy.rent();
    }
}
