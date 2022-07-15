package com.leetcode.multi_threaded;

import java.util.concurrent.Semaphore;

/**
 * 信号量方式
 */
public class FooSemaphore {

    private Semaphore sp1 = new Semaphore(0);
    private Semaphore sp2 = new Semaphore(0);

    public FooSemaphore() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        sp1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sp1.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        sp2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        sp2.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        FooSemaphore fooSemaphore = new FooSemaphore();

        new Thread(() -> {
            try {
                fooSemaphore.second(() -> {
                    System.out.println(22);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                fooSemaphore.third(() -> {
                    System.out.println(33);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                fooSemaphore.first(() -> {
                    System.out.println(11);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
