package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/8/3 18:42
 * @desc LongestCommonPrefix 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"re", "repaired"};
        System.out.println(longestCommonPrefix(strs));

        //String[] strs = {"return", "repaired", "admin"};
        //System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        int count = strs.length;
        for (int i=1; i<count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;//没有相同的了直接退出
            }
        }
        return prefix;
    }

    public static String longestCommonPrefix(String str1, String str2) {
        int len = str1.length()<str2.length()? str1.length() : str2.length();
        System.out.println(len);
        int index = 0;
        //当前位置小于(从0开始所以不能等于len)最短的字符串长度，并且字符相等
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
