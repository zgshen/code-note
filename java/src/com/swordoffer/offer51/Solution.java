package com.swordoffer.offer51;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *  
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class Solution {

    int nums[], tmp[];

    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];//用于合并
        return mergeSort(0, nums.length - 1);
    }

    /**
     * 归并操作
     * Hard 难度题目
     * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/jian-zhi-offer-51-shu-zu-zhong-de-ni-xu-pvn2h/
     * @return
     */
    private int mergeSort(int left, int right) {
        if (left >= right) return 0;
        int m = (left + right) / 2;//中间位置
        //递归分解
        int res = mergeSort(left, m) + mergeSort(m + 1, right);

        //合并分割数组
        int i = left, j = m + 1;// i, j 为两个数组的起始元素下标
        for (int k = left; k <= right; k++) {
            tmp[k] = nums[k];//先复制到 tmp 数组，tmp 为合并数组，每合并一层，tmp 子数组就排好一次序
        }
        //合并两个数组
        for (int k = left; k <= right; k++) {
            //左数组已经遍历完，跳 else 将右数组剩余元素入 tmp 数组
            if (i == m + 1) {
                nums[k] = tmp[j++];
                //j++;
            }
            //右数组已经遍历完，或者右数组元素大于等于左数组元素
            else if (j == right + 1 || tmp[i] <= tmp[j]) {
                nums[k] = tmp[i++];
                //i++;
            } else {
                //左数组元素大于右数组元素，构成逆序对
                nums[k] = tmp[j++];
                //j++;
                res += m - i + 1; // 统计逆序对
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //int arr[] = {7,5,6,4};
        int arr[] = {7, 5};
        System.out.println(new Solution().reversePairs(arr));
    }

}
