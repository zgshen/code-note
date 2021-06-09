package com.design.iterator;

import java.util.ArrayList;
import java.util.List;

class NameIterator implements Iterator {

    private int index;
    private List list = new ArrayList();

    public NameIterator(List list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (index == list.size())   return false;
        else return true;
    }

    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) obj = this.list.get(index++);
        return obj;
    }
}

public class NameRepository implements Container {
    @Override
    public Iterator iterator() {
        return new NameIterator(null);
    }

    public static void main(String[] args) {
    }
}