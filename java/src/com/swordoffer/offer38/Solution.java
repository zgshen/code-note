package com.swordoffer.offer38;

import java.util.*;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * 限制：
 * 1 <= s 的长度 <= 8
 *
 * 参考：
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
 */
public class Solution {

    List<String> list = new ArrayList<>();
    char[] chars;

    public String[] permutation(String s) {
        chars = s.toCharArray();
        dfs(0);
        return list.toArray(new String[list.size()]);
    }

    private void dfs(int x) {
        if (x == chars.length -1 ) {
            list.add(String.valueOf(chars));
            return;
        }
        Set<Character> set = new HashSet<>();
        /**
         * 字符串从开头开始，每次跟本身每一位交换位置
         * 层层递归
         */
        for (int i = x; i < chars.length; i++) {
            if (set.contains(chars[i])) continue;

            set.add(chars[i]);
            swap(x, i);//交换
            dfs(x + 1);// x 从 0 开始每次加 1
            swap(x, i);//完毕恢复串顺序
        }
    }

    private void swap(int m, int n) {
        char tmp = chars[m];
        chars[m] = chars[n];
        chars[n] = tmp;
    }

}
