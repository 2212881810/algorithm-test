package qinfeng.zheng.date_20210828;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 18:02
 * @dec 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为逆序对，返回数组中所有的逆序对
 * <p>
 * <p>
 * 转换成：求右组中比左组arr[L]小的元素个数，与前面的求数组小和正好相反，
 * 数组小和是求： 右组中元素比左组中arr[L]大的元素个数
 */
public class A_03_归并排序_求数组中的逆序对 {


    public static int sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);

    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);

    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int R = right;
        int L = mid;
        int ans = 0;

        while (R >= mid + 1 && L >= left) {
            if (arr[L] > arr[R]) { // 左边大于右边，因为左右两边的数组中都是升序的的，
                // 所以当arr[L] > arr[R] 时，那么arr[L] 一定大于arr[mid+1 ... R]之间的所有元素
                ans += (R - mid);
            }
//            ans += arr[L] > arr[R] ? (R - mid) : 0;
            help[i--] = arr[L] > arr[R] ? arr[L--] : arr[R--]; // 倒着写的，其实也是升序
        }

        while (R >= mid + 1) {
            help[i--] = arr[R--];
        }

        while (L >= left) {
            help[i--] = arr[L--];
        }

        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4};
        System.out.println(sort(arr));
        System.out.println(Arrays.toString(arr));
    }
}
