package com.example.demo.DesignModel.abstractFactory;

public class HuaweiPhone implements PhoneProduct{

    @Override
    public void call() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendMessage() {
        System.out.println("华为手机发短信");
    }
}
