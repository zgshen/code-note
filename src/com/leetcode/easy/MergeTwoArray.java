package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/8/10 23:04
 * @desc MergeTwoArray 合并两个数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 */
public class MergeTwoArray {

    public static void main(String[] args) {
        MergeTwoArray mergeTwoArray = new MergeTwoArray();
        int[] nums1 = {1,2,3,0,0,0}; int m = 3;
        int[] nums2 = {2,5,6}; int n = 3;
        mergeTwoArray.merge(nums1, m, nums2, n);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num_copy = nums1;//复制
        System.arraycopy(nums1, 0, num_copy, 0, m);

        int p1 = 0, p2 = 0;//两个数组起点
        int p = 0;//新数组初始位置
        while (p1<m && p2<n) {
            num_copy[p++] = (nums1[p1] < nums2[p2])? nums1[p1++] : nums2[p2++];
        }

        //p1比p2长，p1还剩有
        if (p1 < m) {
            System.arraycopy(nums1, p1, num_copy, p1+p2, m+n-p1-p2);
        }
        //p2比p1长，p2还剩有
        if (p2 < n) {
            System.arraycopy(nums2, p2, num_copy, p1+p2, m+n-p1-p2);
        }

        for (int value:
             num_copy) {
            System.out.print(value + ",");
        }
    }

    /**
     * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
     */
    public void merge_(int[] nums1, int m, int[] nums2, int n) {
        // 三指针 指针一p1、nums1有效元素尾部；指针二p2、nums2尾部；指针三p3、最终数组尾部
        // 1.当，p1>=0时，nums[p1],nums[p2]对比
        // 1.1 nums[p1]大，将nums[p1]放入p3位置。p1--,p3--
        // 1.2 nums[p2]大于等于nums[p1]，将nums[p2]放入p3位置。p2--,p3--
        // 2.当，p1<0时，将nums[p2]放入p3位置。p2--,p3--
        // 循环结束条件：p2<0

        int p1=m-1,p2=n-1,p3=m+n-1;
        while(p2 >= 0){
            if(p1 >= 0 && nums1[p1] > nums2[p2]){
                nums1[p3--] = nums1[p1--];
            } else {
                nums1[p3--] = nums2[p2--];
            }
        }
    }
}
