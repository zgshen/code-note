package com.swordoffer.offer61;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为 5 
 * 数组的数取值为 [0, 13] .
 */
public class Solution {

    public boolean isStraight(int[] nums) {
        //有没有重复数字
        //0 即小大王有几个
        //不连续的元素有几个
        //顺子尾首元素相减要小于数组长度
        Set<Integer> set = new HashSet<>();
        int max=0, min=14;
        for (int num : nums) {
            if (num == 0) continue;//大小王不管
            max = Math.max(max, num);
            min = Math.min(min, num);
            if (set.contains(num)) return false;//有重复的必然不是顺子，返回 false
            set.add(num);
        }
        return max-min < 5;//因为抽的是5张牌，最大-最小<5 即可构成顺子
    }


    private boolean isStraight1(int[] nums) {
        int joker = 0;
        Arrays.sort(nums);
        for (int i=0; i<4; i++) {
            if (nums[i] == 0)
                joker++;//大小王的数量
            else if (nums[i] == nums[i+1])
                return false;//有重复的返回 false
        }
        return nums[4] - nums[joker] < 5;//排序后的 nums[joker] 是数组最小值，不需要关心 joker 数量，最大-最小<5就行了
    }

}
