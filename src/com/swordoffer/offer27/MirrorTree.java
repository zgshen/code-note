package com.swordoffer.offer27;

import java.util.Stack;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *  
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 *
 */
public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode node = root.left;

        /*root.left = root.right;
        root.right = node;
        mirrorTree(root.left);
        mirrorTree(root.right);*/
        //等价于

        root.left = mirrorTree(root.right);
        root.right = mirrorTree(root.left);

        return root;
    }


    public TreeNode mirrorTreeByStack(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
