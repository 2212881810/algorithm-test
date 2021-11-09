package qinfeng.zheng.date_20210828;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 21:45
 * @dec 荷兰国旗问题
 * <p>
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * <p>
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * <p>
 * <p>
 * <p>
 * 假设以num进行划分,那么整个数组可以分为3部分：
 * 1、 小于num的部分， less
 * 2、 大于num的部分， more
 * 3、 等于num的部分， less + 1 , more
 */
public class A_05_荷兰国旗问题 {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 在数组arr[L....R]上做荷兰国旗问题，以arr[R]为划分值
    // 返回的是相等部分的下标值
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        // 参数合法性判断
        if (L > R) {
            // 非法的
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // 小于部分位置
        int more = R;  // 大于部分位置
        int cur = L;  // 当前要比较的位置
        while (cur < more) {
            if (arr[cur] == arr[R]) {
                cur++;
            } else if (arr[cur] < arr[R]) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > arr[R]) {
                swap(arr, cur, --more);
            }
        }

        // 将arr[R]与大于部分的第一个元素进行交换！！！
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    // 在整个数组arr上做荷兰国旗问题，以compareValue为划分值
    //荷兰国旗问题，会将数组中的数据分成大于compareValue，等于compareValue,小于compareValue 3部分
    public static void dutchFlagArea(int[] arr, int compareValue) {
        int less = -1;  // 小于部分的起始位置
        int more = arr.length;  // 大于部分的起始位置
        int cur = 0;  // 当前位置
        while (cur < more) { // 当前位置等于大于部分的起始位置时,结束比较
            if (arr[cur] == compareValue) {
                cur++;  // 如果当前元素等于目标值, 移动下一个元素进行比较
            } else if (arr[cur] < compareValue) {
                //干了3件事
                // 1. 小于部分的起始位置往后移一位
                // 2. 将当前位置的元素与小于部分起始位置后一位的元素进行交换
                // 3. 当前位置往后移一位
//                swap(arr, less + 1, cur);
//                less++;
//                cur++;


                swap(arr, ++less, cur++);
            } else if (arr[cur] > compareValue) {
//                swap(arr, cur, more - 1);
//                more--;
                swap(arr, cur, --more);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 2, 8, 3, 3, 4, 8, 6, 4};
//        dutchFlagArea(arr, 2);
//        System.out.println(Arrays.toString(arr));

        int[] equalArea = netherlandsFlag(arr, 0, 3);

        System.out.println(Arrays.toString(equalArea));
        System.out.println(Arrays.toString(arr));
    }
}
