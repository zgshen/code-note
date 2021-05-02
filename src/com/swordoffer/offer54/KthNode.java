package com.swordoffer.offer54;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthNode {

    int k;// 第 k 大参数
    int res;// 第 k 大节点对应值

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    void dfs(TreeNode root) {
        if (root == null)
            return;
        /**
         * 中序遍历是 左 -> 根 -> 右 刚好从大到小
         * 所有反过来倒叙中序遍历第 k 个节点就好
         * 先递归右子树，如果找不到再去左子树找
         */
        dfs(root.right);//右边
        if (k == 0) return;//表示递归上一层找到第 k 个节点了，直接返回
        k--;
        if (k == 0) res = root.val;//每层根节点，找到了就记录值
        dfs(root.left);//再找左边
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
