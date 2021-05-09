package com.swordoffer.offer60;

import java.util.Arrays;

/**
 *
 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 示例 1:
 输入: 1
 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]

 示例 2:
 输入: 2
 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

 限制：
 1 <= n <= 11
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/jian-zhi-offer-60-n-ge-tou-zi-de-dian-sh-z36d/
     * 概率 dp 题。没接触过挺难的
     * @param n
     * @return
     */
    public double[] dicesProbability_dep(int n) {
        double dp[] = new double[6];//初始 n 为 1 时候就只有6种结果
        Arrays.fill(dp, 1.0/6.0);
        //i从2开始，1已经初始化了
        for (int i = 2; i <= n; i++) {
            double tmp[] = new double[5*i + 1];
            // dp.length * 6，滑动累加
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j+k] = tmp[j+k] + dp[j]/6.0;//累加
                }
            }
            dp = tmp;// dp 引用置换为新数组
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().dicesProbability_dep(2)));
    }
}
