package com.swordoffer.offer15;

/**
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 *
 * 示例 3：
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 * 提示：
 * 输入必须是长度为 32 的 二进制串 。
 */
public class NumberOfOne {

    // you need to treat n as an unsigned value
    /*public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            //if ((n&1) == 1)
              //  count++;
            count += n&1;
            n >>>= 1;//无符号右移
        }
        return count;
    }*/

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n-1;//n = n&(n-1) 每次可以消去右边一个“1”
        }
        return count;
    }

}
