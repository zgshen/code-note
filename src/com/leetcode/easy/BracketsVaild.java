package com.leetcode.easy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author nathan
 * @date 2020/8/3 23:09
 * @desc BracketsVaild
 */
public class BracketsVaild {

    private static HashMap<Character, Character> mappings = new HashMap<>();

    static  {
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');
    }

    public static void main(String[] args) {
        System.out.println(bracketsVaild("{}{()}()"));
    }

    public static boolean bracketsVaild(String str) {
        Stack<Character> stack = new Stack<>();//创建栈
        for (int i=0; i< str.length(); i++) {
            char c = str.charAt(i);
            if (mappings.containsKey(c)) {
                if (stack.isEmpty()) return false;
                if (mappings.get(c) != stack.pop()) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
