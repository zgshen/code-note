package com.swordoffer.offer02;

/**
 * 单例模式
 */
public class Singleton {

    private static Singleton instance;

    /**
     * 私有构造
     */
    private Singleton() {
    }

    /**
     * 只适用单线程环境的创建方式
     * @return
     */
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
