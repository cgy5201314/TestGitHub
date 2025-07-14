package com.example.demo.DesignModel.factory.factoryMethod;

import com.example.demo.DesignModel.factory.Car;
import com.example.demo.DesignModel.factory.Car1;

public class Car1Factory implements FactoryMethod{
    public Car getCar()
    {
        return new Car1();
    }
}
