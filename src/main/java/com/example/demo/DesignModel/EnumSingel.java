package com.example.demo.DesignModel;

import java.lang.reflect.Constructor;

//枚举单例,防止反射
public enum EnumSingel {
    INSTANCE;
     public EnumSingel getInstance(){
         return INSTANCE;
     }
    public static void main(String[] args) throws Exception{
       EnumSingel e1= EnumSingel.INSTANCE;
       System.out.println(e1);
       EnumSingel e2= EnumSingel.INSTANCE;
       System.out.println(e2);
//        System.out.println(EnumSingel.INSTANCE.getInstance());
//        Constructor<EnumSingel> constructor = EnumSingel.class.getDeclaredConstructor(String.class, int.class);

//        Constructor<EnumSingel> constructor = EnumSingel.class.getDeclaredConstructor();

//        constructor.setAccessible(true);
//        EnumSingel e3=constructor.newInstance();
//        System.out.println(e3);
//        System.out.println(e3);
  //编译javac -c hello.java
        //反编译 javap -p hello.class
        //运行 java  hello
    }
}
