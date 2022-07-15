package com.jdk.java12to17;

import org.junit.Test;

public class TextBlocksExample {

    @Test
    public void strTest() {
        //换行
        var str = """
                Hello World!
                Java 17 is now available!
                """;
        System.out.println(str);
    }

    @Test
    public void blankTest() {
        /**
         * \  行终止符，用于阻止插入换行符
         * \s 表示一个空格，用来避免末尾的空白字符被去掉
         */
        var str = """
                Hello World!\
                Java 17 is now available!    \s
                """;
        System.out.println(str);
    }

    @Test
    public void jsonStrTest() {
        //有引号也不需要转义
        var str = """
                {
                    id: 1,
                    username: "nathan",
                    age: 25
                }
                """;
        System.out.println(str);
    }

    @Test
    public void htmlStrTest() {
        var str = """
                <html>
                    <body>
                       <p>Java 17 is now available!</p>
                    </body>
                </html>
                """;
        System.out.println(str);
    }

}
