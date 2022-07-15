package com.swordoffer.offer64;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 *
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 *
 * 限制：
 * 1 <= n <= 10000
 */
public class Sum {

    public int sumNums(int n) {
        boolean b = n>1 && (n += sumNums(n-1)) > 0;
        return n;
    }

    /**
     * 邪道异常方法，Java 异常捕获特性
     * @param n
     * @return
     */
    public int sumNumsExp(int n) {
        try{
            return 1 / n + n + sumNumsExp(n - 1);
        }catch(Exception e){
            return -1;//当 n=1 时 1/n=1，在 n=0 时异常顺便减掉
        }
    }

    public static void main(String[] args) {
        int i = new Sum().sumNumsExp(2);
        System.out.println(i);
    }

}
