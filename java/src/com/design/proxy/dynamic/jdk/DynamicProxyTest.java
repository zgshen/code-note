package com.design.proxy.dynamic.jdk;

import com.design.proxy.Subject;
import com.design.proxy.SubjectImpl;

/**
 * @author nathan
 * @date 2020/8/20 22:43
 * @desc DynamicProxyTest
 * jdk动态代理测试
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        SubjectInvocationHandler handler = new SubjectInvocationHandler(subject);
        Subject proxy = (Subject) handler.getProxy();
        proxy.doSomething();
    }
}
