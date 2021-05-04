package com.swordoffer.offer57.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 * 限制：
 * 1 <= target <= 10^5
 */
public class Solution {

    /**
     * 遍历求和方式
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int small=1, big=2;
        List<int[]> vec = new ArrayList<int[]>();
        while (small<big && big<=target-1) {
            int res = (small+big)*(big-small+1)/2;
            if (res < target) {
                big++;
                continue;
            }
            if (res > target) {
                small++;
                continue;
            }
            int seq[] = new int[big-small+1];
            int e = small;
            for (int i = 0; i < seq.length; i++) {
                seq[i] = e++;
            }
            vec.add(seq);
            big++;//继续
        }
        return vec.toArray(new int[vec.size()][]);
    }

    /**
     * 滑动窗口法
     * @param target
     * @return
     */
    public int[][] findContinuousSequence1(int target) {
        int i=1, j=2, s=3;//左边界、右边界、和
        List<int[]> vec = new ArrayList<int[]>();
        while (i < j) {
            if (s == target) {
                int ans[] = new int[j-i+1];
                for (int k = i; k < j; k++) {
                    ans[k-i] = k;
                }
                vec.add(ans);//构建数组放进集合
            }

            if (s > target) {
                s = s - i;//大于目标了就减去 i，然后 i 往右移位
                i++;
            } else {
                j++;//小了 j 就先移位再加
                s = s + j;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    public static void main(String[] args) {
        int[][] continuousSequence = new Solution().findContinuousSequence(9);
        for (int[] ints : continuousSequence) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
