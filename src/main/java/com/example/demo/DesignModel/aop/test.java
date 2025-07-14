package com.example.demo.DesignModel.aop;

public class test {
    public static void main(String[] args) {
        Hoster hoster = new Hoster();

//        ProxyInvocationHandler pih = new ProxyInvocationHandler();
//        // 设置代理对象
//        pih.setRant(hoster);
//        // 获取代理对象
//        Rant rant = (Rant)pih.getProxy();
//        rant.rent();
        ProxyUtil proxyUtil = new ProxyUtil();
       proxyUtil.setTarget(hoster);
        Rant rant = (Rant) proxyUtil.getProxy();
        rant.rent();
    }

}
