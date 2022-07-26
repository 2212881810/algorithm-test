package qinfeng.zheng.date_20220726;

import java.util.Arrays;

/**
 * 0~1 ： 位置有序
 * 0~2 ： 位置有序
 * ....
 * 0~N ： 位置有序
 */
public class A_03_插入排序 {

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 外层表示比较的次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 第1次保证： 0、1 有序
            // 第2次保证： 0、1、2有序
            // 第3次保证： 0、1、2、3有序
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
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
