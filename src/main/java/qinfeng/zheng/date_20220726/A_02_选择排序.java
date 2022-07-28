package qinfeng.zheng.date_20220726;

import java.util.Arrays;

/**
 * 选择排序，找出最小元素放到1号位置，然后再找到次小的元素放到2号位置
 */
public class A_02_选择排序 {


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    swap(arr, j, min);
                }
            }

        }

    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
