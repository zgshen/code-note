package com.zgshen.code.thread;

import java.util.concurrent.*;

public class AQSExp {

    /**
     * 递减计数器
     */
    private static void countDownLatch() {
        //并发数
        int threadNumber = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadNumber; i++) {
            final int j = i;
            Thread thread = new Thread(() -> {
                try {
                    //hold住所有进程业务，等计数器为0后执行
                    countDownLatch.await();
                    System.out.println("output:" + j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //业务代码...
            });
            thread.start();
        }
        //计数器减1开发并发业务
        countDownLatch.countDown();
    }

    private static void countDownLatchExt() throws InterruptedException {
        int threadNumber = 150;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
            }).start();
        }
        //等待前面线程全部完成，即计数器减为0时
        countDownLatch.await();
    }

    /**
     * 循环屏障
     */
    private static void cyclicBarrier() throws BrokenBarrierException, InterruptedException {
        //并发数
        int count = 10;
        //加上主线程 count + 1 个，若改成 3 可以看到 CyclicBarrier 是可以重复使用的
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count + 1);
        for (int i = 0; i < count; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    //每一次 await 加1，数量满了（count+1）执行业务
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                //业务代码...
                System.out.println("output:" + j);
            }).start();
        }
        cyclicBarrier.await();
    }

    /**
     * 信号量
     */
    private static void semaphore() {
        final int clientCount = 5;
        final int totalRequestCount = 20;
        //限流，只允许 clientCount 个许可线程
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    //获得许可
                    semaphore.acquire();
                    //availablePermits() 剩余许可数量，当前占用一个，所以必然小于5
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //业务完成，释放许可
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        //计数器，类似闸门，一次性
        //countDownLatch();
        //countDownLatchExt();

        //cyclicBarrier();

        semaphore();
    }

}
