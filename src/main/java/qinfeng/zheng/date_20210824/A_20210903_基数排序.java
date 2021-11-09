package qinfeng.zheng.date_20210824;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/3 22:35
 * @dec  基数排序只适合非负数
 */
public class A_20210903_基数排序 {


    // 求一个数组中最大元素的位数， 如234是个3位数，45是个2位数
    private static int maxBit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max > 0) {
            ans++;
            max /= 10;
        }
        return ans;
    }

    // 求一个数第d位的值,如234,第1位是4，第2位是3，第3位是2
    private static int getDigit(int num , int d) {
        int digit = (num / (int) Math.pow(10, d - 1)) % 10;
        return digit;
    }


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1, maxBit(arr));
    }
    //在arr[left ... right]位置上进行排序,且最大位数是maxBit
    public static void process(int[] arr ,int left ,int right ,int maxBit) {

        // 1. 创建一个辅助数组，用于存储排序过程的中间值
        int[] help = new int[right - left + 1];

        // 有多少位，就需要调换多少次, d==1表示个位，d==2表示拾位，d==3表示百位
        for (int d = 1; d <= maxBit; d++) {

            //1. 再创建一个辅助数组，用于存储0~9出现的次数
            // 出现一次0，count[0]++ ; 出现一次1,count[1]++; ....
            int[] count = new int[10];
            for (int i = left; i <= right; i++) {
                int bitValue = getDigit(arr[i], d);
                count[bitValue]++;
            }

            //2. 将count数组值进行累加
            // count[1] = count[1] + count[0]
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            //3. 按d位将arr[left ... right] 的元素放到help数组中，help中的元素按d位有序
            for (int i = right; i >= left ; i--) {
                int bitValue = getDigit(arr[i], d);
                help[count[bitValue] - 1] = arr[i];
                count[bitValue]--;
            }

            //4.将help中的元素换回到arr数组中
            for (int i = 0; i < help.length; i++) {
                arr[left + i] = help[i];
            }
        }
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            sort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        sort(arr);
        printArray(arr);

    }

}
