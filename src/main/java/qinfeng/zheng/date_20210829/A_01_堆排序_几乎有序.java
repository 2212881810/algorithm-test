package qinfeng.zheng.date_20210829;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/29 18:35
 * @dec 已知一个几乎有序的数组。
 * 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class A_01_堆排序_几乎有序 {

    /**
     *
     *
     * @param arr 几乎有序的数组
     * @param k   ： 每个元素移动的距离,可以等于k, 所以放到堆中的元素是k+1
     */
    public static void sortLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 因为每个元素可以移动的距离不超过k, 所以最小元素的一定位于 0 ...K 之间的某个位置
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 1、将0 ... k-1 位置的元素移动到堆中
        int i = 0;
        for (; i <= Math.min(arr.length-1, k - 1); i++) { // 小于等于啊，bug找半天！！！！
            heap.add(arr[i]);  // 放到k个元素到堆中
        }

        // 2、将后面的元素一边添加到堆中，一边进行排序
        int index = 0; // arr排序的位置
        for (; i < arr.length; i++, index++) {
            heap.add(arr[i]);
            arr[index] = heap.poll();
        }

        // 3、如果堆中还有元素，直接转移到arr中
        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
        }


    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
