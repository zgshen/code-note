package com.jdk.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    List<Fruit> fruits = new ArrayList<>(Arrays.asList(
            new Fruit("apple"),
            new Fruit("banana"),
            new Fruit("orange")
    ));

    /**
     * 遍历
     */
    @Test
    public void outputTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        integers.forEach(System.out::print);
        System.out.println();
        integers.stream().forEach(System.out::print);
        System.out.println();
        //并行流底层使用Fork/Join框架实现，异步处理，输出不一定是12345
        integers.parallelStream().forEach(System.out::print);
        System.out.println();

    }

    /**
     * 映射
     */
    @Test
    public void mapTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //映射每个元素操作，生成新的结果
        List<Integer> collect = integers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect);

        List<String> fruitList = fruits.stream().map(obj -> obj.name="I like ".concat(obj.name)).collect(Collectors.toList());
        System.out.println(fruitList);
    }

    /**
     * 排序、过滤、限制
     */
    @Test
    public void filterTest() {
        List<Integer> integers = Arrays.asList(2, 3, 1, 4, 8, 5, 9, 5);
        List<Integer> collect = integers.stream()
                //.sorted()//排序
                .sorted((x, y) -> y - x)
                .distinct()//去重
                .filter(n -> n < 6)//小于6的数
                .limit(3)//只截取3个元素
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 聚合和统计
     */
    @Test
    public void mergeTest() {
        List<String> strings = Arrays.asList("Hello", " ", "world", "!");
        String collect = strings.stream().collect(Collectors.joining(""));
        System.out.println(collect);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

}

class Fruit {
    public Fruit(String name) {
        this.name = name;
    }
    String name;

    public void setName(String name) {
        this.name = name;
    }
}