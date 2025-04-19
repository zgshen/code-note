package com.jdk.java18to21;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Iterator;

//import static java.lang.StringTemplate.RAW;
//import static java.util.FormatProcessor.FMT;

/**
 * 预览特性，字符串模板。
 * java23已移除
 */
public class StringTemplateExample {

    /*@Test
    public void STRTest() {
        String name = "nathan";
        // STR 模板处理器
        String str = STR."I am \{name}.";
        System.out.println(str);

        // 可以内嵌表达式
        int a=10, b=20;
        int[] arr = {1, 2, 3};
        System.out.println(STR."\{a} + \{b} = \{a+b}");
        System.out.println(STR."Tody is \{LocalDate.now()}.");
        System.out.println(STR."\{a++}, \{a++}, \{arr[0]}");
    }*/

    /**
     * 多行模板表达式
     */
    /*@Test
    public void mutilLineTest() {
        var version = 21;
        var str = STR."""
                <html>
                    <body>
                       <p>Java \{version} is now available!</p>
                    </body>
                </html>
                """;
        System.out.println(str);
    }*/

    /*@Test
    public void FMTTest() {
        record Rectangle(String name, double width, double height) {
            double area() {
                return width * height;
            }
        }
        Rectangle[] zone = new Rectangle[] {
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };

        String strTable = STR."""
            Description  Width  Height  Area
            \{zone[0].name}  \{zone[0].width}  \{zone[0].height}     \{zone[0].area()}
            \{zone[1].name}  \{zone[1].width}  \{zone[1].height}     \{zone[1].area()}
            \{zone[2].name}  \{zone[2].width}  \{zone[2].height}     \{zone[2].area()}
            Total \{zone[0].area() + zone[1].area() + zone[2].area()}
            """;
        String fmtTable = FMT."""
            Description     Width    Height     Area
            %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
            %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
            %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
            \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
            """;
        System.out.println(strTable);
        System.out.println(fmtTable);
    }*/

    /*@Test
    public void RAWTest() {
        String name = "Joan";
        //String info = "My name is \{name}."; //error
        StringTemplate st = RAW."My name is \{name}.";
        System.out.println(STR.process(st));
    }

    @Test
    public void interfaceTest() {
        int x = 10, y = 20;
        StringTemplate st = RAW."\{x} plus \{y} equals \{x + y}";
        System.out.println(st.fragments());
        System.out.println(st.values());
        //StringTemplate{ fragments = [ "", " plus ", " equals ", "" ], values = [10, 20, 30] }
        //输出两部分，一部分是字符串的各段和各段之间的插值占位

        //实现接口，组装数据的方法，如果自己要加料就可以在这里处理
        var INTER = StringTemplate.Processor.of((StringTemplate _st) -> {
            StringBuilder sb = new StringBuilder();
            Iterator<String> fragIter = _st.fragments().iterator();
            for (Object value : _st.values()) {
                sb.append(fragIter.next());
                sb.append(value);
                sb.append("_");//加点东西看看
            }
            sb.append(fragIter.next());
            return sb.toString();
        });
        System.out.println(INTER."\{x} plus \{y} equals \{x + y}");
        //| 10 and 20 equals 30
    }

    public static void main(String[] args) {

        var INTER = StringTemplate.Processor.of((StringTemplate st) -> {
            StringBuilder sb = new StringBuilder();
            Iterator<String> fragIter = st.fragments().iterator();
            for (Object value : st.values()) {
                sb.append(fragIter.next());
                sb.append(value);
            }
            sb.append(fragIter.next());
            return sb.toString();
        });

        int x = 10, y = 20;
        String s = INTER."\{x} plus \{y} equals \{x + y}";
        System.out.println(s);

    }*/

}
