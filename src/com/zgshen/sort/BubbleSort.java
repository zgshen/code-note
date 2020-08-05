package com.zgshen.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author nathan
 * @date 2020/8/4 20:27
 * @desc BubbleSort 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 20, 15};
        int[] ints = bubbleSort(arr);
        for (int v: ints) {
            System.out.print(v + ",");
        }
    }

    /**
     * time: O(n^2)  两个循环n*n
     * space: O(1)   变量大小都不变为1
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length-i-1; j++) {
                if (arr[j+1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

}
