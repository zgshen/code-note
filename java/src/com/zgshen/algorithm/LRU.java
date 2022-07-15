package com.zgshen.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * least recently used
 */
public class LRU {

    //伪头部，伪尾部
    Node head, tail;
    //容量
    int capacity;
    //节点数
    int size;
    //容器哈希表
    Map<Integer, Node> cache;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        //+2多首尾伪节点
        cache = new HashMap<>(capacity + 2);

        head = new Node();
        tail = new Node();
        //初始状态只有两个伪节点
        head.next = tail;
        tail.pre = head;
    }

    //获取
    public int get(int key) {
        Node Node = cache.get(key);
        if (Node == null) return -1;
        moveToHead(Node);
        return Node.value;
    }

    //添加
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            //不存在就创建新的
            Node newNode = new Node(key, value);
            //添加进哈希表
            cache.put(key, newNode);
            //移动到头部
            addToHead(newNode);
            //数量加1
            size++;
            if (size > capacity) {
                //大于容量移除尾部元素
                Node tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            //存在则更新值并移动到头部
            node.value = value;
            moveToHead(node);
        }
    }

    //移除节点
    private void removeNode(Node node) {
        //删除节点，因为是双向链表，需要修改前面元素的next和后面元素的pre
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //添加到头部
    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;

        head.next.pre = node;
        head.next = node;
    }

    //移动到头部
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    //移除尾部节点
    private Node removeTail() {
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(1, 11);
        lru.put(2, 22);
        lru.put(3, 33);
        System.out.println(lru.cache);

        lru.put(1, 111);
        System.out.println(lru.cache);

        lru.put(4, 44);
        System.out.println(lru.cache);
    }

}

class Node {
    public Node pre;
    public Node next;

    public int key;
    public int value;

    public Node() {
    }

    public Node(int key,int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
