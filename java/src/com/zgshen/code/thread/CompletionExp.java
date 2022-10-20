package com.zgshen.code.thread;

import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.*;

/**
 * ExecutorCompletionService 使用
 * 在多个不确定执行时间的 Future 任务中，get 方法可能会堵塞其他任务，其他完成的任务一直无法释放线程。
 * ExecutorCompletionService 将 Future 任务放到队列中，
 * poll 方法获取最先执行完成的任务，避免一个 Future 的 get 方法超执行太久或时失败堵塞其他任务。
 * 有些类似 selector 多路复用器的功能。
 */
public class CompletionExp {
    
    private int task1() throws InterruptedException {
        Thread.sleep(1000L);
        return 1;
    }

    private int task2() throws InterruptedException {
        Thread.sleep(2000L);
        return 2;
    }

    private int task3() throws InterruptedException {
        Thread.sleep(3000L);
        return 3;
    }
    
    @Test
    public void task() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        
        completionService.submit(() -> task1());
        completionService.submit(() -> task2());
        completionService.submit(() -> task3());

        for (int i = 0; i < 4; i++) {
            //Future<Integer> p = completionService.take(); // take 堵塞直到获取最先完成的任务
            Future<Integer> p = completionService.poll(4, TimeUnit.SECONDS); // poll 可以设置超时，不堵塞
            if (p == null) {
                System.out.println("done...");
                return;
            }
            System.out.println(p.get());
        }
        
    }
}
