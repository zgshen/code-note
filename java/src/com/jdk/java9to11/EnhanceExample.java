package com.jdk.java9to11;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnhanceExample {

    /**
     * try 语句新特性
     */
    @Test
    public void tryTest() {
        String path = "/home/nathan/test.sh";
        //Java7 引入的 try-with-resource 机制
        try (var reader = new InputStreamReader(System.in)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Java9 可以在 try 中使用已初始化的资源
        var reader = new InputStreamReader(System.in);
        var writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            //reader是final的，不可再被赋值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 快速创建不可变集合
     */
    @Test
    public void unmodifiableCollectionTest() {
        List<Integer> integers = List.of(1, 2, 3);
        Set<String> strings = Set.of("a", "b", "c");
        Map<String, Integer> stringIntegerMap = Map.of("a", 1, "b", 2, "c", 3);
    }

    /**
     * InputStream
     * @throws IOException
     */
    @Test
    public void InputStreamTest() throws IOException {
        InputStream inputStream = EnhanceExample.class.getResourceAsStream("test.txt");

        byte[] arr = new byte[5];
        inputStream.readNBytes(arr, 0, 5);
        Assert.assertEquals("Java9", new String(arr));

        byte[] allBytes = inputStream.readAllBytes();
        Assert.assertEquals("Java10Java11", new String(allBytes));

        InputStream inputStream1 = EnhanceExample.class.getResourceAsStream("test.txt");;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        inputStream1.transferTo(outputStream);
        Assert.assertEquals("Java9Java10Java11", outputStream.toString());
    }

    /**
     * 流
     */
    @Test
    public void streamTest() {
        List<Integer> list = Arrays.asList(1, 2, 4, 5, 3, 2, 8);
        //输出1，2，4，碰到5不成立停止
        list.stream().takeWhile(x -> x < 5).forEach(System.out::println);

        //丢弃1，2，碰到3不成立停止
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5).dropWhile(i -> i%3!=0).collect(Collectors.toList());
        System.out.println(collect);

        //允许值为空
        Stream<Object> stream = Stream.ofNullable(null);

        //Optional 转 stream
        long count = Stream.of(
                Optional.of(1),
                Optional.empty(),
                Optional.of(2)).flatMap(Optional::stream).count();
        Assert.assertEquals(2, count);

        //空值throw
        Optional.empty().orElseThrow();


        Stream.iterate(1, i -> ++i).limit(5).forEach(System.out::println);
        //迭代器增强，可以直接在 iterate 内部判断
        Stream.iterate(1, i -> i <= 5, i -> ++i).forEach(System.out::println);
    }

}

/**
 * Java9 开始接口允许定义私有类
 */
interface Enhance {

    default void defaultMethod() {
        init();
    }

    private void init() {
        staticMethod();// or Enhance.staticMethod();
    }
    static void staticMethod() {
        System.out.println("static method in interface.");
    }

}
