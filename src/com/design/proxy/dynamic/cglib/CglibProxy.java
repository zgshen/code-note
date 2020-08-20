package com.design.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author nathan
 * @date 2020/8/20 23:10
 * @desc CglibProxy
 */
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before do something, do something");
        //代理类调用父类的方法
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("After do something, do something");
        return obj;
    }

    public Object getProxy(Class clazz){
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(clazz);
        //设置需要织入的逻辑
        enhancer.setCallback(this);
        //使用织入器创建子类
        return enhancer.create();
    }
}
