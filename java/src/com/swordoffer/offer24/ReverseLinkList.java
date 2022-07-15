package com.swordoffer.offer24;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *  
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 */
public class ReverseLinkList {

    /**
     * 遍历头插
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode newList = null, cur = null;
        while (head != null) {
            cur = head;
            head = head.next;
            cur.next = newList;
            newList = cur;
        }
        return newList;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;

        ListNode listNode = new ReverseLinkList().reverseList(node1);
        System.out.println(listNode);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}