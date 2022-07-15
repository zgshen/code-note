package com.zgshen.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 漫画算法：小灰的算法之旅
 */
public class QuickSortRe {

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        //int[] intss = quickSort(arr, 0, arr.length - 1);
        int[] intss = quickSortByStack(arr, 0, arr.length - 1);
        for (int v: intss) {
            System.out.print(v + ",");
        }

    }

    /**
     * 递归方式
     */
    public static int[] quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return arr;
        }
        //得到基准元素位置
        //int pivotIndex = partitionSingle(arr, startIndex, endIndex);
        int pivotIndex = partitionDouble(arr, startIndex, endIndex);
        //根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex-1);
        quickSort(arr, pivotIndex+1, endIndex);
        return arr;
    }

    // 单边操作
    public static int partitionSingle(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int mark = startIndex;
        for (int i = mark+1; i <= endIndex; i++) {
            if (pivot > arr[i]) {
                //表示多一个小于基准的数，移动一位并交换值
                mark++;
                swap(arr, mark, i);
            }
        }
        //遍历结束后 pivod 和 mark 值交换
        swap(arr, startIndex, mark);
        return mark;
    }

    /**
     * 分治，双边循环法
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int partitionDouble(int[] arr, int startIndex, int endIndex) {
        //随机取一个基准位置，这里用 startIndex
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            //必须先从右往左找小于基准的数（若从左到右先，最后 left 会比 piot 大，不合理）
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //从左往右找大于基准的数
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //交换
            if (left < right) {
                swap(arr, left, right);
            }
        }
        //遍历结束后 pivot 和 left 交换
        swap(arr, startIndex, left);
        return left;
    }

    //交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 栈方式
     */
    public static int[] quickSortByStack(int[] arr, int startIndex, int endIndex) {

        //用一个集合栈来替代递归的函数栈
        Stack<Map<String, Integer>> quickSortStack =  new Stack<>();
        //数列的起始下标，以哈希的形式入栈
        Map<String, Integer> rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        //循环结束时，栈为空
        while (!quickSortStack.isEmpty()) {
            //顶部出栈
            Map<String, Integer> param = quickSortStack.pop();
            //得到基准元素位置
            int pivotIndex = partitionSingle(arr, param.get("startIndex"), param.get("endIndex"));
            //根据基准元素，分成两部分，把每一部分的起始下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (param.get("endIndex") > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
        return arr;
    }

}
