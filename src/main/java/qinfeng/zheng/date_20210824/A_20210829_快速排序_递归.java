package qinfeng.zheng.date_20210824;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/29 10:57
 * @dec 快排时间复杂度O(N * logN)
 */
public class A_20210829_快速排序_递归 {


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);

    }


    public static void process(int[] arr, int left, int right) {

        //入参合法性的判断
        if (left >= right) {
            return;
        }

        // 选择一个随机的位置的元素，作为荷兰国旗问题的划分值
        int randomIndex = (int) (Math.random() * (right - left + 1)) + left;
        // 将数组中这个随机位置的元素移动到right位置,因为荷兰国旗问题默认将数组中最后一个元素作为划分值
        swap(arr, randomIndex, right);
        // 通过荷兰国旗算法，求出相等部分的位置,这部分的元素已经排序了
        int[] equalArea = netherLandsFlag(arr, left, right);
        // 递归处理小于部分的元素
        process(arr, left, equalArea[0] - 1);
        // 递归处理大于部分的元素
        process(arr, equalArea[1] + 1, right);
    }

    /**
     * 荷兰国旗问题
     *
     * @param arr   原数组
     * @param left  ：左边位置
     * @param right ： 右边位置
     * @return 等于部分的左右下标位置
     */
    public static int[] netherLandsFlag(int[] arr, int left, int right) {

        // 数据合法性的判断
        if (left > right) {
            return new int[]{-1, -1};
        }

        // 只有一个元素
        if (left == right) {
            return new int[]{left, right};
        }

        // 小于部分的起始位置, left位置的前一个位置
        int less = left - 1;
        // 大于部分的起始位置,right位置
        int more = right;
        // 当前开始比较的位置
        int cur = left;

        while (cur < more) {
            if (arr[cur] == arr[right]) {
                cur++;
            } else if (arr[cur] < arr[right]) {
                swap(arr, ++less, cur++);
            } else { // arr[cur] > arr[right]
                swap(arr, cur, --more);
            }
        }

        // 将划分元素arr[right]与大于部分的第一个元素进行位置交换
        swap(arr, more, right);
        // 返回中间相等部分的位置下标
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            sort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
