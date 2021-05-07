package com.swordoffer.offer20;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 *
 * 部分数值列举如下：
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 *
 * 部分非数值列举如下：
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "    .1  "
 * 输出：true
 *  
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 */
public class Solution {


    /**
     * 考察状态机、编译原理的东西
     *
     * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/mian-shi-ti-20-biao-shi-shu-zhi-de-zi-fu-chuan-y-2/
     * 或
     * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/mian-shi-ti-20-biao-shi-shu-zhi-de-zi-fu-chuan-y-2/518386
     */

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        boolean isNum = false,        //遇到数字
                isDot = false,      //小数点
                is_e_or_E = false;  //e 或 E

        char[] chars = s.trim().toCharArray();//去掉首位空格再转字符数组

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                isNum = true;//判断是不是 0~9 的数字
            } else if (c == '.') {
                //如果是小数点，判断小数点只能有一个，小数点前面不能有 e 或 E
                if (isDot || is_e_or_E) {
                    return false;
                }
                isDot = true;//标记遇到小数点了
            } else if (c == 'e' || c == 'E') {
                if (!isNum || is_e_or_E) return false;//e 或 E 前面必须有数字，而且 e 或 E 不能重复出现
                is_e_or_E = true;//标记遇到 e 或 E
                isNum = false;//到这里 e 或 E 前面的数字情况已经判断完毕，isNum 重置为 false，用来判断 e 或 E 后面的字符
            } else if (c == '-' || c == '+') {
                //- 和 + 号只能出现在第一个字符位置 或者 e 或 E 前面，其他情况肯定是 false
                //这里 i!=0 成立才会判断后面的条件，所以不用担心 i-1 越界
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E')
                    return false;
            } else
                return false;//其他字符均为非法字符
        }
        return isNum;//核心就是判断串里的数字最后是否合法
    }


}
