package com.swordoffer.offer08;

/**
 * 查找二叉树下一个节点
 * 1、如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点
 * 2、否则，向上找第一个左链接指向的树包含该节点的祖先节点
 */
public class GetNext {

    public TreeLinkNode getNext(TreeLinkNode node) {
        if (node.right != null) {
            TreeLinkNode rNode = node.right;
            while (rNode.left != null) {
                rNode = rNode.left;//找到最左节点
            }
            return rNode;
        } else {
            while (node != null) {
                TreeLinkNode pNode = node.next;
                if (pNode.left == pNode) {
                    return pNode;//找到最顶端节点
                }
                node = node.next;
            }
        }
        return null;
    }
    public static void main(String[] args) {

    }

}

class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; // 指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}