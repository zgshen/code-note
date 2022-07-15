package com.swordoffer.offer26;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 限制：
 * 0 <= 节点个数 <= 10000
 */
public class CheckChildTree {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //或条件前面的条件成立了后面的运算就不会执行，因为有一个 true 语句必然返回 true
        return (A != null && B != null)
                && (recur(A, B) //从根节点开始匹配，不匹配则执行下面或条件
                || isSubStructure(A.left, B) //根节点不匹配的话到左子树判断
                || isSubStructure(A.right, B)); //根节点和左子树不匹配的话到右节点判断
    }

    /**
     * 比较
     * @param A
     * @param B
     * @return
     */
    private boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;//B 为 null 表示已经匹配完成
        if (A == null || A.val != B.val) return false;
        //再递归左右节点
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}