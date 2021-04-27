package com.swordoffer.offer13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *  
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 */
public class Moving {

    private int m;
    private int n;
    private int k;
    /**
     *
     * @param m 行
     * @param n 列
     * @param k 坐标数位和
     * @return
     */
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        if (m<=0 || n<=0 || k<0) return 0;

        boolean vis[][] = new boolean[m][n];//矩阵

        //return dfs(vis, 0, 0, 0, 0);
        return bfs(vis);
    }

    /**
     * 深度优先遍历
     * @param vis 矩阵
     * @param i 行
     * @param j 列
     * @param si 行坐标数位和
     * @param sj 列坐标数位和
     * @return
     */
    private int dfs(boolean vis[][], int i, int j, int si, int sj) {
        if (i>=m || j>=n || si+sj>k || vis[i][j]) return 0;
        vis[i][j] = true;//可达标记为true
        int sum = 1;//可达加1
        sum += dfs(vis, i, j+1, sum(i), sum(j+1)) + dfs(vis, i+1, j, sum(i+1), sum(j));//由于是从（0,0）左上开始，只需返回右边+下边的结果
        return sum;
    }

    /**
     * 广度优先遍历
     * @param vis
     * @return
     */
    private int bfs(boolean vis[][]) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();//平铺遍历，将下边和右边元素入队列，判断是否可达后出队列，直到矩阵遍历完毕
        queue.add(new int[]{0, 0, 0, 0});//起始位置（0,0）. 数组内部元素为行坐标，列坐标，行坐标数位和，列坐标数位和
        while (queue.size() > 0) {
            int arr[] = queue.poll();//出队列
            int i = arr[0], j = arr[1], si = arr[2], sj = arr[3];
            if (i>=m || j>=n || si+sj>k || vis[i][j]) continue;//出队列直到队列无元素

            vis[arr[0]][arr[1]] = true;
            sum++;

            queue.add(new int[]{i, j+1, sum(i), sum(j+1)});
            queue.add(new int[]{i+1, j, sum(i+1), sum(j)});
        }
        return sum;
    }

    private int sum(int x) {
        int sum = 0;
        while (x!=0) {
            sum += x%10;
            x = x/10;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println(new Moving().movingCount(1, 2, 20));
    }

}
