package qinfeng.zheng.date_20220726;

import java.util.Arrays;

public class A_05_快速排序 {


    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int random = (int) (Math.random() * (right - left + 1)) + left;
        swap(arr, random, right);

        int[] equal = netherLandFlag(arr, left, right);
        process(arr, left, equal[0] - 1);
        process(arr, equal[1] + 1, right);

    }

    private static int[] netherLandFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }

        int less = left - 1;
        int more = right;
        int cur = left;
        while (cur < more) {
            if (arr[cur] == arr[right]) {
                cur++;
            } else if (arr[cur] < arr[right]) {
                swap(arr, ++less, cur++);
            } else {
                swap(arr, --more, cur);
            }
        }

        swap(arr, more, right);

        return new int[]{less + 1, more};

    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
