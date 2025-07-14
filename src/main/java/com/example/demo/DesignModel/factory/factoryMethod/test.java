package com.example.demo.DesignModel.factory.factoryMethod;

import com.example.demo.DesignModel.factory.Car;


//工厂方法模式，为每个对象创建一个工厂，需要哪个对象调用哪个工厂
//对象实现工厂接口，
public class test {
    public static void main(String[] args) {
        Car car1=new Car1Factory().getCar();
        car1.name();
        Car  car2=new Car2Factory().getCar();
       car2.name();
    }
}
