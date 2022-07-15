package com.swordoffer.offer40;

import java.util.Arrays;

/**
 * 优化版本
 */
public class LeastNumberPlus {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length-1);
    }

    /**
     * 快速排序
     * @return
     */
    private int[] quickSort(int arr[], int k, int left, int right) {
        int i=left, j=right;
        while (i < j) {
            while (i<j && arr[j]>=arr[left]) j--;//从右往左，得到第一个小于基准的元素的下标
            while (i<j && arr[i]<=arr[left]) i++;//从左往右，得到第一个大于基准的元素的下标
            swap(arr, i, j);//将两个值交换
        }
        swap(arr, left, i);//交换，将 i 作为新的基准，新基准将数组分为两部分
        if (k < i)//小于基准数，即 k+1（下标从0开始所以是 k+1）小的数字在左边数组中
            quickSort(arr, k, left, i-1);
        if (k > i)//大于基准数，即 k+1 小的数字在右边数组中
            quickSort(arr, k, i+1, right);
        return Arrays.copyOf(arr, k);//相等直接返回前 k 个数字
    }

    private void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
