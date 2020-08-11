package com.leetcode.easy;

import com.leetcode.easy.extand.TreeNode;

/**
 * @author nathan
 * @date 2020/8/11 23:07
 * @desc IsSameTree
 */
public class IsSameTree {

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) {
            return true;//都是空的相等
        } else if (p==null || q==null) {
            return false;//有一个为空不对
        } else if (p.val != q.val) {
            return false;//值不同不对
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

}
