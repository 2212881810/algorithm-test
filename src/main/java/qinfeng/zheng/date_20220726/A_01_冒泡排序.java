package qinfeng.zheng.date_20220726;

import java.util.Arrays;

/**
 * 冒泡的思想就是先将最大的值排到数组的最后一个位置； 然后再将第二大的值排到倒数据第二个位置~
 */
public class A_01_冒泡排序 {


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr,j ,j+1);
                }

            }

        }

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
