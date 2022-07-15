package com.zgshen.sort;

/**
 * @author nathan
 * @date 2020/8/4 22:40
 * @desc SelectionSort 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 20, 15};
        int[] ints = selectionSort(arr);
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
    public static int[] selectionSort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            int index = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        return arr;
    }

}
