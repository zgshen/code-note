package com.zgshen.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    /**
     * 递归版本
     * @param arr
     */
    private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) return;
        //左边数组结尾
        int mid = ((end - start)>>1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid+1, end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);

        int k = start;
        /**
         * 合并两个数组，遍历比较两个数组，谁小谁放入排序数组并移一位，然后继续比较，直到其中一个数组遍历完毕为止
         * 明显遍历完毕的数组会有 start > end
         */
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        //左边数组未遍历完的情况，剩余元素直接合并入排序数组
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        //右边数组未遍历完的情况，剩余元素直接合并入排序数组
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }

        //回写出栈返回给上一层递归
        for (int i = start; i <= end; i++) {
            arr[i] = result[i];
        }
    }

    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len-1);
    }


    public static void mergesort1(int[] arr) {
        int[] orderedArr = new int[arr.length];
    }

    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
