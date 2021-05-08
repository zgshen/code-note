package com.swordoffer.offer59.one;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 */
public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length ==0 || k==0) return new int[0];
        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        int res[] = new int[nums.length - k + 1];
        for(int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // i 为滑动窗口的起始位置，j 为尾部位置
            // 删除 deque 中对应的 nums[i-1]。即超出窗口的那个元素在队列头，此时要出队列了，队列里只存当前窗口范围中的元素
            if(i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst();
            // 保持 deque 递减。如果队列中元素值比新遍历到的元素小，需要删掉
            while(!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            // 记录窗口最大值。i>=0表示满足一个完整的窗口了
            if(i >= 0)
                res[i] = deque.peekFirst();//最大的元素永远在队列 first
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = {6, 4, 2, 5};
        int[] ints = new Solution().maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(ints));
    }

}
