package qinfeng.zheng.date_20210824;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/1 22:14
 * @dec 计数排序只能用于数据在一定的范围之内的数组，如 0 ~ 200, 100 ~ 500之类的
 *
 *
 * 桶排序思想下的排序：计数排序 & 基数排序
 *
 * 1)桶排序思想下的排序都是不基于比较的排序
 *
 * 2)时间复杂度为O(N)，额外空间负载度O(M)
 *
 * 3)应用范围有限，需要样本的数据状况满足桶的划分
 */
public class A_20210901_计数排序 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 1. 求数组中的最大值的数，
        int min = Integer.MIN_VALUE;

        int max = min;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        // 2. 创建桶，0 ~ max, 每个值都创建一个桶，所以需要创建max+1个桶
        int[] bucket = new int[max + 1];

        //比如arr[5] = 3 , 那么bucket[3]的值就累加1
        //如果arr[8] = 0 , 那么bucket[0]的值就累加1
        //如果arr[10] = 3, 那么bucket[3]的值就再次累加1
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        // 3. 排序打印
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            // 表示原数组中有bucket[i]个i
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 3, 4, 2, 1, 2, 3, 4, 5, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
