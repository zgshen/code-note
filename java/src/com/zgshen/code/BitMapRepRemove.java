package com.zgshen.code;

import java.util.Arrays;

/**
 * @author nathan
 * @date 2020/9/12 1:28
 * @desc BitMapRepRemove
 * 去重。从0开始
 * https://www.jianshu.com/p/9e7f8f33a61a?from=singlemessage
 */
public class BitMapRepRemove {
    public static byte[] flags;

    public static void main(String[] args) {
        int[] array = {255, 1024, 1024, 0, 65536, 0, 1024, 8888, 9999, 1111, 8888};
        int length = 65536 + 1;
        flags = new byte[(int) (length >> 3) + ((length & 7) > 0 ? 1 : 0)];

        int index = 0;
        for(int num : array) {
            if( getFlags(num) != 1) {
                //未出现的元素
                array[index] = num;
                index = index + 1;
                //设置标志位
                setFlags(num);
            }
        }
        array = Arrays.copyOf(array, index);
        System.out.println(Arrays.toString(array));
        System.out.println(array.length);
    }

    public static void setFlags(int num) {
        int offset = num & (0x07);
        flags[num >> 3] |= 0x01 << offset;
    }

    public static int getFlags(int num) {
        int offset = num & (0x07);
        return flags[num >> 3] >> offset & 0x01;
    }
}
