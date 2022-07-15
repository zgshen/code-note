package com.design.flyweight;

/**
 * @author nathan
 * @date 2020/8/22 18:23
 * @desc RealBook
 */
public class RealBook implements Book {
    private String bookName;

    public RealBook(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public void borrow() {
        System.out.println("borrow book,name:" + bookName);
    }
}
