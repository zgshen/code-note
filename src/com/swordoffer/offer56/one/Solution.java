package com.swordoffer.offer56.one;

import java.util.Arrays;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * 限制：
 * 2 <= nums.length <= 10000
 */
public class Solution {


    public int[] singleNumbers(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];//全部数字异或结果为两个不同的数的异或结果
        }

        int x = 1;
        while (!((res & 1) == 1)) {
            res = res >>> 1;//找出两个不同数二进制最低位不同的位数
            x++;
        }
        int divide = (int) Math.pow(2, x - 1);//位数上为1，低位为0对应的数，例 100 = 4
        int m = 0, n = 0;
        for (int i = 0; i < nums.length; i++) {
            //数组两部分各自的异或结果为不同的两个数
            if ((nums[i] & divide) == divide) {
                m ^= nums[i];
            } else {
                n ^= nums[i];
            }
        }
        return new int[]{m, n};
    }

    /**
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jian-zhi-offer-56-i-shu-zu-zhong-shu-zi-tykom/
     */
    public int[] singleNumbers1(int[] nums) {
        int x = 0, y = 0,
                n = 0, m = 1;
        for (int num : nums) {
            n ^= num;//全部数异或结果
        }
        while ((n & m) == 0) {
            m <<= 1;//m从1左移，直到碰到n的第一个1
        }
        for (int num : nums) {
            if ((num & m) == 0) x ^= num;
            else y ^= num;
        }
        return new int[]{x, y};
    }


    public static void main(String[] args) {
        int arr[] = {1, 2, 10, 4, 1, 4, 3, 3};
        int[] ints = new Solution().singleNumbers(arr);
        System.out.println(Arrays.toString(ints));

        System.out.println(2 ^ 1);
    }

}
