package com.jdk.java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {

    public LambdaTest() {
    }
    public LambdaTest(String str) {
        //use param str to do something
    }

    public static void interfaceTest(SingleFncInterface singleFunInterface) {
        singleFunInterface.doSomething("123");
    }

    public void simpleMenthod(String str) {
        System.out.println("simple method. str is:");
    }

    public static void staticMenthod(String str) {
        System.out.println("static menthod. str is:");
    }

    /**
     * Lambda 本质就是单函数接口
     */
    @Test
    public void singleFunTest() {
        //作为参数的形式
        LambdaTest.interfaceTest((String str) -> {
            System.out.println("single function interface. param:" + str);
        });

        //SingleFncInterface s = (String str) -> System.out.println(str);
        SingleFncInterface s = str -> System.out.println(str);
        s.doSomething("123");

        //简化形式，方法引用
        //LambdaTest.interfaceTest(item -> System.out.println(item));
        LambdaTest.interfaceTest(System.out::println);
    }

    /**
     * 方法引用
     */
    @Test
    public void refTest() {
        //静态引用。意思就是用 String 的 valof() 方法来实现 Function 接口的 apply 方法
        Function<Integer, String> fun = String::valueOf;
        String apply = fun.apply(100);
        System.out.println(apply);

        //静态引用
        SingleFncInterface sfi1 = LambdaTest::staticMenthod;

        //实例引用
        LambdaTest lambdaTest = new LambdaTest();
        SingleFncInterface sfi2 = lambdaTest::simpleMenthod;

        //构造引用，带参数
        SingleFncInterface sfi3 = LambdaTest::new;

        //构造引用，不带参数
        Runnable runnable = LambdaTest::new;
        //runnable.run();//单函数 Runnable 接口 run 方法由 LambdaTest 构造实现
    }

    /**
     * 四种类型函数式接口
     */
    @Test
    public void funTest() {
        /**
         * Function<T, R>
         * 调用方法 R apply(T t);
         * T：入参类型，R：出参类型
         */
        Function<Integer, Integer> function = n -> n*n;
        Integer apply = function.apply(10);
        System.out.println(apply);

        /**
         * Consumer<T>
         * 调用方法：void accept(T t);
         * T：入参类型；没有出参
         */
        Consumer<String> consumer = System.out::println;
        consumer.accept("output msg.");

        /**
         * Supplier<T>
         * 调用方法：T get();
         * T：出参类型；没有入参
         */
        Supplier<Integer> supplier = () -> 10*10;
        Integer integer = supplier.get();
        System.out.println(integer);

        /**
         * Predicate<T>
         * 调用方法：boolean test(T t);
         * T：入参类型；出参类型是Boolean
         */
        Predicate<Integer> predicate = num -> num>10;//是否大于10
        boolean test = predicate.test(20);
        System.out.println(test);
    }

}

/**
 * 单函数接口
 */
@FunctionalInterface
interface SingleFncInterface {
    void doSomething(String str);

    default void print() {
        System.out.println("default method.");
    }
}