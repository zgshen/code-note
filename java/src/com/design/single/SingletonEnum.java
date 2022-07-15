package com.design.single;

/**
 * @author nathan
 * @date 2020/8/22 14:22
 * @desc SingletonEnum
 * 枚举实现，最简单安全的方法，涉及到反序列化创建对象时可用这种
 */
public enum SingletonEnum {
    INSTANCE;
    public void whateverMethod() {
    }
}
