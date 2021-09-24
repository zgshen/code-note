package com.jdk.java12to17;

import org.junit.Test;

public class SealedExample {
    @Test
    public void sealedTest() {
        new Cat().speak();
        new Dog().speak();
        new Husky().speak();
    }
}

//接口也可以 sealed interface Animal permits Cat, Dog {
abstract sealed class Animal permits Cat, Dog {
    void eat() {
        System.out.println("have a meal.");
    }
    abstract void speak();
}
//需要明确用 final 修饰
final class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("miao");
    }
}
//如果想被继承的话用 non-sealed 修饰
non-sealed class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("wang");
    }
}

class Husky extends Dog {
    @Override
    void speak() {
        System.out.println("Stupid humans!");
    }
}
