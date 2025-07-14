package com.example.demo.DesignModel.factory;

public class simpleFactory {
    public static void main(String[] args) {
        //静态工厂模式（简单工厂）方法1
//        Car car1=CarFactory.getCar("car1");
//        car1.name();
//        Car car2=CarFactory.getCar("car2");
//        car2.name();
        //方法2
        Car car1=CarFactory.getCar1();
        Car car2=CarFactory.getCar2();
        ///普通实现
//        Car car1=new Car1();
//        Car car2=new Car2();
//        car1.name();
//        car2.name();
    }
}

