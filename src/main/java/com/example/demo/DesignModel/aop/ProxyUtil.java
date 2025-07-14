package com.example.demo.DesignModel.aop;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil implements InvocationHandler {
    //1.设置代理接口
    private Object target;
    public void setTarget(Object  target){
        this.target=target;
    }
    //2.创建代理对象
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
    //3.代理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       log(method.getName());
        Object result =method.invoke(target,args);
        return result;
    }
    public void log(String msg){
        System.out.println("执行了"+msg);
    }
}
