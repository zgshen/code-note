package com.jdk.java12to17;

import org.junit.Test;

import java.util.stream.Stream;

public class NewApiExample {

    @Test
    public void streamTest() {
        //.toList() 代替 .collect(Collectors.toList())
        Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i % 2 == 0)
                //.count(Collectors.toList())
                .toList()
                .forEach(System.out::println);

        //mapMulti 用多个元素替换流中的元素，原来 flatMap 也能实现
        Stream.of(1, 2, 3)
                //.flatMap(num -> Stream.of(num + num, num * num, " "))
                .mapMulti((num, downstream) -> {
                    downstream.accept(num + num);
                    downstream.accept(num * num);
                    downstream.accept(" ");
                })
                .forEach(System.out::print);
    }
}
