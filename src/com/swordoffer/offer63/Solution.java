package com.swordoffer.offer63;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 限制：
 * 0 <= 数组长度 <= 10^5
 */
public class Solution {

    /**
     * 记最小值下标和最大利润，遍历一次就行了。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int cost = 0;
        int min = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[min];
            if (diff > cost) cost = diff;
            if (prices[i] < prices[min]) min = i;
        }
        return cost;
    }

    /**
     * 动态规划思想，与上面写法其实本质一样，只是更好看出 dp 逻辑
     * <p>
     * 前i日最大利润=max(前(i−1)日最大利润,第i日价格−前i日最低价格)
     * dp[i] = max(dp[i−1], prices[i]−min(prices[0:i]))
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            /*cost = price < cost ? price : cost;
            profit = price - cost > profit ? price - cost : profit;*/
            cost = Math.min(cost, price);
            profit = Math.max(profit, price-cost);
        }
        return profit;
    }

    public static void main(String[] args) {
        int arr[] = {7, 1, 5, 3, 6, 4};
        int i = new Solution().maxProfit(arr);
        System.out.println(i);
    }

}
