package com.swordoffer.offer33;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 * 提示：
 * 数组长度 <= 1000
 */
public class Verify {

    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length-1);
    }

    /**
     * 递归分治
     * 后序遍历 左节点 -> 右节点 -> 根节点，且 左节点<根节点<右节点
     * 将数组按左右根分为三部分，判断最后一个元素是否满足根节点要求
     * 然后递归左右子树的部分，重复上述操作，所有判断必须为 true 才满足后序遍历要求
     * @param postorder
     * @param i  数组起始位置
     * @param j  数组末端位置
     * @return
     */
    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j-1) return true;//当 i≥j-1 ，说明此子树节点数量 ≤ 2，一个节点必然 true，两个节点任意两数也都可以构成树的后序遍历

        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p;//得到左子树长度
        while (postorder[p] > postorder[j]) p++;

        boolean res = p == j //根节点
                && recur(postorder, i, m-1) //左子树判断
                && recur(postorder, m, j-1); //右子树判断，最后一个根节点已判断所以 j-1
        return res;
    }

}
