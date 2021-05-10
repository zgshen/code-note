package com.swordoffer.offer14.one;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 * 2 <= n <= 58
 */
public class Solution {

    /**
     * dp 思路
     * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/752454
     * 状态转移方程 dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
     * 减成 i 段，j为其中其中一段长度，剪了j之后不再剪，乘积为 j * (i - j)
     * 剪了j之后再剪，乘积为 j*dp[i-j]
     * 新的 dp[i] 等于两者的最大值
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n<3) return n-1;
        int dp[] = new int[n+1];
        dp[2] = 1;// 2 = 1 + 1
        //分为 i 段
        for (int i = 3; i <= n; i++) {
            //小于等于 i-2 是因为最后是 j*1 等于 j，没意义
            for (int j = 1; j <= i-2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }


    /**
     * 数学推论
     * ① 当所有绳段长度相等时，乘积最大。② 最优的绳段长度为 3
     * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
     * @param n
     * @return
     */
    public int cuttingRope1(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
    }

}
