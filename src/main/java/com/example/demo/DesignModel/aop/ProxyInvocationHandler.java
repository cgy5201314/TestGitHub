package com.example.demo.DesignModel.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {
    private Rant rant;
    public void setRant(Rant rant)
    {
        this.rant = rant;
    }
    //  获取代理对象方法
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rant.getClass().getInterfaces(),this);
    }
    //  代理的具体方法rant,参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         seeHouse();
        Object result =  method.invoke(rant,args);
        fare();
        return result;
    }
    public void seeHouse(){
        System.out.println("中介看房子");
    }
    public void fare(){
        System.out.println("中介收中介费");
    }
}
