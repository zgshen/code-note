package com.jdk.java18to21;

// jdk.incubator.concurrent 孵化包在 Java21 已经移除
/*import jdk.incubator.concurrent.ScopedValue;
import jdk.incubator.concurrent.StructuredTaskScope;*/
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 预览特性，作用域值。
 * scope values，变量作用于，可用于简化对象在不同线程之间的共享
 * 编译（setting-java compile）和运行（edit configurations）需要加上参数 --add-modules jdk.incubator.concurrent
 */
public class ScopedValuesExample {

    private ScopedValue<String> FRUIT = ScopedValue.newInstance();

    @Test
    public void getTest() {
        // where 绑定值，get 获取值，run() 执行完毕则生命周期结束
        ScopedValue.where(FRUIT, "apple").run(() -> {
            System.out.printf(FRUIT.get());
        });
        // 在范围之外 get 会报异常
        System.out.println(FRUIT.get());
    }

    @Test
    public void threadTest() {
        Thread t1 = new Thread(() -> {
            System.out.printf("thread 1 get value:%s\n", FRUIT.get());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.printf("thread 2 get value:%s\n", FRUIT.get());
        });

        // 预览特性编译问题注释掉
        /*System.out.println("bind value apple");
        ScopedValue.where(FRUIT, "apple", t1);
        System.out.println("bind value pear");
        ScopedValue.where(FRUIT, "pear", t2);*/

        // 没必要手动start了
        //t1.start();
        //t2.start();
    }

    // 预览特性失效
    /*@Test
    public void multiTask() {
        ScopedValue.where(FRUIT, "value", () -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                //执行不同的任务
                Future<String> f1  = scope.fork(() -> fruit1());
                Future<String> f2 = scope.fork(() -> fruit2());
                scope.join().throwIfFailed();
                System.out.printf("f1:%s, f2:%s", f1.resultNow(), f2.resultNow());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }*/

    private String fruit1() {
        return "apple";
    }

    private String fruit2() {
        return "pear";
    }

}
