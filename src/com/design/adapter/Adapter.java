package com.design.adapter;

/**
 * @author nathan
 * @date 2020/8/22 19:48
 * @desc Adapter
 * 类适配
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        System.out.println("do something...");
        super.adapteeRequest();
        System.out.println("do something...");
    }
}
