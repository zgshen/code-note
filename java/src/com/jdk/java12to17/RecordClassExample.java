package com.jdk.java12to17;

import org.junit.Assert;
import org.junit.Test;

/**
 * record 用于简化一些类的申明，性质类似 final 类，但可以定义为普通类形式、定义在其他类内部或定义在函数内部
 */
public class RecordClassExample {

    @Test
    public void recordTest() {
        var user = new User(1, "nathan", 25);
        Assert.assertEquals(1, user.id());
        Assert.assertEquals("nathan", user.username());
        Assert.assertEquals(25, user.age());
        //User[id=1, username=nathan, age=25]
        System.out.println(user);
    }

    @Test
    public void innerTest() {
        record Customer (String name, int age) {
            @Override
            public String toString() {
                return "Customer{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        };
        Customer akari = new Customer("Akari", 30);
        System.out.printf("record object:%s", akari);
    }

}

/**
 * 不可变数据透明载体类
 */
record User(long id, String username, int age) {
    //private int otherInt;
    /*{
        System.out.println();
    }*/
    //明确声明全部参数的构造器
    /*public User(long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }*/
    //全参数构造器简化写法
    /*public User {
    }*/
    //其他构造器要明确调用其他已定义构造器，最后调用最底层还是声明全部参数的构造器
    public User(){
        this(1, "nathan", 25);
    }
}
