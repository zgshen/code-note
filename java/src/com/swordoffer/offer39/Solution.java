package com.swordoffer.offer39;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *  
 * 限制：
 * 1 <= 数组长度 <= 50000
 */
public class Solution {

    /**
     * 某个数字数量超过数组的一半，此数字与其他的全部数字做一一抵消，
     * 最后剩下的肯定还是此数字。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int num = nums[0], count=1;//num 保存数字，count 保存数字次数
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) count++;//数字相同加 1
            else if (count-1 == 0) {//不同的数需要拿次数做抵消，直到没了为 0
                num = nums[i];//为 0 时要更换数字。最后遍历完就是数量超过一半的那个数
                count = 1;
            }
        }
        return num;
        /*
        //题目假设数组是非空的，并且给定的数组总是存在多数元素。如果不是的话，加上下面判断
        int total = 0;
        for (int n : nums) {
            if (num == n) total++;
        }
        return total > nums.length/2 ? num : 0;//最后验证那个数字数量没超过数组的一半的话，就返回 0
        */
    }

    //和上面差不多
    //https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solution/mian-shi-ti-39-shu-zu-zhong-chu-xian-ci-shu-chao-3/
    public int majorityElement1(int[] nums) {
        int x = 0, votes = 0, count = 0;
        for(int num : nums){
            if(votes == 0) x = num;
            //votes += num == x ? 1 : -1;
            //votes = votes + num;
            if (num == x)
                votes = votes + 1;
            else
                votes = votes - 1;
        }
        // 验证 x 是否为众数
        for(int num : nums)
            if(num == x) count++;
        return count > nums.length / 2 ? x : 0; // 当无众数时返回 0
    }

    public static void main(String[] args) {
        int i = 1;
        if (--i == 0) {
            System.out.println(1);
        }
    }
}
