package com.example.demo.DesignModel.factory.factoryMethod;

import com.example.demo.DesignModel.factory.Car;
import com.example.demo.DesignModel.factory.Car2;

public class Car2Factory implements FactoryMethod{
    public Car getCar()
    {
        return new Car2();
    }
}
