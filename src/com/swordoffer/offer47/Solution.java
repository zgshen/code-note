package com.swordoffer.offer47;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 */
public class Solution {

    /**
     * 动态规划的核心是 分析各种情况，然后写出状态方程；若不想用数组存储每一次计算的和的值，可用替换原元素的值的方法，但会改变输入变量的值
     * 此题的状态方程是
     * dp(i,j)=
     * grid(i,j)                            ,i=0,j=0
     * grid(i,j)+dp(i,j−1)                  ,i=0,j≠0
     * grid(i,j)+dp(i−1,j)                  ,i≠0,j=0
     * grid(i,j)+max[dp(i−1,j),dp(i,j−1)]   ,i≠0,j≠0
     * ​
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int row = grid.length-1;
        int col = grid[0].length-1;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (i==0 && j==0) continue;
                else if (i==0) grid[i][j] += grid[i][j-1];
                else if (j==0) grid[i][j] += grid[i-1][j];
                else grid[i][j] += Math.max(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[row][col];
    }


    /**
     * 减少判断的做法，先计算 i=0 和 j=0 格子，即第一行和第一列
     * @param grid
     * @return
     */
    public int maxValue1(int[][] grid) {
        int row = grid.length-1;
        int col = grid[0].length-1;
        //第一行
        for (int j = 1; j <= col; j++) {
            grid[0][j] += grid[0][j-1];
        }
        //第一列
        for (int i = 1; i <= row; i++) {
            grid[i][0] += grid[i-1][0];
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                grid[i][j] += Math.max(grid[i][j-1], grid[i-1][j]);
            }
        }
        return grid[row][col];
    }


}
