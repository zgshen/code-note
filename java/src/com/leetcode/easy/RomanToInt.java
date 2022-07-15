package com.leetcode.easy;

/**
 * @author nathan
 * @date 2020/8/1 18:18
 * @desc RomanToInt 罗马数字转整数
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 */
public class RomanToInt {

    public static void main(String[] args) {

        /*System.out.println(romanToInt("VI"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("MCM"));
        System.out.println(romanToInt("MC"));*/

        //System.out.println(romanToInt_copy("MCMXCIV"));
        System.out.println(romanToInt_copy("MCM"));
        //System.out.println(romanToInt_copy("MC"));

    }

    public static int romanToInt(String s) {
        int len = s.length();
        int res = 0;
        int pre = 0;
        for (int i=0; i<len; i++) {
            String lm = s.substring(i, i + 1);
            int toint = toint(lm);
            //System.out.println(toint + ":" + pre);
            if (res!=0 && toint>pre) {
                res = res + toint - pre*2;
            }  else res += toint;
            pre = toint;
        }
        return res;
    }

    public static int toint(String v) {
        switch (v) {
            case "I": return 1;
            case "V": return 5;
            case "X": return 10;
            case "L": return 50;
            case "C": return 100;
            case "D": return 500;
            case "M": return 1000;
            default: return 0;
        }
    }



    public static int romanToInt_copy(String s) {
        // 1000 100 1000 = 1900
        int sum = 0;
        int preNum = getValue(s.charAt(0));

        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        System.out.println(preNum);
        sum += preNum;
        return sum;
    }

    private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

}
