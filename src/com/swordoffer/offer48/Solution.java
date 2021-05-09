package com.swordoffer.offer48;

import com.zgshen.code.http.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 * 提示：
 * s.length <= 40000
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap();//字符最近出现的位置，重复出现就覆盖
        int max = 0, tmp=0;// 最大长度， 包含当前元素最大长度
        for (int i = 0; i < s.length(); i++) {
            Integer j = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);

            int x = i - j;//与上一次出现位置的距离
            if (x > tmp) {
                tmp++;
            } else tmp = x;//包括等于了
            max = Math.max(max, tmp);

        }
        return max;
    }

    /**
     * 与上面只是方向不同罢了
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringDFS(String s) {
        int max = 0, tmp = 0;

        for (int j = 0; j < s.length(); j++) {
            int i = j-1;
            while (i>=0 && s.charAt(i)!=s.charAt(j))
                i--;//往前遍历，找到和当前相同字符所在位置

            if (j-i > tmp)
                tmp++;//大了加1
            else
                tmp = j-i;//小了替换
            max = Math.max(max, tmp);
        }
        return max;
    }

    /**
     * 双指针方式
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringDoublePoint(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, i = -1;
        for (int j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j)))
                i = Math.max(i, map.get(s.charAt(j)));//字符上次出现的地方是不是超过 i 了，反例输入 abba

            map.put(s.charAt(j), j);//存储或覆盖字符出现的位置
            max = Math.max(max, j-i);//比较下当前不重复窗口距离和历史最长长度
        }
        return max;
    }

    public static void main(String[] args) {
        int abba = new Solution().lengthOfLongestSubstringDoublePoint("abba");
        System.out.println(abba);
    }

}
