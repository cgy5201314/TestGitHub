package com.example.demo.Config.fan;

import java.util.ArrayList;
//好处：
//在集合，对象（指定属性类型），方法上（指定参数类型，接口上(用户实体类型还是别的类型）中约束数据类型
//在集合数据取出可以避免类型转换，

//用法，泛型类型必须是引用类型，泛型类型指定后可以传入类型的子类
public class FanXing {
    public static void main(String[] args) {
        Dog dog = new Dog("小黄", 1);
        ArrayList<Dog> list = new ArrayList<>();
        list.add(dog);
        list.add(new Dog("小黄2", 2));
        list.add(new Dog("小黄3", 3));
//        list.add(new Cat("小毛4", 4));  不用泛型
        for (Object o : list) {
            Dog dog1 = (Dog) o;
            System.out.println(dog1.getName() + ":" + dog1.getAge());
        }

    }
}
class Dog{
    String name;
    int age;
    public Dog(String  name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
class Cat{
    String name;
    int age;
    public Cat(String  name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}