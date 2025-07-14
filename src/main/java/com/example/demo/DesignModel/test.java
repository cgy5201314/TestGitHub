package com.example.demo.DesignModel;

public class test {
    public static void main(String[] args) {
//        SingelItem s=new SingelItem();无法new对象，只能通过方法返回实例对象
        SingelItem s1=SingelItem.getSingelItem();
        SingelItem s2=SingelItem.getSingelItem();
        if(s1==s2){
            System.out.println("单例模式成功");
        }
        System.out.println( Student.student+"1");
        Student.student=null;
        System.out.println( Student.student);
    }
}
