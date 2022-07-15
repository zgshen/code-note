package com.swordoffer.offer44;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 *
 * 限制：
 * 0 <= n < 2^31
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
     * 确定 n 所在 数字 的 位数 ，记为 digit ；
     * 确定 n 所在的 数字 ，记为 num ；
     * 确定 n 是 num 中的哪一数位，并返回结果。
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1;
        int start =1, count = 9;
        //循环是为了确定 digit，start 是数字范围的起始数字
        while (n > count) {
            n = n - count;//n跟着移动一个又一个区间
            digit = digit + 1;
            start = start * 10;
            count = 9 * digit * start;
        }
        long num = start + (n-1)/digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public static void main(String[] args) {
        int nthDigit = new Solution().findNthDigit(11);
        System.out.println(nthDigit);
    }
}
