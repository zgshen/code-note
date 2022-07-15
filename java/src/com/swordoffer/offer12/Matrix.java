package com.swordoffer.offer12;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"
 *  "A","B","C"
 *          "C"
 *      "D","E"
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *  
 * 提示：
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 */
public class Matrix {

    /**
     * 深度优先搜索（DFS）问题 + 剪枝
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                //遍历矩阵，从 words 第一个元素开始匹配
                if (dfs(board, words, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param board 矩阵
     * @param word 目标匹配字符串
     * @param i 行索引
     * @param j 列索引
     * @param k 当前目标字符在 word 中的索引
     * @return
     */
    private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i>=board.length || i<0 ||
            j>=board[0].length || j<0 ||
            board[i][j] != word[k]) {//开头就不匹配直接返回 false
            return false;
        }
        if (k == word.length - 1) return true;//最后一个已经匹配返回 true 回溯
        board[i][j] = '\0';//当前元素标记为空防止 board[i][j] != word[k] 判断再次匹配

        //往矩阵四个方向遍历  右边、左边、下边、上边
        boolean res = dfs(board, word, i, j+1, k+1) || dfs(board, word, i, j-1, k+1)
                || dfs(board, word, i+1, j, k+1) || dfs(board, word, i-1, j, k+1);
        board[i][j] = word[k];//遍历完还原
        return res;
    }

    public static void main(String[] args) {
        /*char matrix[][] = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };*/

        char matrix[][] = {
                {'a','a'},
        };

        //System.out.println(new Matrix().exist(matrix, "s"));
        System.out.println(new Matrix().exist(matrix, "aa"));
    }

}
