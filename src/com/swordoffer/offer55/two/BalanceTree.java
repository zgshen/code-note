package com.swordoffer.offer55.two;


/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 */
public class BalanceTree {

    boolean isBalance = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return isBalance;
    }

    int dfs(TreeNode root) {
        if (root == null) return 0;
        int m = dfs(root.left);
        int n = dfs(root.right);

        if (Math.abs(m - n) > 1)
            isBalance = false;
        return 1 + (m>n? m:n);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
