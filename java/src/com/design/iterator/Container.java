package com.design.iterator;

public interface Container {
    Iterator iterator();
    void add(Object object);
    void remove(Object object);
}
