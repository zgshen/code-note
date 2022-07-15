package com.swordoffer.offer50;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *  
 * 限制：
 * 0 <= s 的长度 <= 50000
 */
public class FirstAppearance {

    //丢人写法
   /* public char firstUniqChar(String s) {
        if (s == null) return ' ';
        char arr[] = s.toCharArray();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i=0; i<arr.length; i++) {
            String str = String.valueOf(arr[i]);
            Integer val = map.get(str);
            if (val == null)
                map.put(str, 1);
            else
                map.put(str, ++val);
        }
        System.out.println(map);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey().charAt(0);
            } else continue;
        }
        return ' ';
    }*/

    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char arr[] = s.toCharArray();
        for (char c : arr) {
            dic.put(c, !dic.containsKey(c));
        }

        //再遍历一次查第一个 true 的字符
        for (char c : arr) {
            if (dic.get(c))
                return c;
        }
        return ' ';
    }

    public char firstUniqueChar1(String s) {
        Map<Character, Boolean> dic = new LinkedHashMap<>();
        char arr[] = s.toCharArray();
        for (char c : arr) {
            dic.put(c, !dic.containsKey(c));
        }
        for (Map.Entry<Character, Boolean> entry : dic.entrySet()) {
            if (entry.getValue()) return entry.getKey();
        }
        return ' ';
    }

    public static void main(String[] args) {
        String str = "leetcode";
        System.out.println(new FirstAppearance().firstUniqChar(str));
    }
}
