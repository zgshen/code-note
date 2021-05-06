package com.swordoffer.offer43;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 *
 * 限制：
 * 1 <= n < 2^31
 */
public class Solution {

    /**
     * 直接遍历的方法，超时间限制
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int num = 0;
        for (int i=n; i>0; i--) {
            String str = String.valueOf(i);
            if (str.contains("1")) {
                for (Character c : str.toCharArray()) {
                    if (c == '1') num++;
                }
            }
        }
        return num;
    }

    /**
     * 数学归纳，计算数字个位、十位、百位...上出现 1 的情况，然后全部 加起来就好了
     * 输入数字 n ，
     * 个位：出现的情况1，11，21...，1每隔10会出现1次，设对10余数 k = n%10，则个位出现1次数 count = n/10*1 + (if(k>1) 1 else if(k<1) 0 else k-1+1)
     *
     * 十位：出现的情况10，11，12...19，1每隔100会出现10次，设对100余数 k = n%100，则十位出现1次数 count = n/100*10 + (if(k>19) 10 else if(k<10) 0 else k-10+1)
     *
     * 百位：出现的情况100，101，102...199，1每隔1000会出现100次，设对1000余数 k = n%100，即个位出现1次数 count = n/1000*100 + (if(k>199) 1 else if(k<100) 0 else k-100+1)
     *
     * 设 i 为计算1所在的位数，i=1 表示计算个位1的个数，i=10 表示计算十位的个数，i=100 表示计算百位的个数....
     * 则 k = n%10i
     * 则 count(i) = n/10i*i + (if(k>2i-1) 1 else if(k<i) 0 else k-i+1)
     *             = n/10i*i + min( max(n%10i-i+1, 0), i) //保证 k-i+1 在 [0, i] 区间，max(n%10i-i+1, 0)表示要大于0，min 中表示要比 i 小
     * @param n
     * @return
     */

    public int countDigitOne1(int n) {
        if (n<0) return 0;
        int count = 0;
        for (long i = 1; i <= n; i*=10) {
            //i = 1, 10, 100...
            long digit = 10*i;
            count += n/digit*i + Math.min(Math.max(n%digit-i+1, 0), i);
        }
        return count;
    }


    /**
     * 以数字2304为例，若当前位为十位（base = 10），则当前位数0，高位数23，低位数4
     * 当前位等于0情况，则可理解有两部分组成0~2299和2300~2304；若固定当前位位1，则前半段组合为[0~22]1[0~9]，次数为230=23*10（high*base）；后半部组合十位只能取0，无法满足固定为1计算前提，次数为0
     * 同理，若数字为2314，当前位等于1情况，优先拆分为0~2299和2300~2314；若固定当前位位1，则前半段组合为[0~22]1[0~9]，次数为230=23*10（high*base）；后半部组合231[0~4]，次数为5（low+1）
     * 同理，若数字为2324，当前位大于1情况，优先拆分为0~2299和2300~2324；若固定当前位位1，则前半段组合为[0~22]1[0~9]，次数为230=23*10（high*base）；后半部组合231[0~9]，次数为10（base）
     * 将数据拆分为两部分理解，计数前提当前位可以拨动到1
     * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/791034
     * @param n
     * @return
     */
    public int countDigitOne2(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("10".contains(1+""));

        int i = new Solution().countDigitOne(12);
        System.out.println(i);
    }

}
