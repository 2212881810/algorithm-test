package qinfeng.zheng.date_20220726;

import java.util.Arrays;

public class A_06_堆排序 {

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 大根堆，那么父大于子
     *
     * @param arr
     * @param index
     */
    // 往上比较，子比父大，就交换
    private static void heapInsert(int[] arr, int index) {
       while (arr[(index-1)/2] < arr[index]){
           swap(arr,(index-1)/2,index);
           index = (index - 1) / 2;
       }
    }

    // 从index(父)位置往下比较，如果左、右子树比父大，则进行交换
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int longest = (left + 1 < heapSize) && arr[left + 1] > arr[left] ? left + 1 : left;
            longest = arr[index] > arr[longest] ? index : longest;
            if (longest == index) {
                break;
            }
            swap(arr, index, longest);

            // 下一次循环
            index = longest;
            left = index * 2 + 1;
        }
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 构造大根堆,所以第0号位置是最大的元素
        // 注意循环条件中的等于0
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //排序
        int heapSize = arr.length;
        while (heapSize > 0) {
            // 把最大的元素移到数据最后面的位置
            swap(arr, 0, --heapSize);

            //最后面的元素移到0号位置，然后重新构造一个大根堆，但是要注意此时的大根堆的size(已经排除了上一次循环的最大元素！！！！)
            heapify(arr, 0, heapSize);
        }

    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 8, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
