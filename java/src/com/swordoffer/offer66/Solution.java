package com.swordoffer.offer66;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 */
public class Solution {

    /**
     * 画矩阵可清晰分析出来
     * 本质也是 dp 的一种
     * https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        if (a.length == 0) return a;
        int b[] = new int[a.length];

        b[0] = 1;//0 乘与任何数都等于任何数
        int tmp = 1;

        //下三角
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i-1] * a[i];
        }

        //与上三角相乘
        for (int i = a.length-2; i >= 0; i--) {
            tmp *= b[i];
            b[i] *= tmp;//tmp 就是上三角积
        }

        return b;
    }

}
