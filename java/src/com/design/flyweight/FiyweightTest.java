package com.design.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nathan
 * @date 2020/8/22 18:29
 * @desc FiyweightTest
 */
public class FiyweightTest {

    private static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        bookList.add(Library.getBook("Redis in action"));
        bookList.add(Library.getBook("Spring in action"));
        bookList.add(Library.getBook("Java8 in action"));
        bookList.add(Library.getBook("Redis in action"));//redis twice
        for (Book b:
             bookList) {
            b.borrow();
        }
        System.out.println(Library.getMapSize());
    }
}
