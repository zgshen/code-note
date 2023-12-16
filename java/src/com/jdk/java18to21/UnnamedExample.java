package com.jdk.java18to21;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 未命名模式和变量，预览版
 */
public class UnnamedExample {

    record Point(int x, int y) { }
    record User(Order order) {}
    record Order() {}

    @Test
    public void unnamedPatternTest() {
        Point p = new Point(1, 2);
        unnamedPattern(p);
    }
    public void unnamedPattern(Object obj) {
        // 如果用不上某些变量可以省略写为 _
        if (obj instanceof Point(int x, _)) {
            System.out.printf("x value is:%d.", x);
        }

        // switch 也可以这么用
        switch (obj) {
            case User(_) -> System.out.printf("user object.");
            case Point(_, _) -> System.out.println("point object.");
            default -> System.out.printf("default output.");
        }
    }

    @Test
    public void unnamedVariablesTest() {
        Queue<Integer> list = new LinkedList<>(List.of(1, 2, 3));
        int sum = 0;
        for (Integer _ : list) {
            sum += 10;
        }

        while (list.size() > 1) {
            var _ = list.remove();
        }

        int _ = sum;
        try {
            Point _ = new Point(1, 2);
        } catch (Exception _) {
            throw new RuntimeException();
        }

    }

}

