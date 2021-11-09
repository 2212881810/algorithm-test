package qinfeng.zheng.date_20210828;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 19:08
 * @dec 在一个数组中，对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class A_04_归并排序_求乘以2小于num的个数 {
    public static int sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);

        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int ans = 0;

        int p = mid + 1; // 右组的起始位置
        for (int i = left; i <= mid; i++) {
            // 比较[mid+1, p)，arr[mid+1 ... p] 肯定是递增的
            while (p <= right && arr[i] > arr[p] * 2) {
                p++; // 如果arr[p] *  2 < arr[i], 那么就再比较arr[p+1] 与 arr[i]的大小！！！！
            }
            ans += (p - (mid + 1));
        }


        // 后面就是归并排序的代码
        int[] help = new int[right - left + 1];
        int i = 0;
        int L = left;
        int R = mid + 1;


        while (L <= mid && R <= right) {
            help[i++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];
        }

        while (L <= mid) {
            help[i++] = arr[L++];
        }

        while (R <= right) {
            help[i++] = arr[R++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
        return ans;

    }


    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 2, 1};
        System.out.println(sort(arr));
        System.out.println(Arrays.toString(arr));
    }
}
