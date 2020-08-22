package com.design.single;

/**
 * @author nathan
 * @date 2020/8/22 14:20
 * @desc SingletonStatic
 * 登记式/静态内部类
 * 明确要使用饿汉式用这种较好
 */
public class SingletonStatic {

    private SingletonStatic() {
    }

    /**
     * 使用静态内部类的延迟初始化机制实现单例
     * 调用getInstanceByStaticClass方法时才会完成静态内部类Singleton.SingletonHolder的加载过程(类加载、链接、初始化
     */
    private static class SingletonHolder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
