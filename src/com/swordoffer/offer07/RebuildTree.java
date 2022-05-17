package com.swordoffer.offer07;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class RebuildTree {

    int[] preorder;//前序遍历数组
    Map<Integer, Integer> map = new HashMap<>();//中序

    /**
     * 递归很难写，分为只有一层递归再到多层递归两种当时来理解比较容易
     *
     * 构建树的过程其实就是在找每级树根节点的过程
     * 当递归到叶子节点，此时 preL==preR ，然后 preL + 1
     * 再往下层递归 由于 preL 已经大于 preR，结束最后一级递归，往上层返回
     * @param preL 树前序数组左边界，不是左节点下标！整棵树就是从0开始
     * @param preR 前序右边界
     * @param inL 中序左边界
     * @param inR 中序右边界
     * @return
     */
    private TreeNode recur(int preL, int preR, int inL, int inR) {
        if (preL > preR)
            return null;

        TreeNode root = new TreeNode(preorder[preL]);//前序遍历数组左边界即为本级树根节点
        int rootIndex = map.get(preorder[preL]);//得到中序数组根节点下标

        int leftSize = rootIndex - inL;//得到左子树长度，不管是前序还是中序，子树长度都一样的，只是遍历排序情况不同

        /**
         * 继续找左子树的根节点
         * 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
         */
        root.left = recur(preL+1, preL+leftSize, inL, rootIndex-1);
        /**
         * 继续找右子树的根节点
         * 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
         */
        root.right = recur(preL+1+leftSize, preR, rootIndex+1, inR);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //全局
        this.preorder = preorder;
        //构建哈希映射
        int len = preorder.length;
        for (int i=0; i<len; i++) {
            map.put(inorder[i], i);
        }
        return recur(0, len-1, 0, len-1);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}