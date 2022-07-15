package com.swordoffer.offer55.one;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 提示：
 * 节点总数 <= 10000
 */
public class TreeDeepLen {

    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    /**
     * 后序遍历（DFS）
     * @param root
     * @return
     */
    int dfs(TreeNode root) {
        if (root == null) return 0;
        int m = dfs(root.left);
        int n = dfs(root.right);
        return 1 + (m>n? m:n);
    }

    /**
     * 层序遍历（BFS）
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>(), tmp;
        queue.add(root);
        int num = 0;
        while (!queue.isEmpty()) {
            tmp = new LinkedList<>();//每层子树创建新队列用于替换
            for (TreeNode node : queue) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            num++;
        }
        return num;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);

        node1.left = node2;
        node2.right = node3;
        int i = new TreeDeepLen().maxDepth(node1);
        System.out.println(i);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
