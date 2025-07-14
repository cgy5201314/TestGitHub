package com.example.demo.DesignModel;

//abstract 修饰表示抽象类，不能new对象但是你永远无法new对象，除非继承你
public class Student {
    //默认是无参构造方法，所以我可以new你的对象
    private Student(){

    }
   public static  Student student = new Student();

}
