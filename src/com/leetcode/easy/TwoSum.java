package com.leetcode.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author nathan
 * @date 2020/7/21 17:26
 * @desc TwoSum
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 */
public class TwoSum {

    public static void main(String[] args) {
        int nums[] = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.stream(ints).boxed().collect(Collectors.toList()));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashTable = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (hashTable.containsKey(target - nums[i])) {
                return new int[]{i, hashTable.get(target - nums[i])};
            } else {
                hashTable.put(nums[i], i);
            }
        }
        return new int[]{};
    }

}
