package qinfeng.zheng.date_20210824;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 16:27
 * @dec
 */
public class A_20210828_归并排序_非递归 {


    public static void mergesort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int mergeStep = 1;
        int N = arr.length;
        while (mergeStep < N) {
            int L = 0;  // 当前左组的第1个位置
            while (L < N) {
                if (mergeStep > N - L) {
                    break;
                }
                // 1.计算mid位置和R位置
                int mid = L + mergeStep - 1;
                // R的位置等于mid位置+步长，但是数组余下的元素有可能不够步长的个数，所以取较小值
                int R = mid + Math.min(mergeStep, N - mid - 1);
                merge(arr, L, mid, R); // merge之后 arr[L...R]上有序
                // 2.构造下一次循环的L位置
                L = R + 1;
            }

            // 防止溢出
            if (mergeStep > N >> 1) {
                break;
            }

            // 步长乘以2
            mergeStep <<= 1;

        }

    }


    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int L = left;
        int R = mid + 1;
        int index = 0;

        while (L <= mid && R <= right) {
            help[index++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];
        }

        while (L <= mid) {
            help[index++] = arr[L++];
        }

        while (R <= right) {
            help[index++] = arr[R++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            mergesort(arr1);
            Arrays.sort(arr2);  // jdk提供的排序方法
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

        int[] arr = {4, 8, 2, 3, 7, 5, 4, 1, 6, 3};
        mergesort(arr);
        System.out.println(Arrays.toString(arr));


    }
}
