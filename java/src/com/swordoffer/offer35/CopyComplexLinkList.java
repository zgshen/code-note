package com.swordoffer.offer35;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *  提示：
 *  -10000 <= Node.val <= 10000
 *  Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CopyComplexLinkList {

    /**
     * 哈希表方式
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));//（key，value） 映射为 （原节点，新节点）
            cur = cur.next;
        }
        /**
         * 赋值原链表，重新遍历并从哈希表中取出复制节点，并构建 next 和 radom 指向
         */
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);//下一个节点
            map.get(cur).random = map.get(cur.random);//radom 节点
            cur = cur.next;
        }
        return map.get(head);//哈希表 key 为 head 的值就是复制链表的头节点
    }

    /**
     * 拼接和拆分
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head) {
        if (head == null) return head;
        Node cur = head;
        //在原链表的各个节点后面插入复制节点
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }

        cur = head;//重新复制头节点，再遍历一次，为复制节点建立 radom 连接
        while (cur != null) {
            Node node = cur.next;
            if (cur.random != null) {
                node.random = cur.random.next;
            }
            cur = node.next;
        }

        //拆分
        Node newNode = head.next;
        cur = head;
        while (cur.next != null) {
            Node next = cur.next;
            cur.next = next.next;//每次都指向间隔的一个，拆分成两个链表
            cur = next;
        }
        return newNode;
    }
}
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}