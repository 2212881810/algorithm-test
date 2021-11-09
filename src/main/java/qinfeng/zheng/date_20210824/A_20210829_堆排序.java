package qinfeng.zheng.date_20210824;

import java.util.Arrays;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/29 17:08
 * @dec 堆一定是完全二叉树，说到堆一定要说是大根堆还是小根堆
 */
public class A_20210829_堆排序 {

    /**
     * 插入新元素到堆时，往上比较交换
     * 大根堆 ！！！！
     * @param arr   ： 通过arr数组来构造堆结构
     * @param index : arr[index]是数组arr中刚来的数,还没有经过堆排序
     */
    public static void heapInsert(int[] arr, int index) {
        // 原则：子节点大于父节点,需要交换元素位置
        // arr[(index - 1) / 2] < arr[index] 有两层意思跳出循环：
        // 1. 当index == 0时， arr[0] < arr[0],不成立,跳出循环
        // 2. 正常的arr[父] >= arr[子]
        while (arr[(index - 1) / 2] < arr[index]) { // 如果父节点小于index这个子节点的值
            swap(arr, (index - 1) / 2, index);  // 将父节点和index子节点的元素进行交换
            index = (index - 1) / 2;   // 构造下一次循环的index, 也就是之前index的父节点位置
        }
    }

    /**
     * 删除大根堆中最大值(堆顶元素,index= 0) 之后，如何重新构造这个堆呢？
     * 将堆中最后一元素与index=0位置进行交换 ,同时heapSize减 1 ,然后调用heapify方法，就可以重新构造一个堆！！！
     * ===============
     * 该方法的作用： 原本是一个正常的大顶堆,但是其中index位置的元素发生变化,从而需要重新构造这个大顶堆
     *
     * @param arr      ： 通过arr来构造这个堆
     * @param index    ： 堆中发生变化的元素下标位置
     * @param heapSize ： 表示堆大小
     *                 <p>
     */
    // 从index位置,往下看,不断的下沉
    // 停： 孩子都比index小，或者是index无孩子
    public static void heapify(int[] arr, int index, int heapSize) {
        // 删除堆中的元素时，需要将它子节点中的元素往上移
        int left = index * 2 + 1;  // 左子节点在数组中的下标值
        int right = index * 2 + 2;  // 右子节点在数组中的下标值
        while (left < heapSize) {  //说明index有孩子
            // 1. 如果有右子节点，比较左右两个子节点的大小,记录较大的那个节点的下标
            int largest = (left + 1 < heapSize) && (arr[left + 1] > arr[left]) ? left + 1 : left;
            // 2. 再比较父节点index和largest的大小
            largest = arr[index] > arr[largest] ? index : largest;

            if (largest == index) { // 父节点比子节点大
                break;
            }
            // 3. 交换父节点与较大的那个子节点的位置
            swap(arr, index, largest);

            // 4. 构造下一次循环的位置
            index = largest;  // 新的父节点
            left = index * 2 + 1;  // 新的左子子节点
        }


    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 1. 将这个数组构造成一个大根堆的第一种方式，时间复杂度O(N * logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        // 1. 将这个数组构造成一个大根堆的第二种方式，时间复杂度O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }


        // 2. 排序(原理：经过heapInsert之后的arr是一个大根堆, 最大的元素是index == 0,将它移动到arr最后面 )
        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize); // 将数组中第一个元素和最后一个元素进行交换
            heapify(arr, 0, heapSize);  // 从index == 0开始往下沉,重构大根堆
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
        int[] arr = {6, 4, 5, 2, 1, 8};
        heapInsert(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {8, 4, 3, 2, 1, 5};  // 原本index=2位置的元素是6,现在改成了3,不再是一个大顶堆
        heapify(arr2, 2, 6);  // 通过heapify方法之后,再次成为了一个大顶堆
        System.out.println(Arrays.toString(arr2));

        // 这是一个大根堆，现在去掉最大值,然后重新构造堆
        int[] arr3 = {8, 4, 6, 2, 1, 5};
        swap(arr3, 0, arr3.length - 1);  //{5, 4, 6, 2, 1, 8}
        // 最后一个元素不看成是堆中元素了，所以heapSize == 5
        // 从 index  == 0 开始往下比较
        heapify(arr3, 0, 5);
        System.out.println(Arrays.toString(arr3));  // 前5个元素是一个大根堆,完美！！！！！！！！！


        System.out.println("=========测试堆排序=================");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr10 = generateRandomArray(maxSize, maxValue);
            int[] arr20 = copyArray(arr10);
            sort(arr10);
            comparator(arr20);
            if (!isEqual(arr10, arr20)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");


    }


}
