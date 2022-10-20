package com.zgshen.code.map;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcurrentTest {
    
    private static ConcurrentHashMap<String, Integer> cm = new ConcurrentHashMap();

    /**
     * putIfAbsent，哈希表没有的键值才会设置成功，存在则不会修改
     */
    @Test
    public void putTest() {
        Integer a = cm.putIfAbsent("aa", 1);
        Integer b = cm.putIfAbsent("aa", 2);
        System.out.printf("%s, %s, %s \n", a, b, cm); //输出的是aa-1
    }

    /**
     * 如果键值对不存在就写入默认值返回，存在就继续执行操作后返回
     */
    @Test
    public void mergeTest() {
        cm.putIfAbsent("aa", 1);
        // aa存在，会执行++value后返回
        cm.merge("aa", 1, (key, value) -> {
           return ++value;
        });
        // bb不存在，设置为1后返回
        cm.merge("bb", 1, (key, value) -> {
            return value;
        });

        System.out.printf("%s \n", cm);
    }

    /**
     * 计算操作
     */
    @Test
    public void computeTest() {
        cm.putIfAbsent("aa", 1);
        cm.compute("aa", (key, value) -> {
            return value + 10;
        });
        /*cm.compute("", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String s, Integer integer) {
                return null;
            }
        });*/
        System.out.printf("%s \n", cm);
        
        // 键值对存在才做，这里bb不存在所以什么都不做
        cm.computeIfPresent("bb", (key, value) -> {
            return value + 10;
        });
        System.out.printf("%s \n", cm);

        // 键值对不存在才做，bb不存在值设置为2返回
        cm.computeIfAbsent("bb", key -> 2);
        System.out.printf("%s \n", cm);
    }
    
}
