package com.leetcode.easy;

import com.leetcode.easy.extand.ListNode;

/**
 * @author nathan
 * @date 2020/8/5 23:41
 * @desc MergeTwoLists 合并两个有序链表
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(2);
        ln1.next = new ListNode(4);
        ln1.next.next = new ListNode(6);

        ListNode ln2 = new ListNode(1);
        ln2.next = new ListNode(3);
        ln2.next.next = new ListNode(5);

        //ListNode listNode = MergeTwoLists(ln1, ln2);
        ListNode listNode = MergeTwoListsIteration(ln1, ln2);
        System.out.print(listNode.val + ",");
        while (listNode.next!=null) {
            listNode = listNode.next;
            System.out.print(listNode.val + ",");
        }

    }

    public static ListNode MergeTwoLists(ListNode ln1, ListNode ln2) {
        if (ln1 == null) return ln2;
        if (ln2 == null) return ln1;
        if (ln1.val < ln2.val) {
            ln1.next = MergeTwoLists(ln1.next, ln2);
            return ln1;
        } else {
            ln2.next = MergeTwoLists(ln1, ln2.next);
            return ln2;
        }
    }


    public static ListNode MergeTwoListsIteration(ListNode ln1, ListNode ln2) {
        ListNode preList = new ListNode(-1);
        ListNode pre = preList;
        while (ln1!=null && ln2!=null) {
            if (ln1.val <= ln2.val) {
                pre.next = ln1;
                ln1 = ln1.next;
            } else {
                pre.next = ln2;
                ln2 = ln2.next;
            }
            pre = pre.next;
        }
        pre.next = ln1==null ? ln2:ln1;
        return preList.next;
    }

}
