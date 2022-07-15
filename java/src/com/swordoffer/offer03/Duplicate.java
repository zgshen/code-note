package com.swordoffer.offer03;

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 */
public class Duplicate {

    /*public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            }
            map.put(num, 1);
        }
        return -1;
    }

    public int feindRepeatNumber(int[] nums) {
        Set<Integer> col = new HashSet<>();
        for (int num : nums) {
            if (col.contains(num))
                return num;
            col.add(num);
        }
        return -1;
    }*/

    /**
     * 原地交换
     * 因为数组长度 n，数字范围 0～n-1，在 n 之内，遍历数组将元素与值对应下标中的元素交换，如果对应下标中已有相应值元素，则重复
     */
    public Integer findRepeatNumber(int[] nums) {
        int i = 0;//从头遍历
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            int val = nums[i];
            if (nums[val] == val)
                return val;
            nums[i] = nums[val];
            nums[val] = val;
            //i++; 原地交换就能遍历元素直到有重复的
        }
        return -1;
    }

    public static void main(String[] args) {
        //int arr[] = {2, 3, 1, 0, 2, 5, 3};
        int arr[] = {0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int repeatNumber = new Duplicate().findRepeatNumber(arr);
        System.out.println(repeatNumber);
    }

}
