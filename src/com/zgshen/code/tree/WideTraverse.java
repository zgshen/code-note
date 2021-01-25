package com.zgshen.code.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class WideTraverse {

    public static void levelOrderTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
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

        levelOrderTraverse(node);
    }
}
