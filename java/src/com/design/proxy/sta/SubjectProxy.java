package com.design.proxy.sta;

import com.design.proxy.Subject;
import com.design.proxy.SubjectImpl;

/**
 * @author nathan
 * @date 2020/8/20 22:27
 * @desc SubjectProxy
 */
public class SubjectProxy implements Subject {

    private SubjectImpl subject;

    public SubjectProxy(SubjectImpl subject) {
        this.subject = subject;
    }

    @Override
    public void doSomething() {
        System.out.println("Before do something, do something");
        subject.doSomething();
        System.out.println("After do something, do something");
    }
}
