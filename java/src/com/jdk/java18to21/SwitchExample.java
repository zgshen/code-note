package com.jdk.java18to21;

import org.junit.Test;

public class SwitchExample {

    /**
     * 新增 when 关键字用于后续的条件判断，jdk19
     */
    @Test
    public void whenTest() {
        Person person = new Tom(30);
        switch (person) {
            case null -> System.out.println("null!");
            case Jack jack -> System.out.println("I am jack");
            case Tom tom
                    when tom.age == 30 -> System.out.println("Tom's age is 30");
            default -> System.out.println("Who am i?");
        }
    }

    /**
     * 密闭类全部穷举可省略default
     */
    @Test
    public void coverageTest() {
        Person person = new Nathan(29);
        switch (person) {
            case Tom tom -> System.out.println("I am tom.");
            case Jack jack -> System.out.println("I am jack.");
            case Nathan nathan -> System.out.println("I am nathan.");
            //default -> ...
            //由于 Person 是一个密闭类，实现类只有固定的三个，
            //所以这里的三个 case 已经穷尽所有情况，可以不用加 default 分支。
        };
    }

}

sealed abstract class Person permits Tom, Jack, Nathan {
    int age;

    Person(int age) {
        this.age = age;
    }
}

final class Tom extends Person {
    public Tom(int age) {
        super(age);
    }
}

final class Jack extends Person {
    public Jack(int age) {
        super(age);
    }
}

final class Nathan extends Person {
    public Nathan(int age) {
        super(age);
    }
}