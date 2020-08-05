package com.zgshen.sort;

/**
 * @author nathan
 * @date 2020/8/4 23:37
 * @desc InsertionSort
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 20, 15};
        int[] ints = insertionSort(arr);
        for (int v: ints) {
            System.out.print(v + ",");
        }
        System.out.println();

        int[] intss = insertionSortWhile(arr);
        for (int v: intss) {
            System.out.print(v + ",");
        }

        int gap = 4;
        double floor = Math.floor(gap / 3);
        for (; gap > 0; gap = Double.valueOf(Math.floor(gap/3)).intValue()) {
            System.out.println(gap);
            System.out.println(Double.valueOf(Math.floor(gap/3)).intValue());
        }
    }

    /**
     * time: O(n^2)  两个循环n*n
     * space: O(1)   变量大小都不变为1
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            int value = arr[i];
            int index = i;
            for (int j=i-1; j>=0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];
                    //arr[j] = value;
                    index = j;
                } else break;
            }
            arr[index] = value;
        }
        return arr;
    }

    public static int[] insertionSortWhile(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            int value = arr[i];
            int index = i;
            while (index > 0 && arr[index-1] > value) {
                arr[index] = arr[index-1];
                index--;
            }
            arr[index] = value;
        }
        return arr;
    }
}
