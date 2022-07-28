package qinfeng.zheng.date_20220726;

import java.util.Arrays;

/**
 * 时间复杂度只有 堆排序 和 归并排序 是 N*logN, 其它都是N^2, 不过快排有可能会是N*logN
 * 稳定性：只有插入、冒泡、归并是稳的！
 */
public class A_07_希尔排序 {

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    private static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap; j = j - gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }


    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
