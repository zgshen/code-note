package com.swordoffer.offer46;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 231
 */
public class Solution {

    public int translateNum(int num) {
        String str = String.valueOf(num);
        int a = 1, b = 1; //f(0) f(1)
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) continue;

        }
        return 1;
    }

    /**
     * 跟青蛙跳台阶解法一样，知识多了个条件而已
     * 设 num 有 n 位，n 位的翻译方法有 f(n) 种
     * 若最后一位只能单独翻译，不能和前一个数字组合，则在最后一位确定条件下，f(n) = f(n-1)
     * 若最后一位能和前一个数字组合，即当前位数数字 10<=m<=25，用了两位，剩下 n-2 位，则 f(n) = f(n-2)
     * 那么 f(n) = f(n-1) + f(n-2)，能否和前一个数字组合全看当前数字和前一个数字来判断
     * @param num
     * @return
     */
    public int translateNum1(int num) {
        String s = String.valueOf(num);//转换成字符串，空间复杂度变为 O(n)
        int preOne = 1, preTwo = 1; // n-1, n-2
        //至少两位
        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);//
            int cur = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? preOne + preTwo : preOne;
            preTwo = preOne;
            preOne = cur;
        }
        return preOne;
    }

    /**
     * 不转字符串的做法，空间复杂度 O(1)
     * 不过遍历方向是从右往左
     * 但者题动态规划是对称的，从左还是从右没区别
     */
    public int translateNum2(int num) {
        int preOne = 1, preTwo = 1;
        int x, y = num % 10;//y为个位数字
        while (num != 0) {
            num /= 10;//去掉最低位
            x = num % 10;//前一位数字
            int tmp = 10*x + y;//组合成两位数去判断
            int c = (tmp>=10 && tmp<=25) ? preOne+preTwo : preOne;
            preTwo = preOne;
            preOne = c;
            y = x;//继续下一位的遍历
        }
        return preOne;
    }

    public static void main(String[] args) {
        int i = new Solution().translateNum1(12);
        System.out.println(i);
    }

}
