package com.swordoffer.offer53.one;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class GetNumberOfK {

    public int search(int[] nums, int target) {
        int i=0, j=nums.length-1;
        //寻右边界
        while (i <= j) {
            int m = (i+j)/2;
            if (nums[m] <= target) i=m+1;//目标值比中间值还大，将左边界移到 i 上面
            else j=m-1;//中位数大于右边界
        }

        int right = i;
        if (j>=0 && nums[j]!=target) return 0;//右边界端点元素不等于目标值，则证明数组不包含目标值元素

        //右边界找到了，找左边界
        i=0;//重新从0开始
        //j=nums.length-1;//重新从尾端开始//其实没必要了
        while (i <= j) {
            int m = (i+j)/2;
            if (nums[m] < target) i=m+1;
            else j=m-1;
        }
        int left = j;
        return right - left -1;
    }

    public static void main(String[] args) {
        int arr[] = {1};
        int search = new GetNumberOfK().search(arr, 1);
        System.out.println(search);
    }



}
