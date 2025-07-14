package com.example.demo.DesignModel.factory;

//简单工厂模式
public class CarFactory {
//    public static Car getCar(String name){
//        if(name=="car1"){
//            return new Car1();
//        }else if(name=="car2"){
//            return new Car2();
//        }else{
//            return null;
//        }
//    }
    public static Car1 getCar1(){
        return new Car1();
    }
    public static Car2 getCar2(){
        return new Car2();
    }
}
