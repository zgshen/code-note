package com.design.single;

/**
 * @author nathan
 * @date 2020/8/22 13:39
 * @desc SingletonDoubleCheckLock
 * 双重校验锁方式，一般饿汉用静态内部类方式，特殊要求才用这种
 */
public class SingletonDoubleCheckLock {

    //volatile防指令重排拿到未完全初始化对象
    private static volatile SingletonDoubleCheckLock instance;

    private SingletonDoubleCheckLock() {
    }

    public SingletonDoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheckLock.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheckLock();
                }
            }
        }
        return instance;
    }

    /**
     * instance = new Singleton() 对应字节码：
     * 1 NEW // 在堆内存中分配内存，将指向该区域的引用放入操作数栈
     * 2 DUP // 在操作数栈中复制引用
     * 3 INVOKESPECIAL // 调用Singleton类的构造方法
     * 4 PUTSTATIC // 将引用赋值给静态变量instance
     *
     * 在JVM执行以上字节码的时候，如果不加volatile关键字，那么可能在DUP指令(指令2)执行之后，跳过执行构造方法的指令(指令3)，
     * 而直接执行PUTSTATIC指令(指令4)，然后用操作数栈上剩下的引用来执行指令3。因为在单线程环境下，JVM认为打乱指令3、4的执行顺序并不会影响程序的正确性。
     * 但是，在多线程环境下，如果指令3、4发生重排，当执行完指令1、2、4之后，instance对象已经不再为null，此时来一个线程调用getInstance方法，
     * 就会拿到一个尚未完全初始化的对象，从而发生对象逃逸。这种现象在单例类的构造函数耗时很大时更加频繁。而volatile关键字的存在则告诉JVM，
     * 在处理被volatile修饰的变量时，禁止使用指令重排。
     * 参考：https://segmentfault.com/a/1190000022801946?utm_source=tag-newest
     */

}
