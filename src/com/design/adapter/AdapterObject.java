package com.design.adapter;

/**
 * @author nathan
 * @date 2020/8/22 19:50
 * @desc AdapterObject
 * 对象适配
 */
public class AdapterObject implements Target {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        System.out.println("do something...");
        adaptee.adapteeRequest();
        System.out.println("do something...");
    }
}
