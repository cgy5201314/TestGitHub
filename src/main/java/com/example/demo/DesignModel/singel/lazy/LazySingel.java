package com.example.demo.DesignModel.singel.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

//dcl懒汉式单例,枷锁
public class LazySingel {
    private static Boolean flag = false;
    private LazySingel(){
        if(flag==false){
            flag=true;
        }else{
            throw new RuntimeException("单例对象已创建");
        }
    }
    private volatile static LazySingel lazySingel;//原子性操作
    public static LazySingel getLazySingel(){
        if(lazySingel==null)
           synchronized (LazySingel.class){
               if(lazySingel==null){
                   lazySingel = new LazySingel();//不是原子性操作有问题
                   //1.分配内存空间
                   //2.执行构造函数，初始化对象
                   //3.对象指向空间
                   //期望的是123，但是执行可能是 132等
                   //13时指向空间，但是对象还未创建，另一个线程进来认为已经创建了，
                   // 直接返回报错，所以要避免指令重排，lazySingel+volatile
               }else  {
                   return lazySingel;
               }
           }

        return lazySingel;
    }

    public static void main(String[] args) throws Exception {
       LazySingel  s1= LazySingel.getLazySingel();
        System.out.println(s1);
        Constructor<LazySingel> constructor = LazySingel.class.getDeclaredConstructor();

//        LazySingel  s1=LazySingel.getLazySingel();
//        System.out.println(s1);

        Field flag = LazySingel.class.getDeclaredField("flag");
        flag.setAccessible(true);
        System.out.println(flag.get(s1));
        flag.set(s1,false);//重新改回值
        LazySingel s2=constructor.newInstance();
        System.out.println(s2);

    }
}
//懒汉式单例,多线程有问题
//public class LazySingel {
//    private LazySingel(){
//    }
//    private static LazySingel lazySingel;
//    public static LazySingel getLazySingel(){
//        if(lazySingel==null){
//            lazySingel = new LazySingel();
//        }
//        return lazySingel;
//    }
//}
