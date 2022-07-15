package com.swordoffer.offer49;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 */
public class UglyNumber {

    /**
     * 暴力破解不满足时间限制
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int i = 1;
        int num = 0;
        while (num != n) {
            int m = i;
            while (m % 2 == 0) m/=2;
            while (m % 3 == 0) m/=3;
            while (m % 5 == 0) m/=5;
            if (m == 1) num++;
            if (num == n) return i;
            i++;
        }
        return num;
    }


    /**
     * 从第一个元素 1 开始推导比较容易理解
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        int a=0, b=0, c=0;//a b c 分别标记小于当前仇数的数字，因子是 2 3 5 的最大的数字的下标
        int dp[] = new int[n];
        dp[0] = 1;//第一个丑数就是 1，循环从下标 1 开始
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2;
            int n3 = dp[b] * 3;
            int n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int i = new UglyNumber().nthUglyNumber1(1356);
        System.out.println(i);
    }

}
