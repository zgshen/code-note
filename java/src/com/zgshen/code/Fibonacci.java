package com.zgshen.code;

import java.util.stream.Stream;

/**
 * @author nathan
 * @date 2020/8/10 22:36
 * @desc Fibonacci 斐波那契数列
 */
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fi = new Fibonacci();
        //for (int i=0; i<10; i++)
        //System.out.println(fi.fibonacci(i));

        Stream.iterate(new long[]{0, 1}, a -> new long[]{a[1], a[0] + a[1]})
                .limit(10)
                .map(a -> a[1])
                .forEach(System.out::println);
    }

    private long fibonacci(int num) {
        if (num == 0 || num == 1) {
            return num;
        }
        return fibonacci(num -1) + fibonacci(num - 2);
    }

}
