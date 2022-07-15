package com.swordoffer.offer36;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 */
public class Convert {

    Node pre;
    Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        //需要闭环
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 核心做法就是当前节点 node 的 left 和 right
     * 通过 dfs 递归修改子树 node
     */
    private void dfs(Node node) {
        if (node == null) return;
        //递归左子树，直到最后 null 返回表示到叶子节点了
        dfs(node.left);

        //节点左子树不为 null，就修改双向节点引用，步骤 1 和 2
        if (pre != null)
            pre.right = node;//2
        else
            head = node;//为空就是链表头节点，新构建的双向链表返回这个就行
        node.left = pre;//1
        pre = node;//完毕移动当前节点做 pre，对于右子树当前 node 就是 pre

        //递归右子树
        dfs(node.right);
    }

    public static void main(String[] args) {
        Node n = new Node(1);
        Convert c = new Convert();
        c.treeToDoublyList(n);
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};