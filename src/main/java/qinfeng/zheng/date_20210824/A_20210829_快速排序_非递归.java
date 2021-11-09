package qinfeng.zheng.date_20210824;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/29 11:43
 * @dec
 */
public class A_20210829_快速排序_非递归 {
    private static class Op {
        int left;
        int right;

        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;

        // 在整个数组上进行一次荷兰国旗算法，然后将数组切成两部分
        int[] equalArea = netherLandsFlag(arr, left, right);

        Stack<Op> stack = new Stack<>();

        stack.push(new Op(0, equalArea[0] - 1));
        stack.push(new Op(equalArea[1] + 1, right));

        while (!stack.isEmpty()) {
            // 对每一部分再次进行荷兰国旗算法
            Op op = stack.pop();
            if (op.left < op.right) {
                equalArea = netherLandsFlag(arr, op.left, op.right);
                stack.push(new Op(op.left, equalArea[0] - 1));
                stack.push(new Op(equalArea[1] + 1, op.right));
            }
        }
    }


    public static int[] netherLandsFlag(int[] arr, int left, int right) {

        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }

        // 注意这里一定是left + 随机数，出过错,找半天！！！！！！！！！！！
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);

        int less = left - 1;
        int more = right;
        int cur = left;
        while (cur < more) {
            if (arr[cur] == arr[right]) {
                cur++;
            } else if (arr[cur] < arr[right]) {
                swap(arr, ++less, cur++);
            } else {
                swap(arr, cur, --more);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            sort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("arr1:" + Arrays.toString(arr1));
                System.out.println("arr2:" + Arrays.toString(arr2));
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }

}
