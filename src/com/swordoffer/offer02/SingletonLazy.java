package com.swordoffer.offer02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 懒汉式单例模式
 * 加同步锁，创建前判断
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy(){
    }

    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
