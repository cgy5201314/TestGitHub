package com.example.demo.DesignModel.abstractFactory;

public class HuaweiFactory implements AbstractPhoneFactory{

    @Override
    public PhoneProduct getPhoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public PhoneRouter getPhoneRouter() {
        return null;
    }
}
