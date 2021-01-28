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
        /*int[] arr = {5, 6, 1, 20, 15};
        int[] ints = bubbleSort(arr);*/

        /*int[] arr = {5, 6, 1, 15, 18, 20, 26};
        int[] ints = bubbleSortPlus(arr);*/

        int[] arr = {5, 6, 15, 18, 20, 26, 1};
        int[] ints = sort(arr);
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


    /**
     * 优化
     * 1、不到 i-1 轮数组已经排完序不需要继续
     * 2、后区间有序不需要每次再比较
     */
    public static int[] bubbleSortPlus(int[] arr) {
        //最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要到这里位置
        int sortBorder = arr.length - 1;
        for (int i=0; i<arr.length; i++) {
            //有序标记，每一轮的初始值都是 true
            boolean isSorted = true;
            for (int j=0; j<sortBorder; j++) {
                if (arr[j+1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    //有交换，不是有序
                    isSorted = false;
                    //变更为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) break;
        }
        return arr;
    }

    /**
     * 鸡尾酒排序
     * 第一轮从左到右，第二轮从有到左，一次循环
     */
    public static int[] sort(int[] arr) {
        int temp = 0;
        for (int i=0; i<arr.length/2; i++) {
            //有序标记，每一轮的初始值都是 true
            boolean isSorted = true;
            //奇数轮
            for (int j=i; j<arr.length-i-1; j++) {
                if (arr[j+1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    //有交换，不是有序
                    isSorted = false;
                }
            }
            if (isSorted) break;
            //偶数轮，重置 isSorted 为 true
            isSorted = true;
            for (int j=arr.length-i-1; j>i; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    //有交换，不是有序
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
        return arr;
    }

}
