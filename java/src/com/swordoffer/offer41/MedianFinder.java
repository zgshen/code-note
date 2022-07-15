package com.swordoffer.offer41;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 */
public class MedianFinder {
    //使用最小堆和最大堆解决

    Queue<Integer> a, b;

    /** initialize your data structure here. */
    public MedianFinder() {
        a = new PriorityQueue<>();//小顶堆，保存较大的一半
        b = new PriorityQueue<>((x, y) -> (y-x));//优先队列，最大优先。大顶堆，保存较小的一半
        /*b = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });*/
    }

    public void addNum(int num) {
        if (a.size() != b.size()) {
            //不相等说明 a 一般比 b 元素多了，这次该放到 b 中了
            a.add(num);//先放到 a 中，再获取 a 中最小的元素
            b.add(a.poll());//然后放到 b 中
        } else {
            b.add(num);//先放到 b 中，再获取 b 最大的元素
            a.add(b.poll());//优先将元素放到 a 中
        }

    }

    /**找中位数**/
    public double findMedian() {
        return a.size()!=b.size() ? a.peek() : (a.peek()+b.peek())/2.0;
    }


}
