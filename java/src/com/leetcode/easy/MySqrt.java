package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/8/7 22:37
 * @desc MySqrt 平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 */
public class MySqrt {

    public static void main(String[] args) {
        System.out.println(mySqrt(15));
        System.out.println(NewtonIteration(15));
    }

    public static int mySqrt(int num) {
        int start = 0, end = num;
        int ans = -1;
        while (start <= end) {
            // int mid = start + (end - start)/2
            int mid = (start + end) / 2;
            if ((long)mid * mid <= num) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    //牛顿迭代
    public static int NewtonIteration(int num) {
        if (num == 0) {
            return 0;
        }
        double C = num, x0 = num;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }

}
