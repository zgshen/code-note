package com.swordoffer.offer65;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculate {

    public int add(int a, int b) {
        int sum, carry;
        while (b != 0) {
            sum = a ^ b;//异或，非进位和
            carry = (a & b) << 1;//与+左移，进位

            a = sum;
            b = carry;//进位给 b，下一轮判断 b 确定是否有进位
        }
        return a;
    }

    public static void main(String[] args) {
        int add = new Calculate().add(1, 2);
        System.out.println(add);
    }

}
