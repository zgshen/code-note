package com.swordoffer.offer10_1;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 * 0 <= n <= 100
 *
 */
public class Fibonacci {

    /**
     * 太多重复计算，当 n 值比较大时变得很慢，深度过大会导致栈溢出
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }

    public int fib1(int n) {
        int res[] = {0, 1};
        if (n < 2) return res[n];

        int preOne = 1;//n-1
        int preTwo = 0;//n-2
        int fibRes = 0;
        for (int i=2; i<=n; ++i) {
            //fibRes = preOne + preTwo;//等于前两数之和
            fibRes = (preOne + preTwo) % 1000000007;//取模 1e9+7这个数，满足[0,1e9+7)内所有数，两个数相加不爆int，两个数相乘不爆long long

            preTwo = preOne;//新的n-2
            preOne = fibRes;//新的n-1，用于计算下个和
        }
        return fibRes;
    }

    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        System.out.println(new Fibonacci().fib(5));
        System.out.println((System.currentTimeMillis() - s1) + "ms");

        long s2 = System.currentTimeMillis();
        System.out.println(new Fibonacci().fib1(45));
        System.out.println((System.currentTimeMillis() - s2) + "ms");
    }

}
