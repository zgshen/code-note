package com.jdk.java9to11;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class VariableExample {

    @Test
    public void var() {
        String str1 = "abc";
        var str2 = "abc";
        Assert.assertEquals(str1, str2);
    }

    @Test
    public void collection() {
        //相当于 Object 用
        var list = new ArrayList<>();
        list.add(123);
        list.add("abc");

        // 表示对象引用，类名很长的情况能简化代码编写
        var v = new VariableExample();
        System.out.println(v.getClass().getName());

        Runnable runnable = () -> System.out.println("interface var");
        // 无法表示接口引用，毕竟匿名类方式无法推断是哪个类的实现
        //var r = () -> System.out.println("interface var");
    }

    @Test
    public void strTest() {
        //空白判断
        " ".isBlank();

        //去除首位空格
        String s = "java";
        String str = '\u2000' + s + '\u2000';//半角空格
        Assert.assertEquals(str.trim(), str);
        Assert.assertEquals(str.strip(), s);

        //去除首部空格
        Assert.assertEquals("java", " java".stripLeading());
        //去除尾部空格
        Assert.assertEquals("java", "java ".stripTrailing());

        //复制
        Assert.assertEquals("javajava", s.repeat(2));

        //行数统计
        Assert.assertEquals(3, "A\nB\nC".lines().count());
    }
}
