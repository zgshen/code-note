package com.leetcode.multi_threaded;

import java.util.concurrent.atomic.AtomicInteger;

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
