package com.swordoffer.offer42;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class Solution {

    /**
     * 由于要求是子数组，所以可以遍历数组，计算以每个元素结尾的子数组和，用一个变量 res 来存储子数组和的最大值
     * 判断当前元素的前一个元素值是大于还是小于0，肯定是大于等于0才不会让求和小于当前元素值，这时才加上，并把和存在当前元素上
     * 并判断是否大于 res，大于的话就替换 res 值为当前元素值（和的值）
     * 由于数组从头遍历下来，以前一个元素为结尾的数组和我们已经计算过了，直接判断它是否大于等于0要不要加上就行，再把和存在当前元素，与 res 比较，如此重复
     * 最终遍历完数组 res 就是子数组和的最大值
     * 运用了动态规划的思想
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > 0)
                nums[i] += nums[i-1];

            if (res < nums[i])
                res = nums[i];
        }
        return res;
    }

}
