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
     * 类型检查
     * 根据 object 不同的类型和条件做不同的处理
     * 这个是 17 的预览特性
     */
    @Test
    public void formatTest() {
        Object o = 1000000000;
        String formatted = switch (o) {
            //相当于 if (o instanceof Integer && (int)o > 10)
            //18之后版本不支持内部计算
            //case Integer i && i > 10 -> String.format("a large Integer %d", i);
            case Integer i -> String.format("a small Integer %d", i);
            case Long l    -> String.format("a Long %d", l);
            case null      -> String.format("null!");
            default        -> o.toString();
        };
        System.out.println(formatted);
    }

    /**
     * 要么全用 -> 要么全用 ：，用 -> 不用手动加 break
     */
    @Test
    public void switchTest() {
        Integer num = 2;
        switch (num) {
            case null:
                System.out.println("null!");
                break;
            case 1, 2, 3: // 1、2或3
                System.out.println(num);
                break;
            default:
                System.out.println("default1");
        }

        switch (num) {
            case null -> System.out.println("null!");
            case 1, 2 -> System.out.println(num);
            default -> System.out.println("default2");
        }
    }

}
