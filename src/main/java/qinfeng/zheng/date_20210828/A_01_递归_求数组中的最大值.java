package qinfeng.zheng.date_20210828;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 11:19
 * @dec
 */
public class A_01_递归_求数组中的最大值 {
    public static int getMaxValue(int[] arr) {
        int ans = process(arr, 0, arr.length - 1);
        return ans;
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {  // 数组中只有一个元素
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 54, 4, 7, 23, 56, 444, -1, 34, -54};
        System.out.println(getMaxValue(arr));
    }


}
