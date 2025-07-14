package com.example.demo.DesignModel.abstractFactory;

public class XiaomiPhone implements PhoneProduct{
    @Override
    public void call() {
        System.out.println("XiaomiPhone call");
    }

    @Override
    public void sendMessage() {
        System.out.println("XiaomiPhone sendMessage");
    }
}
