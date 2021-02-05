package com.zgshen.code;

import java.util.Arrays;

/**
 * 小灰的算法之旅
 * 一个无序数组中，有若干个正整数，范围是1~100，其中有98个整数出现偶数次，只有两个出现了奇数次，找出这两个数
 */
public class FindLostNum {

    private static int[] solve(int[] array) {
        //用于存储两个出现奇数次的整数
        int[] res = new int[2];
        //第一次进行整体异或操作
        int xorRes = 0;
        for (int i=0; i<array.length; i++) {
            //异或操作
            xorRes ^= array[i];
        }
        //如果进行异或操作结果为零，说明输入的数组不服个要求
        if (xorRes == 0) {
            return null;
        }
        int sparator = 1;
        /**
         * 两个数异或必定在二进制存在不同
         * 从最低位开始，一直左移到第一个不同的位置
         * 可以把数组分为在某位上为 0 或 1 的两类数
         */
        while ((xorRes & sparator) == 0) {
            sparator <<= 1;
        }
        //分治，两个数分别在不同的两组数中
        for (int i=0; i<array.length; i++) {
            if ((array[i] & sparator) == 0) {
                res[0] ^= array[i];
            } else {
                res[1] ^= array[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 3, 2, 1, 6};
        int[] solve = solve(arr);
        System.out.println(Arrays.toString(solve));
    }

}
