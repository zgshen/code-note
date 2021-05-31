package com.jdk.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {

    public static void interfaceTest(SingleFunInterface singleFunInterface) {
        singleFunInterface.doSomething("123");
    }

    public static void staticMenthod() {
        System.out.println("static menthod.");
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

        SingleFunInterface s = (String str) -> System.out.println(str);
        s.doSomething("123");

        //简化形式，方法引用
        LambdaTest.interfaceTest(System.out::println);
    }

    /**
     * 静态方法
     */
    @Test
    public void test() {
        Function<Integer, String> fun = String::valueOf;
        String apply = fun.apply(100);
        System.out.println(apply);
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
interface SingleFunInterface {
    void doSomething(String str);
}