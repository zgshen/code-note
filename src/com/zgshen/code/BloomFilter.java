package com.zgshen.code;

import java.util.BitSet;

public class BloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;
    /**
     * 质数数组，7个不同质数构建7个不同算法
     */
    private static final int[] SEEDS = new int[] { 5, 7, 11, 13, 31, 37, 61 };
    /**
     * 创建长度为2^24的比特位
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] hashList = new SimpleHash[SEEDS.length];

    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            hashList[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(String value) {
        for (SimpleHash f : hashList) {
            //一个数据算出的8个hash值，对应放到10亿比特位上
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : hashList) {
            //查看7个比特位上的值
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    public static void main(String[] args) {
        BloomFilter bloomFileter = new BloomFilter();
        System.out.println(bloomFileter.contains("123"));
        bloomFileter.add("123");

        for (int i = 1 ; i < 10000000 ; i ++){
            bloomFileter.add(String.valueOf(i));
        }

        System.out.println(bloomFileter.contains("123"));
        int count = 0;
        for (int i = 10000000; i < 20000000; i++) {
            if (bloomFileter.contains(String.valueOf(i))) {
                count++;
            }
        }

        System.out.println("总共的误判数:" + count);
        System.out.println(DEFAULT_SIZE);
    }

}
