package com.leetcode.multi_threaded;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {

    private Semaphore h;
    private Semaphore o;

    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);//凑够两个 h 才能输出 o
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);//h 的信号量要两个
    }


    public static void main(String[] args) {
        //H2O h2O = new H2O();
        H2O_one h2O = new H2O_one();
        new Thread(() -> {
            try {
                for (int i = 0; i < 6; i++) {
                    h2O.hydrogen(() -> System.out.print("h"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    h2O.oxygen(() -> System.out.print("o"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}


class H2O_one {

    int h_num = 0;
    Lock lock = new ReentrantLock();
    Condition hc = lock.newCondition();
    Condition oc = lock.newCondition();

    public H2O_one() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (h_num == 2) {
                hc.signal();
                oc.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            h_num++;
            if (h_num == 2) hc.signal();//最后的 h 抢占到，须 signal
        } finally {
            lock.unlock();
        }
    }
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        lock.lock();
        try {
            while (h_num != 2) hc.await();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            h_num = 0;
            oc.signalAll();
        } finally {
            lock.unlock();
        }
    }
}