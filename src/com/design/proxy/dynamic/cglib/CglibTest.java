package com.design.proxy.dynamic.cglib;

import com.design.proxy.Subject;
import com.design.proxy.SubjectImpl;

/**
 * @author nathan
 * @date 2020/8/20 23:08
 * @desc CglibTest
 * JDK动态代理和CGLIB动态代理的区别
 *
 * JDK动态代理：
 * 只能代理实现了接口的类。
 * 没有实现接口的类不能实现JDK的动态代理。
 *
 * CGLIB动态代理：
 * 针对类来实现代理。
 * 对指定目标类产生一个子类，通过方法拦截技术拦截所有父类方法的调用。
 * 动态代理的典型应用：Spring AOP：同时用到了JDK动态代理和CGLIB动态代理。
 */
public class CglibTest {
    public static void main(String[] args) {
        //创建返回代理类对象
        CglibProxy proxy = new CglibProxy();
        Subject sub = (Subject) proxy.getProxy(SubjectImpl.class);
        sub.doSomething();
    }
}
