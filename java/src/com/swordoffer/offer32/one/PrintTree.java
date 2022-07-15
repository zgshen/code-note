package com.swordoffer.offer32.one;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 * 提示：
 * 节点总数 <= 1000
 *
 */
public class PrintTree {

    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.add(root);
        //入栈出栈，每一层肯定比下层先出栈
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) continue;
            list.add(poll.val);
            queue.add(poll.left);
            queue.add(poll.right);
        }

        int arr[] = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            arr[i] = list.get(i);
        return arr;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
