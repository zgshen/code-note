package com.jdk.java18to21;

import org.junit.Test;

import java.util.*;

/**
 * 正式特性，有序集合。
 */
public class CollectionExample {

    /**
     * 以前获取元素的方式不统一
     */
    @Test
    public void getCollectionElementTest() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.get(0);
        list.get(list.size() - 1);

        Deque<Integer> deque = new LinkedList<>(list);
        deque.getFirst();
        deque.getLast();

        SortedSet<Integer> set = new TreeSet<>(list);
        set.getFirst();
        set.getLast();

        LinkedHashSet<Integer> linkList = new LinkedHashSet<>(list);
        Iterator<Integer> iterator = linkList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
        }
    }

    /**
     * 现在列表和链表都能使用统一的方式获取首尾元素了
     */
    @Test
    public void getCollectionElementNewTest() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.getFirst();
        list.getLast();

        LinkedHashSet<Integer> linkList = new LinkedHashSet<>(list);
        linkList.getFirst();
        linkList.getLast();
    }

}
