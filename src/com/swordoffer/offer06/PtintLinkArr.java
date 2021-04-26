package com.swordoffer.offer06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class PtintLinkArr {

    /**
     * 栈先进后出
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int len = stack.size();
        int arr[] = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }

    /**
     * 递归
     */
    public int[] reversePrint1(ListNode head) {
        return recursive(head, 0);
    }
    public int[] recursive(ListNode head, int i) {
        if (head == null)
            return new int[i];//遍历到最后一个元素的下个 null 节点得到数组长度了就可以创建数组返回，下面操纵赋值

        int arr[] = recursive(head.next, i+1);
        arr[arr.length - i - 1] = head.val;//倒叙赋值
        return arr;
    }

    /**
     * 头插法
     */
    public int[] reversePrint2(ListNode oldList) {
        ListNode newList = new ListNode(-1);
        int len = 0;
        while (oldList != null) {
            ListNode next = oldList.next;

            oldList.next = newList.next;
            newList.next = oldList;//oldList 节点塞入 newList 和 newList.next 之间

            oldList = next;
            len++;
        }

        newList = newList.next;
        int arr[] = new int[len];
        while (newList != null) {
            arr[arr.length - len] = newList.val;
            newList = newList.next;
            len--;
        }
        return arr;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1).setNext(
                        new ListNode(3).setNext(
                        new ListNode(2).setNext(
                        new ListNode(5)
                )));

        int[] ints = new PtintLinkArr().reversePrint2(listNode);
        System.out.println(Arrays.toString(ints));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    public ListNode setNext(ListNode next) {
        this.next = next;
        return this;
    }
}