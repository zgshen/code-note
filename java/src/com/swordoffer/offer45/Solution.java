package com.swordoffer.offer45;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 *
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class Solution {

    /*
     * 自定义排序
     * 设数组 numsnums 中任意两数字的字符串为 xx 和 yy ，则规定排序判断规则 为：
     * 若拼接字符串 x + y > y + x，则 x “大于” y;
     * 反之，若 x + y < y + x，则 x “小于” y。
     *
     * 需要证明自定义排序规则是有效的，并且满足传递性，这是难点，如果要理清证明流程，
     * 这题应该属于 hard 难度题目。
     */

    /**
     * 用小根堆，排序的规则一样，o1 + o2 > o2 + o1,（字典序小的放在堆顶）
     * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/492343
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        for (int num : nums) {
            queue.add("" + num);
        }
        StringBuilder sb  = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 直接使用内置函数的方式
     * @param nums
     * @return
     */
    public String minNumber1(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }

}
