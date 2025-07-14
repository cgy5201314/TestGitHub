package com.example.demo.DesignModel.abstractFactory;

public class XiaomiFactory implements AbstractPhoneFactory{

    @Override
    public PhoneProduct getPhoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public PhoneRouter getPhoneRouter() {
        return null;
    }
}
