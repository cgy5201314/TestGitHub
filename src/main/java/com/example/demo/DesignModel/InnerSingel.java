package com.example.demo.DesignModel;

import java.lang.reflect.Constructor;

//静态内部类单例模式
public class InnerSingel {
    private InnerSingel(){
    };
    public static InnerSingel getInstance(){
        return InnerClass.innerSingel;
    }
    private static class InnerClass{
        private static final InnerSingel innerSingel = new InnerSingel();
    }


}
