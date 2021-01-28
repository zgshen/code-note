package com.zgshen.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {16, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //构建最大堆
        for (int i = (arr.length-2)/2; i >= 0; i--) {
            downAdjust(arr, i, arr.length);
        }

        for (int i=arr.length-1; i>=0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            downAdjust(arr, 0, i);
        }
    }

    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;//左节点
        //判断是否属于数组
        while (childIndex < length) {
            //判断右节点是否小于左节点，成立定位到右节点
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex ++;
            }
            //父节点小于任意子节点，不必操作
            if (temp <= array[childIndex]) {
                break;
            }
            //父大于子，下沉
            array[parentIndex] = array[childIndex];
            //继续下一层遍历判断
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }

}
