package com.design.single;

/**
 * @author nathan
 * @date 2020/8/22 13:33
 * @desc SingletonLazy
 * 懒汉式
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public synchronized static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
