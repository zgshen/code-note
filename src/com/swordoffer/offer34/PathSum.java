package com.swordoffer.offer34;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 target = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 提示：
 * 节点总数 <= 10000
 */
public class PathSum {

    LinkedList<List<Integer>> res = new LinkedList<>();//符合路径集合
    LinkedList<Integer> path = new LinkedList<>();//路径值集合

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    private void recur(TreeNode root, int tar) {
        if (root == null) return;
        tar = tar - root.val;
        path.add(root.val);

        //树的根节点开始往下一直到叶节点所经过的节点形成一条路径
        if (tar == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        } else {
            recur(root.left, tar);
            recur(root.right, tar);
        }
        path.removeLast();
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}