package com.swordoffer.offer02;

/**
 * 枚举方式实现单例
 */
public class SingletonEnum {

    private SingletonEnum() {
    }

    private static enum Singleton {
        INSTANCE;
        private SingletonEnum singletonEnum;

        private Singleton() {
            singletonEnum = new SingletonEnum();
        }

        public SingletonEnum getInstance() {
            return singletonEnum;
        }
    }

    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        SingletonEnum singletonEnum = SingletonEnum.getInstance();
        SingletonEnum singletonEnum1 = SingletonEnum.getInstance();
        System.out.println(singletonEnum == singletonEnum1);
    }

}
