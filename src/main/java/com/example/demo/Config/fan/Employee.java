package com.example.demo.Config.fan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private int sal;
    private MyDate birthday;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }

    public static void main(String[] args) {
        Employee employee = new Employee("xsx", 1000, new MyDate(2020, 1, 1));
        Employee employee1 = new Employee("ssw", 10002, new MyDate(2020, 2, 2));
        Employee employee2 = new Employee("ssw", 10003, new MyDate(2020, 1, 3));
        List<Employee> list=new ArrayList<>();
        list.add(employee);
        list.add(employee1);
        list.add(employee2);
        list.sort(new Comparator<Employee>(){

            @Override
            public int compare(Employee o1, Employee o2) {
//                int nameCompare = o1.getName().compareTo(o2.getName());
                // 如果名字相同，再按生日排序（日期比较）
//                if (nameCompare == 0) {
//                    return o1.getBirthday().compareTo(o2.getBirthday());
//                }
//                return nameCompare;
                int i=o1.getName().compareTo(o2.getName());
                if(i!=0){  //不等于0表示名字不同，直接返回结果
                    return i;
                }
              int j= Integer.compare(o1.getBirthday().getYear(),o2.getBirthday().getYear());
                if(j!=0){
                    return j;
                }
                int k= Integer.compare(o1.getBirthday().getMonth(),o2.getBirthday().getMonth());
                if(k!=0){
                    return k;
                }
                return Integer.compare(o1.getBirthday().getDay(),o2.getBirthday().getDay());

            }
        });
        list.forEach(e -> System.out.println(e));
    }
}
