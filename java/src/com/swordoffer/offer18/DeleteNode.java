package com.swordoffer.offer18;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *  
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 */
public class DeleteNode {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null)
            return head;

        ///第一个元素相同直接返回下一下
        if (head.val == val)
            return head.next;

        ListNode h = head;

        /*while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            }
            head = head.next;
            //若删除的是最后元素，后面没元素了，直接退出
            if (head == null)
                break;
        }
        return h;*/

        while (h.next!=null && h.next.val!=val)
            h = h.next;
        //若下一个元素不为空，证明上面循环有节点值等于需要删除的值了
        if (h.next!=null)
            h.next = h.next.next;//next节点指向下一位

        return head;
    }

    public ListNode deleteNode1(ListNode head, int val) {
        if (head==null) return head;
        if (head.val==val) return head.next;
        ListNode cur=head;

        while (cur.next!=null && cur.next.val!=val) cur=cur.next;
        if (cur.next!=null) cur.next = cur.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode listNode = new DeleteNode().deleteNode1(node1, 4);
        System.out.println(listNode);
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
