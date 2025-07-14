package com.example.demo.DesignModel.abstractFactory;
//接口的接口工厂
public interface AbstractPhoneFactory {
    PhoneProduct getPhoneProduct();
    PhoneRouter getPhoneRouter();
}
