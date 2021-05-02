package com.swordoffer.offer29;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 */
public class PrintMatrix {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length==0) return new int[0];
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;//输出数组的下标
        int t = 0, b = matrix.length-1, l = 0, r = matrix[0].length-1;
        //循环每执行一轮即遍历矩阵一圈，顺时针从左上到右上到右下到左下再回到左上，然后进去内一圈循环
        while (true) {
            //顶层的矩阵一边从左到右一行
            for (int i=l; i<=r; i++) {
                res[index++] = matrix[t][i];
            }
            //下一轮循环 +1，判断大于底部了就是已经遍历完毕
            if (++t > b) {
                break;
            }

            //矩阵右边从上到下，由于前面已遍历一行，这里 i 从 t 开始
            for (int i=t; i<=b; i++) {
                res[index++] = matrix[i][r];
            }
            //这里是判断是否小于来自左边的 l 变量了
            if (--r < l) {
                break;
            }

            //矩阵底部从右边往左回来
            for (int i=r; i>=l; i--) {
                res[index++] = matrix[b][i];
            }
            if (--b < t) {
                break;
            }

            //矩阵左边从下往上回来
            for (int i=b; i>=t; i--) {
                res[index++] = matrix[i][l];
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int mat[][] = {{1,2,3},{4,5,6},{7,8,9}};
        int[] ints = new PrintMatrix().spiralOrder(mat);
        System.out.println(ints);
    }

}
