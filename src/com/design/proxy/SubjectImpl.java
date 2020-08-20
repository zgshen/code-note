package com.design.proxy;

import com.design.proxy.Subject;

/**
 * @author nathan
 * @date 2020/8/20 22:25
 * @desc SubjectImpl
 */
public class SubjectImpl implements Subject {
    @Override
    public void doSomething() {
        System.out.println("Doing...");
    }
}
