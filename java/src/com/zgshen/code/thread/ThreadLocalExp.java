package com.zgshen.code.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalExp {

    private static ThreadLocal<Dog> local = new InheritableThreadLocal<>();
    //如果是 newFixedThreadPool(2) 第二个输出不一定都是10
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    @Test
    public void localTest() throws InterruptedException {
        local.set(new Dog());
        pool.execute(() -> {
            System.out.printf("1 sub thread dog age: %s \n", local.get().age);
        });

        Dog dog = new Dog();
        dog.setAge(20);
        local.set(dog);
        
        pool.execute(() -> {
            System.out.printf("2 sub thread dog age: %s \n", local.get().age);
        });

        System.out.printf("3 main thread dog age: %s \n", local.get().age);
        
        pool.awaitTermination(1, TimeUnit.SECONDS);
        pool.shutdown();
    }

    public static class Dog {
        private Integer age = 10;
        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
