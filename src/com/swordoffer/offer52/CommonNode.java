package com.swordoffer.offer52;

import java.util.Stack;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CommonNode {

    /**
     * 相交的两个链表 a 和 b 有公共部分 c
     * 遍历
     * a -> c -> b [最后节点就是相交节点]
     * b -> c -> a [一样最后节点就是相交节点]
     * 这两个路径长度相同，且遍历完最后的节点就是相交节点
     * 如果链表没有相交，即 c 为 null，那遍历的最后节点就是 null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

    /**
     * 入栈出栈方式
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (headA != null || headB != null) {
            if (headA != null) {
                stack1.push(headA);
                headA = headA.next;
            }
            if (headB != null) {
                stack2.push(headB);
                headB = headB.next;
            }
        }

        ListNode node = null;
        while (!stack1.isEmpty()) {
            ListNode pop1 = stack1.pop();
            if (!stack2.isEmpty()) {
                ListNode pop2 = stack2.pop();
                if (pop1 != pop2) {
                    return node;
                } else
                    node = pop1;
            }
        }
        return node;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(6);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;

        node.next = node2;
        node2.next = node3;

        ListNode intersectionNode1 = new CommonNode().getIntersectionNode1(null, null);
        System.out.println(intersectionNode1);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}