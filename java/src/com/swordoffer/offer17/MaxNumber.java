package com.swordoffer.offer17;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */
public class MaxNumber {

    /**
     * 不考虑大数，暴力循环输出
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int res[] = new int[end];
        for (int i = 0; i < end; i++) {
            res[i] = i+1;//从1开始的
        }
        return res;
    }


    /**
     * 考虑大数，只能使用字符串表示
     * @param n
     * @return
     */
    int n;
    int nine = 0, start;//9 个数，开始是 0 的个数
    StringBuilder res = new StringBuilder();
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String printNumbers1(int n) {
        this.n = n;
        num = new char[n];
        start = n - 1;//一开始就是最长位数减一
        dfs(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    private void dfs(int x) {
        //退出递归条件是构建完最高位
        if (x == n) {
            String str = String.valueOf(num).substring(start);//截取掉前面的 0
            if (!"0".equals(str)) res.append(str + ",");//要求从 1 开始，0 不要
            if (n - start == nine) start--;//
            return;
        }
        for (char c : loop) {
            if (c == '9') {
                nine++;
            }
            num[x] = c;// char 数组存放数字符号
            dfs(x+1);//从高位开始构造数字，到低位
        }
        nine--;
    }


    public static void main(String[] args) {
        /*int[] ints = new MaxNumber().printNumbers(1);
        System.out.println(Arrays.toString(ints));*/

        String s = new MaxNumber().printNumbers1(2);
        System.out.println(s);

        String s1 = String.valueOf(new char[]{'1', '2'});
        System.out.println(s1);
    }
}
