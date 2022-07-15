package com.zgshen.code.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树深度优先遍历
 */
public class DeepTraverse {

    /**
     * 前序，根节点->左节点->右节点
     */
    private static void preOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    private static void preOderTraverseByStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序，左节点->根节点->右节点
     */
    private static void middleOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        middleOrderTraverse(node.left);
        System.out.println(node.val);
        middleOrderTraverse(node.right);
    }

    /**
     * 后序，左节点->右节点->根节点
     */
    private static void afterOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        afterOrderTraverse(node.left);
        afterOrderTraverse(node.right);
        System.out.println(node.val);
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(
                new Integer[]{3,2,9,null,null,10,null, null,8,null,4}
                ));
        /**
         *          3
         *      2      8
         *    9  10      4
         */

        System.out.println(inputList);
        TreeNode node = TreeNode.createTree(inputList);

        System.out.println("--->pre");
        preOrderTraverse(node);
        System.out.println("--->pre stack");
        preOderTraverseByStack(node);

        System.out.println("--->middle");
        middleOrderTraverse(node);

        System.out.println("--->after");
        afterOrderTraverse(node);
    }


}

