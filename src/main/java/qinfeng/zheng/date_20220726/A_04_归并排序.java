package qinfeng.zheng.date_20220726;

import java.util.Arrays;

/**
 * 归并排序的实现思路： 先将数组从中间切成两个小数组，然后再将两个小数组从中间切，切成4个小数组
 * 一直切，一直切，切到数组中只有一个元素，然后再将小数组进行排序合并！
 */
public class A_04_归并排序 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);

    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {

        int[] help = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int cur = 0;

        while (l <= mid && r <= right) {
            help[cur++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
        }

        while (l <= mid) {
            help[cur++] = arr[l++];
        }

        while (r <= right) {
            help[cur++] = arr[r++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
