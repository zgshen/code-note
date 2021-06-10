package com.design.iterator;

import java.util.ArrayList;
import java.util.List;

public class NameRepository implements Container {

    private int index;
    private List list = new ArrayList();

    @Override
    public void add(Object object) {
        list.add(object);
    }

    @Override
    public void remove(Object object) {
        boolean remove = list.remove(object);
        if (remove) index--;
    }

    @Override
    public Iterator iterator() {
        return new NameIterator(list);
    }

    class NameIterator implements Iterator {

        private List list;

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

    public static void main(String[] args) {
        Container container = new NameRepository();
        container.add("g");
        container.add("a");
        container.add("f");
        container.add("e");

        Iterator iterator = container.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next.equals("g"))  {
                container.remove("g");
                continue;
            }
            System.out.println(next);
        }

    }
}