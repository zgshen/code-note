package com.zgshen.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {

    public static int[] sort(int[] arr) {
        //先遍历找到最大值
        int maxVal = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal)
                maxVal = arr[i];
        }

        //根据最大值确定统计数组长度
        int[] countArr = new int[maxVal + 1];
        //遍历填充
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }

        //根据统计数组生成排序数组
        int[] sortedArr = new int[arr.length];
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                sortedArr[index++] = i;
            }
        }
        return sortedArr;
    }

    /**
     * 优化版本
     * @param arr
     * @return
     */
    public static int[] sort1(int[] arr) {
        //先遍历找到最大值和最小值
        int maxVal = arr[0], minVal = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal)
                maxVal = arr[i];
            if (arr[i] < minVal)
                minVal = arr[i];
        }

        //遍历填充
        int[] countArr = new int[maxVal - minVal + 1];
        for (int i = 0; i < arr.length; i++) {
            //要减去偏移量
            countArr[arr[i] - minVal]++;
        }

        //改变统计数组，后面元素等于前面元素之和
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }

        //倒叙遍历原始数组，找到元素在统计数组的位置并设值
        int[] sortedArr = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            sortedArr[countArr[arr[i] - minVal] - 1] = arr[i];
            countArr[arr[i] - minVal]--;
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] ints = sort(arr);
        System.out.println(Arrays.toString(ints));

        int[] arr1 = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] ints1 = sort1(arr);
        System.out.println(Arrays.toString(ints1));
    }

}
