package com.swordoffer.offer11;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 */
public class MinElement {

    /**
     * 直接遍历
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 1) return numbers[0];
        for (int i=0; i<numbers.length-1; i++) {
            if (numbers[i+1] < numbers[i])
                return numbers[i+1];
        }
        return numbers[0];
    }

    /**
     * 二分法
     */
    public int minArray1(int[] numbers) {
        if (numbers.length == 1) return numbers[0];
        int start = 0;
        int end = numbers.length-1;
        int mid = start;

        while (numbers[start] >= numbers[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }

            mid = (start + end)/2;
            if (numbers[start]==numbers[mid] && numbers[start]==numbers[end]) {
                return order(numbers, start, end);
            }
            if (numbers[mid] >= numbers[start]) {
                start = mid;
            } else if (numbers[mid] <= numbers[end] ) {
                end = mid;
            }
        }
        return numbers[mid];
    }

    private int order(int[] numbers, int start, int end) {
        int res = numbers[start];
        for (int i=start+1; i<end; i++) {
            if (res > numbers[i])
                res = numbers[i];
        }
        return res;
    }


    public static void main(String[] args) {
        //int arr[] = {3,4,5,1,2};
        int arr[] = {10,1,10,10,10};
        System.out.println(new MinElement().minArray(arr));
        System.out.println(new MinElement().minArray1(arr));
    }
}
