package com.example.demo.DesignModel.factory.factoryMethod;

import com.example.demo.DesignModel.factory.Car;

//工厂方法模式,方便新增时满足开闭原则
public interface FactoryMethod {
    Car getCar();
}
