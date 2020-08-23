package com.design.observer;

/**
 * @author nathan
 * @date 2020/8/23 23:04
 * @desc Observer
 * 观察者
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
