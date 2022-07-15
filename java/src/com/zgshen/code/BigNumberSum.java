package com.zgshen.code;

/**
 * 大数相加
 */
public class BigNumberSum {

    public static String sum(String bigNumA, String bigNumB) {
        int maxLength = bigNumA.length() > bigNumB.length() ? bigNumA.length() : bigNumB.length();
        int arrA[] = new int[maxLength + 1];
        int arrB[] = new int[maxLength + 1];

        //逆序存储
        for (int i=0; i<bigNumA.length(); i++) {
            arrA[i] = bigNumA.charAt(bigNumA.length() - 1 - i) - '0';//- '0' 变成 int 数
        }
        for (int i=0; i<bigNumB.length(); i++) {
            arrB[i] = bigNumB.charAt(bigNumB.length() - 1 - i) - '0';
        }

        int[] res = new int[maxLength + 1];
        //遍历，按位相加，注意处理进位
        for (int i=0; i<res.length; i++) {
            int tmp = res[i];
            tmp += arrA[i];
            tmp += arrB[i];
            if (tmp >= 10) {
                tmp = tmp - 10;
                res[i+1] = 1;
            }
            res[i] = tmp;
        }

        //逆序转回 String
        StringBuilder sb = new StringBuilder();
        //是否找到最高有效位，找到了就可以开始拼接
        boolean findFirst = false;
        for (int i=res.length - 1; i>=0; i--) {
            if (!findFirst) {
                if (res[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String sum = sum("1200000000", "230000000");
        System.out.println(sum);
    }

}
