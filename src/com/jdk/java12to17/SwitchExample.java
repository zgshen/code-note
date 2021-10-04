package com.jdk.java12to17;

import org.junit.Assert;
import org.junit.Test;

public class SwitchExample {

    /**
     * 传统做法
     */
    @Test
    public void switchBeforeTest() {
        Character c= 'B';
        String res;
        switch (c) {
            case 'A':
                res = "优秀";
                break;
            case 'B':
                res = "良好";
                break;
            case 'C':
                res = "及格";
                break;
            default:
                res = "未知等级";
        }
        Assert.assertEquals("良好", res);
    }

    /**
     * 新的 switch 表达式
     */
    @Test
    public void switchNowTest() {
        var c = 'B';
        var res = switch (c) {
            case 'A' -> "优秀";
            case 'B' -> "良好";
            case 'C' -> "及格";
            default -> "未知等级";
        };
        Assert.assertEquals("良好", res);
    }

    /**
     * 根据 object 不同的类型和条件做不同的处理
     * 这个是 17 的预览特性
     */
    @Test
    public void formatTest() {
        Object o = 1000000000;
        String formatted = switch (o) {
            case Integer i && i > 10 -> String.format("a large Integer %d", i);
            case Integer i -> String.format("a small Integer %d", i);
            case Long l    -> String.format("a Long %d", l);
            default        -> o.toString();
        };
        System.out.println(formatted);
    }

}