package com.example.demo.utils;

import java.util.concurrent.Semaphore;

public class test3 {
    public static void main(String[] args) {
        Semaphore s1=new Semaphore(1);
        Semaphore s2=new Semaphore(0);
        Semaphore s3=new Semaphore(0);
        Thread t1=new Thread(()->{
            try {
                s1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1");
            s2.release();
        });
        Thread t2=new Thread(()->{
            try {
               s2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2");
            s3.release();//通知后续的线程
        });
        Thread t3=new Thread(()->{
            try {
                s3.acquire();
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
