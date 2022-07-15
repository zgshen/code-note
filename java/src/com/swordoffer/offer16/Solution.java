package com.swordoffer.offer16;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 */
public class Solution {

    /**
     * 计算 x^n
     * 二分法的思路
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long b = n;// b long 类型
        double res = 1.0;

        //负数情况，计算的就是 1/x 的 b 转整数次方
        if (b<0) {
            x = 1/x;
            b = -b;
        }

        while (b>0) {
            //奇数的时候要分一个 x 出来
            if ((b & 1) == 1)
                res = res * x;

            //每次 x^2 叠加，
            x = x * x;
            b = b>>1;
        }
        return res;
    }

    public static void main(String[] args) {
        double v = new Solution().myPow(3, 6);
        System.out.println(v);
        System.out.println(Math.pow(3, 6));
    }

}
