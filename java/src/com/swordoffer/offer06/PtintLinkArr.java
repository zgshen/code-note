package com.swordoffer.offer06;

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
        int arr[] = new int[stack.size()];
        for (int i = 0; i < arr.length; i++) {
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
    public int[] reversePrint2(ListNode head) {
        ListNode newList = null;
        int len = 0;
        while (head != null) {
            ListNode next = head.next;
            // 插入头部
            head.next = newList;
            newList = head;
            // 下一个继续遍历
            head = next;
            len++;
        }

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

        int[] ints = new PtintLinkArr().reversePrint1(listNode);
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