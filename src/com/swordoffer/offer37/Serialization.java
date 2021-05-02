package com.swordoffer.offer37;

import com.zgshen.typora.Test;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 请实现两个函数，分别用来序列化和反序列化二叉树。

 示例:
 你可以将以下二叉树：

   1
  / \
 2   3
    / \
   4   5

 序列化为 "[1,2,3,null,null,4,5]"

 能正常序列化反序列化就行，下面实现输出实际是 1,2,null,null,3,5,null,null,null
 */
public class Serialization {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //下一个节点不存在了就返回 null 字符
        if (root == null)
            return "null";
        String ret = String.valueOf(root.val);
        String left = serialize(root.left);
        String right = serialize(root.right);
        return ret + "," + left + "," + right;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return dfs(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private TreeNode dfs(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("null"))
            return null;

        TreeNode root = new TreeNode(Integer.valueOf(str));
        root.left = dfs(queue);
        root.right = dfs(queue);
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);

        node.left = node1;
        node.right = node2;
        node.right.left = node3;

        String serialize = new Serialization().serialize(node);
        System.out.println(serialize);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}