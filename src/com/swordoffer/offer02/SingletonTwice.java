package com.swordoffer.offer02;

/**
 * 懒汉式双重校验锁
 */
public class SingletonTwice {

    private static SingletonTwice instance;

    private SingletonTwice() {
    }

    /**
     * 减小锁粒度，到真正创建对象的时候才使用同步
     * @return
     */
    public static SingletonTwice getInstance() {
        if (instance == null) {
            synchronized (SingletonTwice.class) {
                if (instance == null) {
                    instance = new SingletonTwice();
                }
            }
        }
        return instance;
    }

}
