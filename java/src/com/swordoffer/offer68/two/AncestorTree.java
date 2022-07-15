package com.swordoffer.offer68.two;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class AncestorTree {

    TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if ((lson&&rson) //子树两路径均为 true，证明 p、q 都在子树中
                || ((root.val==p.val || root.val==q.val) && (lson||rson))) //当前节点是 p、q 的一个，并且两子树有一个为 true，表示子树存在 p、q 的另一个
        {
            //那么当前节点就是公共节点
            res = root;//此时的节点的父节点必定不满足此条件，深度已是最大
        }
        return lson || rson //左右子树至少有一个包含
            || (root.val==p.val || root.val==q.val); //或者当前节点是 p、q 中的一个
    }

    //使用哈希表的方法
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    public TreeNode lowestCommonAncestorHash(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);//先将每个节点与父节点作为键值记录到 parent 中
        //从 p 节点往祖先移动
        while (p != null) {
            visited.add(p.val);//路径记录起来
            p = parent.get(p.val);
        }
        //q 节点同样往祖先移动
        while (q != null) {
            //移动的时候判断是否有根 p 遍历一样的节点，有即该节点就是公共节点
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);//子节点为键，父节点为值
            dfs(root.left);
        }

        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}