package com.swordoffer.offer02;

/**
 * 饿汉式单例模式
 * 静态创建
 */
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}
