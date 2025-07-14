package com.example.demo.utils;

import java.util.concurrent.CountDownLatch;

public class test2 {
    public static void main(String[] args) {
        CountDownLatch latch1=new CountDownLatch(1);
        CountDownLatch latch2=new CountDownLatch(1);
        Thread t1=new Thread(()->{
            System.out.println("线程1");
            latch1.countDown();//通知后续的线程
        });
        Thread t2=new Thread(()->{
            try {
                latch1.await();//等待latch1的通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2");
            latch2.countDown();//通知后续的线程
        });
        Thread t3=new Thread(()->{
            try {
                latch2.await();//等待latch2的通知
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
