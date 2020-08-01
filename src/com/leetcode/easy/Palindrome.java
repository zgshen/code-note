package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/8/1 18:01
 * @desc Palindrome 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(-1));
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(5312135));
        System.out.println(isPalindrome(246642));
    }

    public static boolean isPalindrome(int x) {
        //小于0必定不是，最后一位是0也不是
        if (x < 0 || (x % 10 == 0 && x != 0 )) {
            return false;
        }
        int revNum = 0;
        //将后半部分翻转，如果等于前半部分则符合条件
        while (x > revNum) {
            revNum = revNum * 10 + x % 10;
            x /= 10;
        }
        /*if (revNum == x || revNum / 10 == x) {
            return true;
        } else return false;*/
        //如果位数是双数需要退一位比较
        return revNum == x || revNum / 10 == x;
    }

}
