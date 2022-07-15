package com.swordoffer.offer32.three;

import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 提示：
 * 节点总数 <= 1000
 *
 */
public class PrintTreeAsLinkList {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = null;
        queue.add(root);

        //设置标志和使用栈记录倒叙的记录
        boolean flag = false;
        Stack<Integer> tmp = new Stack<>();
        //入栈出栈，每一层肯定比下层先出栈
        while (!queue.isEmpty()) {
            int size = queue.size();
            list = new ArrayList<>();
            while (size > 0) {
                size--;
                TreeNode poll = queue.poll();
                if (poll == null) continue;
                queue.add(poll.right);
                queue.add(poll.left);
                if (!flag)
                    tmp.add(poll.val);
                else list.add(poll.val);
            }
            flag = !flag;
            while (!tmp.isEmpty())
                list.add(tmp.pop());
            if (!list.isEmpty())
                res.add(list);
        }
        return res;
    }


    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = null;//链表可双向，奇偶分别从首尾添加就好
        queue.add(root);

        //入栈出栈，每一层肯定比下层先出栈
        while (!queue.isEmpty()) {
            int size = queue.size();
            list = new LinkedList<>();
            while (size > 0) {
                size--;
                TreeNode poll = queue.poll();
                if (poll == null) continue;

                if (res.size()%2 == 0)
                    list.addLast(poll.val);//偶数 头->尾
                else
                    list.addFirst(poll.val);//奇数 尾->头
                queue.add(poll.left);
                queue.add(poll.right);
            }
            if (!list.isEmpty())
                res.add(list);
        }
        return res;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}