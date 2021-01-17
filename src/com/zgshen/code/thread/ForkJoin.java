package com.zgshen.code.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {

    private class SumTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 20;

        private int arr[];
        private int start;
        private int end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        private Integer subtotal() {
            Integer sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            System.out.println(Thread.currentThread().getName() + ": (" + start + "~" + end + ")=" + sum);
            return sum;
        }

        @Override
        protected Integer compute() {
            if ((end - start) <= THRESHOLD) {
                return subtotal();
            }else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        //提交任务到ForkJoinPool可以直接使用ForkJoinTask的invoke，隐式的使用ForkJoinPool.commonPool()池，也可以显示的创建ForkJoinPool实例通过submit提交
        ForkJoinTask<Integer> task = pool.submit(new ForkJoin().new SumTask(arr, 0, arr.length));
        //ForkJoinTask<Integer> task = pool.commonPool().submit(new ForkJoin().new SumTask(arr, 0, arr.length));
        System.out.println("计算: " + task.invoke());
        pool.shutdown();
    }

}
