package com.swordoffer.offer56.two;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * 限制：
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 */
public class Solution {

    /**
     * 遍历统计方法
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        //可以用 map，也可以建个 32 位 int 数组存放
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (nums[i] != 0) {
                //每位二进制位数字加起来放到map中
                if ((nums[i] & 1) == 1) {
                    if (map.get(j)==null) map.put(j, 1);
                    else map.put(j, map.get(j)+1);
                }
                j++;
                nums[i] = nums[i] >> 1;
            }
        }
        int sigle = 0, n = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //每位对3取模结果就是单个出现的数字对应二进制位的数字，再还原成十进制就行了
            sigle += entry.getValue()%3 * Math.pow(2, entry.getKey());
        }
        return sigle;
    }

    /**
     * 效率较高，比较容易理解的做法
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/shu-ju-jie-gou-he-suan-fa-san-chong-jie-4b9se/
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        //最终的结果值
        int res = 0;
        //int类型有32位，统计每一位1的个数
        for (int i = 0; i < 32; i++) {
            //统计第i位中1的个数
            int oneCount = 0;
            for (int j = 0; j < nums.length; j++) {
                oneCount += (nums[j] >>> i) & 1;
            }
            //每一位对3取模，要么是0要么是1（只出现一次的数字的二进制位中在这一位）
            //如果1的个数不是3的倍数，说明那个只出现一次的数字的二进制位中在这一位是1
            if (oneCount % 3 == 1)
                res |= 1 << i;
        }
        return res;
    }


    /**
     * 状态机解法
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        //System.out.println();
        int arr[] = { 3,   72, 72, 72, 73, 73, 73, 86, 86, 86, 87, 87, 87};

        //Arrays.sort(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println(new Solution().singleNumber(arr));
    }

}
