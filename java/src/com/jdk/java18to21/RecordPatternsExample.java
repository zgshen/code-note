package com.jdk.java18to21;

import org.junit.Test;

/**
 * 正式特性，记录模式。
 */
public class RecordPatternsExample {

    @Test
    public void patternTest() {
        Point p = new Point(1, 2);
        printSum(p);
    }

    /**
     * 类型判断和转换，及获取值
     * @param obj
     */
    public void printSum(Object obj) {
        /**
         * 跟普通类一样，传入对象再使用方法获取x和y的值
         */
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x+y);
        }

        /**
         *  简化操作
         *  定义传入对象声明参数类型，可在后续直接获取值
         */
        if (obj instanceof Point(int x, int y)) {
            System.out.println(x + y);
        }
    }

    @Test
    public void nestTest() {
        ColoredPoint cp = new ColoredPoint(new Point(1, 2), Color.BLUE);
        printPointY(cp);
    }

    public void printPointY(Object obj) {
        if (obj instanceof ColoredPoint coloredPoint) {
            if (coloredPoint.p() != null) {
                System.out.println(coloredPoint.p().y());
            }
        }

        /**
         * 可嵌套
         */
        if (obj instanceof ColoredPoint(Point p, Color c)) {
            System.out.println(p.y());
            System.out.println(c);
        }
    }

}

record ColoredPoint(Point p, Color c) {}

enum Color { RED, GREEN, BLUE}

record Point(int x, int y) {
    public Point(int x) {
        //this.x = x;
        this(x, 0);
    }
}
