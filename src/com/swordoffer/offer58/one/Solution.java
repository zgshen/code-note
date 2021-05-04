package com.swordoffer.offer58.one;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class Solution {

    //双指针
    public String reverseWords(String s) {
        s = s.trim();//先去掉首位空格
        int j = s.length()-1, i=j;//双指针。从右往左走

        StringBuilder sb = new StringBuilder();
        //遍历
        while (i >= 0) {
            while (i>=0 && s.charAt(i)!=' ') i--;//找到空格
            sb.append(s.substring(i, j+1) + " ");

            while (i>=0 && s.charAt(i)==' ') i--;//接下来可能还有空格的话 i 继续走
            j = i;//让 i j 相等继续找下个单词
        }
        return sb.toString();
    }

    //分割函数
    public String reverseWords1(String s) {
        if (s==null || s.length()==0) return s;
        String str[] = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = str.length-1; i >= 0; i--) {
            if (str[i].equals("")) continue;
            sb.append(str[i] + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        //String the_sky_is_blue = new Solution().reverseWords("the sky is blue");
        //System.out.println(the_sky_is_blue);

        System.out.println("1234".substring(0, 2));
    }
}
