package com.example.demo.DesignModel;
//饿汉模式
public class SingelItem {
    private SingelItem(){
        System.out.println(Thread.currentThread().getName()+"ok");
    };//私有化无参构造函数私有化，这样就不能创对象
//    private static SingelItem singelItem =new SingelItem();
//    public static SingelItem getSingelItem(){
//        return singelItem;
//    }
    private static SingelItem singelItem;
    public static SingelItem getSingelItem() {

        if (singelItem == null)
            synchronized (SingelItem.class) {
                //由于多线程的问题，可能会创建多个对象，可以考虑枷锁
                if (singelItem == null) {
                    singelItem = new SingelItem();
                }
                else {return singelItem; }
       }
        return singelItem;
    }

    public static void main(String[] args) {
        for(int i=1;i<10;i++) {
            new Thread(() -> {
                SingelItem.getSingelItem();
                System.out.println(SingelItem.getSingelItem());
            }).start();
        }
    }
}
//单例模式三步走
//1.私有化构造函数（防止new多个对象
//2.创建私有化静态对象属性（防止，通过类.属性进行修改
//3.创建公共静态方法，返回私有化静态对象属性
