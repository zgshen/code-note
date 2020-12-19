package com.design.composite;

/**
 * 组合模式
 * 简单的如树结构
 */
public class Composite {

    public static void main(String[] args) {
        Node parent = new Node("p");
        Node child = new Node("c");

        parent.next = child;

        Node node = parent;
        while (node != null) {
            System.out.println(node.getValue());
            node = node.next;
        }
    }
}

class Node {
    private String value;
    public Node next;
    public Node() {
    }
    public Node(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
