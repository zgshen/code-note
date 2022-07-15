package com.swordoffer.offer22;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 *
 */
public class OutputKNode {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head==null) return null;
        int i = 0;

        ListNode h = head;
        while (head.next != null) {
            i++;
            //让i和j相隔k个元素，遍历完数组，h就是倒数蒂k个元素
            if (i >= k)
                h = h.next;
            head = head.next;
        }
        //如果k比链表的长度还长，那么倒数第k个数就是不存在的
        if (k > i+1) {
            return null;
        }
        return h;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode kthFromEnd = new OutputKNode().getKthFromEnd(node1, 5);
        System.out.println(kthFromEnd);
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}