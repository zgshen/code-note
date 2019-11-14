package com.zgshen.algorithm.linearlist;

public class removeDuplicates {
    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example, Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     * 已排序数组，去除重复的，返回去除之后的长度，不允许创建另一个数组
     * 两两比较，跳过重复的数
     */
    public static int solution(int nums[]) {
        if (nums.length == 0) return 0;
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index -1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int nums[] = {1, 5, 5, 5, 5, 7, 8, 23, 33};
        System.out.println(solution(nums));
    }
}
