package com.zgshen.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 */
public class BucketSort {

    public static double[] sort(double[] arr) {
        double max = arr[0];
        double min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }

        double d = max -min;
        int bucketNum = arr.length;
        //初始化桶
        ArrayList<LinkedList<Double>> bucketLists = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketLists.add(new LinkedList<>());
        }

        //遍历原始数组，将元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            //计算所在桶下标
            int index = (int) ((arr[i] - min) * (bucketNum - 1) / d);
            bucketLists.get(index).add(arr[i]);
        }

        //桶内部排序
        for (int i = 0; i < bucketLists.size(); i++) {
            Collections.sort(bucketLists.get(i));
        }

        double[] sortedArr = new double[arr.length];
        int index = 0;
        for (LinkedList<Double> bucketList : bucketLists) {
            for (Double aDouble : bucketList) {
                sortedArr[index++] = aDouble;
            }
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        double[] arr = {6, 1.2, 2, 7.2, 9, 3, 7.6, 5, 10.5};
        double[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));
    }

}
