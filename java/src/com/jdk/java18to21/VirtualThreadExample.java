package com.jdk.java18to21;

import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class VirtualThreadExample {

    /**
     * 平台线程和虚拟线程
     * @throws InterruptedException
     */
    @Test
    public void virtualVsPlatformTest() throws InterruptedException {
        Thread vt = Thread.ofVirtual().unstarted(() -> {
            System.out.println("this is a virtual thread.");
        });
        vt.start();

        Thread dv = new Thread(() -> {
            System.out.println("default platform thread.");
        });
        dv.start();

        Thread pv = Thread.ofPlatform().unstarted(() -> {
            System.out.println("this is a platform thread.");
        });
        pv.start();

        System.out.printf("is virtual?: %s %s %s %n", vt.isVirtual(), dv.isVirtual(), pv.isVirtual());

        Thread.startVirtualThread(() -> {
            System.out.println("this is a another virtual thread.");
        });
    }
    
    
    @Test
    public void executorsExpTest() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {System.out.println("virtual thread test.");});
            //executor.close(); 
            //try-with-resources会隐式调用close
        }
    }

    /**
     * 执行时间对比
     */
    @Test
    public void contrastTest() {
        long s1 = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            task(executor);
        }
        long s2 = System.currentTimeMillis();
        System.out.printf("虚拟线程耗时:%sms %n", s2-s1);

        try (var executor = Executors.newCachedThreadPool()) {
            task(executor);
        }
        long s3 = System.currentTimeMillis();
        System.out.printf("无限制线程池耗时:%sms %n", s3-s2);

        try (var executor = Executors.newFixedThreadPool(300)) {
            task(executor);
        }
        long s4 = System.currentTimeMillis();
        System.out.printf("固定线程数量线程池耗时:%sms %n", s4-s3);
    }

    public void task(ExecutorService executor) {
        IntStream.range(0, 10_000).forEach(i -> {
            executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                return i;
            });
        });
    }

    /**
     * ThreadLocal 中使用需要注意大量创建虚拟线程的资源占用
     * @throws InterruptedException
     */
    @Test
    public void ThreadLocalTest() throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread vt = Thread.ofVirtual().unstarted(() -> {
            threadLocal.set("thread1");
            System.out.println(threadLocal.get());
        });

        Thread pt = Thread.ofPlatform().unstarted(() -> {
            try {
                vt.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            threadLocal.set("thread2");
            System.out.println(threadLocal.get());
        });
        
        vt.start();
        pt.start();
    }

    /**
     * 虚拟线程调度
     */
    @Test
    public void schedulerTest() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            //业务代码...
            System.out.println("create virtual thread to run task.");
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                Future<Integer> future = executor.submit(() -> {
                    System.out.printf("i:%s, thread:%s %n", finalI, Thread.currentThread());
                    Thread.sleep(Duration.ofMillis(30));
                    System.out.printf("i:%s, thread:%s %n", finalI, Thread.currentThread());
                    return 1;
                });
            }
        }
    }

    /**
     * 多任务
     */
    @Test
    public void mutilTask() {
        long start = System.currentTimeMillis();
        int[] s = {7, 2, 8, -9, 4, 0};
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var f1 = executor.submit(() -> task("t1", s, 0, s.length/2));
            var f2 = executor.submit(() -> task("t2", s, s.length/2, s.length));
            int sum = f1.get() + f2.get();
            System.out.println("sum:" + sum + " completed. spend time:" 
                    + (System.currentTimeMillis()-start));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    int task(String name, int[] s, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += s[i];
        }
        return sum;
    }

}
