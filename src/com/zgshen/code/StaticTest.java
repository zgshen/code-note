package com.zgshen.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * @author nathan
 * @date 2020/8/7 16:27
 * @desc StaticTest 类加载测试
 */
public class StaticTest {

    public static void main(String[] args) {
        staticFunction();
    }

    {// 非静态初始化块
        System.out.println("2");
    }

    static {
        System.out.println("1");
    }

    static StaticTest st = new StaticTest();

    public StaticTest() {
        System.out.println("3");
        System.out.println("a="+a+",b="+b + ",c=" + c);
    }

    public static void staticFunction(){
        System.out.println("b=" + b);
    }

    int a=110;
    static int b =112;
    static final int c = 113;

}
