package com.zgshen.sort;

/**
 * @author nathan
 * @date 2020/8/5 9:59
 * @desc QuickSort 快速排序的两种算法
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = {5, 6, 1, 20, 15};
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        /*int[] ints = quickSort(arr, 0, arr.length-1);
        for (int v: ints) {
            System.out.print(v + ",");
        }
        System.out.println();*/

        int[] intss = quickSortTwoWay(arr, 0, arr.length-1);
        for (int v: intss) {
            System.out.print(v + ",");
        }

    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left, right);//基准值
            quickSort(arr, left, partition-1);
            quickSort(arr, partition+1, right);
        }
        return arr;
    }

    // 分区操作，单边操作
    public static int partition(int[] arr, int left, int right) {
        int pivot = left;// 设定基准值（pivot）
        int index = pivot + 1;
        //从第二个开始查找
        for (int i = index; i <= right; i++) {
            //当前值小于基准值
            if (arr[i] < arr[pivot]) {
                //交换当前值和开始值
                swap(arr, i, index);
                index++;
            }
        }
        //最后将基准值和
        swap(arr, pivot, index - 1);
        return index-1;//基准值目前位置
    }

    //交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static int[] quickSortTwoWay(int[] arr, int left, int right) {
        //第一种方式
        /*if (left < right) {
            int partition = partitionTwoWay(arr, left, right);//基准值
            quickSortTwoWay(arr, left, partition-1);
            quickSortTwoWay(arr, partition+1, right);
        }*/
        //int i, j, t, temp;
        if (left < right) {
          int pivot = arr[left];
          int temp;
          int low = left;
          int hight = right;
          while (low != hight) {
              while (low<hight && arr[hight]>pivot) {
                  hight--;
              }
              while (low<hight && arr[low]<pivot) {
                  low++;
              }
              if (low < hight && arr[low]==arr[hight]) {
              } else {
                  temp = arr[low];
                  arr[low] = arr[hight];
                  arr[hight] = temp;
              }
          }
          //arr[left] = arr[low];
          //arr[low] = pivot;
          //左右部分继续遍历
          quickSortTwoWay(arr, left, low-1);
          quickSortTwoWay(arr, low+1, right);
        }
        return arr;
    }

    // 分区操作，双边操作
    public static int partitionTwoWay(int[] arr, int left, int right) {
        int pivot = arr[left];// 设定基准值（pivot）
        while (left < right) {
            while (left<right && arr[right]>pivot) {
                right--;
            }
            arr[left] = arr[right];//基准位置临时存小值
            while (left<right && arr[left]<=pivot) {
                left++;
            }
            arr[right] = arr[left];//右边存大值
        }
        arr[left] = pivot;//基准值移到中间
        return left;
    }
}
