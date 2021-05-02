package com.swordoffer.offer40;

import java.util.Arrays;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class LeastNumber {

    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length-1);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 快速排序
     * @return
     */
    private void quickSort(int arr[], int left, int right) {
        if (right <= left) return;
        //left 为基准
        int i=left, j=right;
        while (i < j) {
            while (i<j && arr[j]>=arr[left]) j--;//从右往左，得到第一个小于基准的元素的下标
            while (i<j && arr[i]<=arr[left]) i++;//从左往右，得到第一个大于基准的元素的下标
            swap(arr, i, j);//将两个值交换
        }
        swap(arr, left, i);//交换，将 i 作为新的基准，新基准将数组分为两部分继续递归
        quickSort(arr, left, i-1);
        quickSort(arr, i+1, right);
    }

    private void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int arr[] = {4,5,1,6,2,7,3,8};
        //int arr[] = {6,1,2,7,9};
        int[] leastNumbers = new LeastNumber().getLeastNumbers(arr, 3);
        System.out.println(Arrays.toString(leastNumbers));
    }
}
