package com.swordoffer.offer05;

/**
 * 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 */
public class ReplaceSpace {

    /**
     * 遍历写进新字符串
     */
    public String replaceSpace(String s) {
        s.replace(" ", "%20");
        StringBuilder sb = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == ' ')
                sb.append("%20");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 双指针从后往前复制替换
     * Java String 类不可变，换 StringBuffer 试试
     */
    public String replaceSpace1(StringBuffer s) {
        if (s == null || s.length() == 0)
            return s.toString();
        int p1 = s.length() - 1;
        int i = 0;
        while (i <= p1) {
            if (s.charAt(i) == ' ')
                s.append("  ");
            i++;
        }

        int p2 = s.length() - 1;
        while (p1 >=0 && p1 < p2) {
            char c = s.charAt(p1);
            p1--;

            if (c == ' ') {
                s.setCharAt(p2--, '0');
                s.setCharAt(p2--, '2');
                s.setCharAt(p2--, '%');
            } else
                s.setCharAt(p2--, c);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        //String str = "We are happy.";
        StringBuffer str = new StringBuffer("We are happy.");
        String s = new ReplaceSpace().replaceSpace1(str);
        System.out.println(s);
    }

}
