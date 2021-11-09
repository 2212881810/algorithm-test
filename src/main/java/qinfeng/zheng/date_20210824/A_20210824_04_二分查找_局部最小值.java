package qinfeng.zheng.date_20210824;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/24 23:05
 * @dec 不是有序的数组，但是相临两个数不相等
 * <p>
 * 这个题的思路也是很有意思的， 该背就背吧
 */
public class A_20210824_04_二分查找_局部最小值 {


    // 求局部最小值的index
    public static int lessValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // 不存在
        }

        if (arr.length == 1) {
            return 0;
        }

        // 最左边的元素就是一个局部最小值
        if (arr[0] < arr[1]) {
            return 0;
        }

        // 最右边的元素就是一个局部最小值
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        // 如果代码走到这里，那么一定是arr[0] > arr[1],  arr[N-1]  < arr[N]
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);

            // mid大于右边，那么局部最小值右边一定有一个
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;

                // mid大于左边，那么局部最小值左边一定有一个
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;

            } else { // 既不大于左边，也不大于右边，那mid本身就是一个局部最小值
                return mid;
            }
        }

        return left;

    }
}
