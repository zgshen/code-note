package com.zgshen.code.tree;

import java.util.Arrays;

/**
 * 漫画算法：小灰算法之旅
 * 二叉堆
 * 数组存储
 * 假设父节点下标是 parent，那么它的左孩子下标就是 2× parent+1；右孩子下标就是 2× parent+2。
 */
public class BinaryHeap {

    /**
     * 插入操作，上浮调整
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;//插入节点下标，数组最后一个元素
        /**
         * 计算父节点下标
         * 若当前是左节点，父节点下标为（节点下标-1）/2
         * 若当前是右节点，减1为奇数，（节点下标-1）/2 向下取整结果也等于左节点的情况
         */
        int parentIndex = (childIndex - 1)/2;

        int temp = array[childIndex];//调整的数
        while (childIndex>0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1)/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     * 删除操作，最后一个元素替换一个元素再做下沉操作
     * @param array 源数组
     * @param parentIndex 要调整节点的父节点下标
     * @param length 数组长度
     */
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

    /**
     * 构建堆，下沉操作
     * @param array
     */
    public static void buildHeap(int[] array) {
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        System.out.println(3/2);

        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

    }

}
