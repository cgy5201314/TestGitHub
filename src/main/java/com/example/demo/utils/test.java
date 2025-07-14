package com.example.demo.utils;

public class test {
    public static void main(String[] args){
        System.out.println("主线程");
       Thread t1= new Thread(()->{
            System.out.println("线程1");
        });

       Thread t2= new Thread(()->{
           try {
               t1.join();//等待t1执行完毕
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("线程2");
        });

       Thread t3= new Thread(()->{
           try {
               t2.join();//等待t2执行完毕
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("线程3");
        });
       t1.start();
       t2.start();
       t3.start();
    }
}
