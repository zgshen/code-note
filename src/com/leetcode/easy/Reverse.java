package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/7/21 19:21
 * @desc Reverse
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 */
public class Reverse {

    public static void main(String[] args) {
        int num = 1534236469;
        System.out.println(reverse(num));
    }

    public static int reverse(int x) {
        Long res = 0L;
        while (x != 0) {
            res =  res*10 + x%10;
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) return 0;
            x = x/10;
        }
        return res.intValue();
    }

    public static int reverse_official(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
