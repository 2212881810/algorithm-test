package qinfeng.zheng.date_20210828;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 17:13
 * @dec 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * <p>
 * 转换成计算右组比左组元素大的个数  *  该数
 */
public class A_02_归并排序_求数组小和 {


    public static int getMinSum(int[] arr) {

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
        return process(arr, left, mid) +
                process(arr, mid + 1, right) +
                merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int L = left;
        int R = mid + 1;
        int ans = 0; // 计录小和

        while (L <= mid && R <= right) {
            // 右组arr[R] 比左组的arr[L]大,那么右组中[R....right]的元素都会比左组的arr[L]元素大
            if (arr[L] < arr[R]) {
                ans += arr[L] * (right - R + 1);
            }
            help[i++] = arr[L] < arr[R] ? arr[L++] : arr[R++];
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


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
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

    public static int testMinValue(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    ans += arr[i];
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println("开始！");
        for (int i = 0; i < 100000; i++) {
            int[] arr1 = generateRandomArray(100, 100);
            int[] arr2 = copyArray(arr1);

            int value = getMinSum(arr1);
            int testValue = testMinValue(arr2);

            if (value != testValue) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("结束！");
    }
}
