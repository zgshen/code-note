package com.jdk.java8;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class CompletableFutureExample {

    @Test
    public void futureTest() throws ExecutionException, InterruptedException, TimeoutException {
        //单纯地返回一个值
        CompletableFuture<String> future = CompletableFuture.completedFuture("msg");
        System.out.println(future.get());

        //直接进行运算并返回
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2500L);
                //Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 + 1;
        });
        //是否执行完毕
        System.out.println(supplyAsync.isDone());
        //立刻返回执行结果或异常，否则返回指定值
        System.out.println(supplyAsync.getNow(1));
        //设置超时
        System.out.println(supplyAsync.get(2, TimeUnit.SECONDS));
    }

    @Test
    public void apply() throws ExecutionException, InterruptedException {
        //多步骤处理，一个步骤处理完把结果返回给下一步继续处理，同步 thenApply，异步 thenApplyAsync
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(1)
                .thenApply(i -> i + 2)
                .thenApplyAsync(i -> i + 3)
                //计算完毕后的处理，不影响 get 返回值
                .whenCompleteAsync((result, exception) -> {
                    result *= 10;
                    System.out.println("calculate result:" + result);
                });
        System.out.println(future.get());
    }

    @Test
    public void thenComposeExample() throws ExecutionException, InterruptedException {
        String original = "Message";
        //将字符串转换大写，得到结果再转换小写，再组合起来
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApply(s -> s.toUpperCase())
                .thenCompose(upper -> CompletableFuture.completedFuture(original)
                        .thenApply(s -> s.toLowerCase())
                        .thenApply(s -> upper + s));
        System.out.println(cf.get());
    }

    @Test
    public void allof() throws ExecutionException, InterruptedException {
        List<Integer> integers = List.of(1, 2, 3);
        List<CompletableFuture<Integer>> futureList = integers.stream()
                        .map(item -> CompletableFuture.completedFuture(item).thenApplyAsync(num -> num * num))
                        .collect(Collectors.toList());
        CompletableFuture<Void> allof = CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                .whenCompleteAsync((result, exception) -> {
                    futureList.forEach(cf -> {
                        System.out.println(cf.getNow(0));
                    });
                });
        //handle 住看输出结果，因为是上面都用异步的，这里不等很可能看不到输出
        allof.get();
    }

}
