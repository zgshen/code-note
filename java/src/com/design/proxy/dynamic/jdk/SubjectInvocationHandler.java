package com.design.proxy.dynamic.jdk;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author nathan
 * @date 2020/8/20 22:37
 * @desc SubjectInvocationHandler
 */
public class SubjectInvocationHandler implements InvocationHandler {

    private Object target;

    public SubjectInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before do something, do something");
        Object result = method.invoke(target, args);
        System.out.println("After do something, do something");
        return result;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        SubjectInvocationHandler handler = new SubjectInvocationHandler(new RealSubject());
        Subject subject = (Subject) handler.getProxy();
        subject.request();
    }
}

interface Subject {
    void request();
}
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject request");
    }
}
