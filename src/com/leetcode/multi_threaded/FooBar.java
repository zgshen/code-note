package com.leetcode.multi_threaded;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 *
 * 示例 2:
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
public class FooBar {

    private int n;
    //使用信号量方式
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();//获取令牌
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();//释放令牌
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) {
        //FooBar fooBar = new FooBar(5);
        //FooBar_one fooBar = new FooBar_one(5);
        //FooBar_two fooBar = new FooBar_two(5);
        FooBar_three fooBar = new FooBar_three(5);
        Thread bar = new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        bar.setName("bar");
        bar.start();

        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        foo.setName("foo");
        foo.start();
    }

}


/**
 * 同步方式
 */
class FooBar_one {

    private int n;
    private volatile boolean flag = true;

    public FooBar_one(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (!flag) this.wait();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                this.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (flag) this.wait();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                this.notify();
            }
        }
    }

}

/**
 * Lock 接口，和同步方式没啥区别
 */
class FooBar_two{

    private int n;
    private Lock lock;
    private Condition condition;
    private boolean flag;

    public FooBar_two(int n) {
        this.n = n;
        lock = new ReentrantLock();
        condition = lock.newCondition();
        flag = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                lock.lock();//堵塞到获取锁为止
                if (!flag) condition.await();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                condition.signal();
            } finally {
                lock.unlock();//一定要手动 unlock，循环最后不会 await 让出锁了，因为 await 是在前面
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                if (flag) condition.await();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

}


class FooBar_three{

    private int n;
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;

    public FooBar_three(int n) {
        this.n = n;
        this.countDownLatch = new CountDownLatch(1);
        this.cyclicBarrier = new CyclicBarrier(2);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            countDownLatch.countDown();
            try {
                cyclicBarrier.await();//每次两个一组，循环使用
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            countDownLatch.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            countDownLatch = new CountDownLatch(1);//countDownLatch 只能用一次，须重新初始化
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}