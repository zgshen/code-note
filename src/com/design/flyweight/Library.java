package com.design.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nathan
 * @date 2020/8/22 18:25
 * @desc Library
 */
public class Library {

    private static final Map<String, Book> booksMap = new HashMap<>();

    public static Book getBook(String bookName) {
        Book book = booksMap.get(bookName);
        if (book == null) {
            book = new RealBook(bookName);
            booksMap.put(bookName, book);
        }
        return book;
    }

    public static int getMapSize() {
        return booksMap.size();
    }

}
