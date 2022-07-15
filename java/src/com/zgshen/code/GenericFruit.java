package com.zgshen.code;

import java.util.*;

/**
 * @author nathan
 * @date 2020/9/9 17:08
 * @desc GenericFruit
 */
public class GenericFruit {
    static class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

    static class Apple extends Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }

    static class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    static class GenerateTest<T>{
        public void show_1(T t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t){
            System.out.println(t.toString());
        }
    }

    public static void show_11(GenerateTest<?> tt){
        System.out.println(tt.toString());
    }

    public static <T> void show_22(T tt){
        System.out.println(tt.toString());
    }

    public static void main(String[] args) {
        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        GenerateTest<Apple> aa = new GenerateTest<Apple>();

        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        Person person = new Person();

        generateTest.show_1(apple);
        //generateTest.show_1(person);
        //show_11(aa);
        show_11(aa);


        List<String> lsa = new ArrayList<String>(); // Not really allowed.
        Object o = lsa;
        o = 1;
        System.out.println(o);
        System.out.println(lsa);
    }




}
