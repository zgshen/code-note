package com.swordoffer.offer21;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 提示：
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 */
public class Solution {

    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            /*if (nums[i] % 2 == 1) i++;
            else if (nums[j] % 2 == 0) j--;*/
            //用位运算也可以
            if ((nums[i] & 1) == 1) i++;
            else if ((nums[j] & 1) == 0) j--;
            else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4};
        System.out.println(Arrays.toString(new Solution().exchange(arr)));
    }

}
