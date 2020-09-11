package com.zgshen.code;

/**
 * @author nathan
 * @date 2020/9/11 12:49
 * @desc BitMap 位图实现，原理类似二位数组
 * 一个int占32位，每位的0、1用来表示这个数是否存在
 * 存储最大值为n的数，只需要申请一个int数组，长度为 int tmp[1 + n/32] 就可存储，即
 * tmp[0]：可表示0~31
 * tmp[1]：可表示32~63，如此推下去。
 *
 * 确定位置：给定任意整数M，那么M/32就得到数组下标，M%32就知道它在此下标当前int的哪个bit位位置
 */
public class BitMap {
    private long length;
    private static int[] bitsMap;

    public BitMap(long length) {
        this.length = length;
        bitsMap = new int[(int) (length>>5) + 1];//右移5位即除以32
    }

    public int getBit(long index) {
        int intData = bitsMap[(int) (index >> 5)];//int数组当前下标值
        int offset = (int) (index & 31);//偏移量，bit位置
        return intData >> offset & 0X01;//右移偏移量个单位，并且把高位全部去掉，就得到偏移量上的值
    }

    public void setBitOne(long index) {
        int arrIndex = (int) index >> 5;
        int offset = (int) index & 31;
        int intData = bitsMap[arrIndex];
        bitsMap[arrIndex] = intData | (1 << offset);//偏移量位置或操作置1
    }

    public void setBitZero(long index) {
        int arrIndex = (int) index >> 5;
        int offset = (int) index & 31;
        int intData = bitsMap[arrIndex];
        bitsMap[arrIndex] = intData & ~(1 << offset);//1移到偏移量位置后取反，再做与，偏移量位置0
    }

    private static String get32BitBinString(int number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1);
            number = number >>> 1;//无符号右移
        }
        return sBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(31);
        bitMap.setBitOne(31);
        bitMap.setBitOne(2);
        System.out.println(bitMap.getBit(1));
        System.out.println(get32BitBinString(bitsMap[31>>5]));
    }

}
