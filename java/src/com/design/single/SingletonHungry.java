package com.design.single;

/**
 * @author nathan
 * @date 2020/8/22 13:37
 * @desc SingletonHungry
 * 饿汉式
 * 建议使用
 */
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}
