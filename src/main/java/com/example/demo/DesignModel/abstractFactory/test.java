package com.example.demo.DesignModel.abstractFactory;


//抽象工厂模式
//针对不同的品牌具有类似的产品发挥作用
//顶级抽象类定义返回 产品 的方法
//不同的品牌有不同的工厂实现了抽象类，返回各自的 产品品牌
//对于产品如手机，路由器，都定义了接口方法实现功能
//不同的品牌创建自己的产品实现类，实现产品接口
//当有新的品牌，且具有类所的产品时，只需创建新的工厂类，实现抽象类，返回新的产品类即可

public class test {
    public static void main(String[] args) {
        XiaomiFactory xiaomi=new XiaomiFactory();
        PhoneProduct phone=xiaomi.getPhoneProduct();
        phone.call();
        phone.sendMessage();
        System.out.println("-----------------");
        HuaweiFactory huawei=new HuaweiFactory();
        PhoneProduct phone1=huawei.getPhoneProduct();
        phone1.call();
        phone1.sendMessage();
    }
}
