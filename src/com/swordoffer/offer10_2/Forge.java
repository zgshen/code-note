package com.swordoffer.offer10_2;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 */
public class Forge {

    /**
     * 设青蛙跳 n 级台阶有 f(n) 种解法
     * 若青蛙第一次跳一级，台阶剩下 n-1 级，这种情况有 f(n-1) 种解法
     * 若青蛙第一次跳两级，台阶剩下 n-2 级，这种情况有 f(n-2) 种解法
     * 那么所有的情况就是 f(n) = f(n-1) + f(n-2)
     * 起始 n=0 时 f(0) = 1，规定
     *     n=1 时 f(1) = 1，只能跳一级
     *     n=2 时 f(2) = f(1) + f(0) = 2，可以跳一级两次或两级一次
     *     ...
     *     n 时 f(n) = f(n-1) + f(n-2)
     *
     * 题目算法和斐波那契数列一样，只是起始值不一样而已
     */
    public int numWays(int n) {
        int res[] = {1, 1};
        if (n < 2) return res[n];

        int preOne = 1;//n-1
        int preTwo = 1;//n-2
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
        System.out.println(new Forge().numWays(7));
    }

}
