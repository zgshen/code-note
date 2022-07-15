package com.leetcode.multi_threaded;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 *
 * 示例 2:
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 *  
 * 提示：
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 */
public class Foo {

    AtomicInteger firstJob;
    AtomicInteger secondJob;

    public Foo() {
        firstJob = new AtomicInteger();
        secondJob = new AtomicInteger();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJob.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJob.get() != 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJob.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJob.get() != 1) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        secondJob.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        //System.out.println(foo.atomicInteger.incrementAndGet());
        foo.first(() -> {
            System.out.println(111);
        });

        foo.second(() -> {
            System.out.println(222);
        });

        foo.third(() -> {
            System.out.println(333);
        });
    }
}
