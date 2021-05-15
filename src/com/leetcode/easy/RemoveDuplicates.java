package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * 前提排序好的数组
 * 删除重复元素，返回新数组长度
 */
public class RemoveDuplicates {

    public static int remove(int[] arrays) {
        if (arrays.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j=1; j<arrays.length; j++) {
            if (arrays[j] != arrays[i]) {
                i++;
                arrays[i] = arrays[j];
            }
        }
        return i+1;
    }


    public static void mai23n(String[] args) {
        int[] arr = {2, 3, 3, 10, 12, 18, 18};

        System.out.println(remove(arr));
    }


    public static void main(String[] args) {
        String c = "12";
        String d = "3";
        String cd = c+d;
        cd = cd.intern();

        String a = "123";

        String b = "123";
        /*String a = new String("123");
        String b = new String("123");


        boolean equals = a.equals(b);
        System.out.println(equals);*/

        System.out.println(a == cd);
        System.out.println(a == b);
    }

}
