package qinfeng.zheng.date_20210825;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/25 22:21
 * @dec
 */
public class A_异或运算_01_交换两个数 {
    public static void main(String[] args) {
        //如何不用额外变量交换两个数
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        swap(arr, 1, 2);
        System.out.println(Arrays.toString(arr));
    }

    // i和j不能相等，否则出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
