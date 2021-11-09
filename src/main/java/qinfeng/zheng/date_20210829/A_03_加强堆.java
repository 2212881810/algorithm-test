package qinfeng.zheng.date_20210829;

import java.util.*;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/30 22:10
 * @dec 系统提供的堆无法做到的事情：
 * 1）已经入堆的元素，如果参与排序的指标方法变化，
 * 系统提供的堆无法做到时间复杂度O(logN)调整！都是O(N)的调整！
 * 2）系统提供的堆只能弹出堆顶，做不到自由删除任何一个堆中的元素，
 * 或者说，无法在时间复杂度O(logN)内完成！一定会高于O(logN)
 * 根本原因：无反向索引表
 */
public class A_03_加强堆 {

    // 加强堆
    public static class HeapEnhancer<T> {
        // 通过动态数组List来实现堆元素的存储
        private List<T> heap;
        // 反向索引表，维护了每个元素在动态数组中的位置,操作元素时间复杂度O(1)
        private Map<T, Integer> indexMap;
        // 堆大小
        private Integer heapSize;
        // 比较器
        private Comparator<? super T> comparator;

        public HeapEnhancer(Comparator<T> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.heapSize = 0;
            this.comparator = comparator;
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }

        public int size() {
            return this.heapSize;
        }

        public boolean contains(T obj) {
            return this.indexMap.containsKey(obj);
        }

        public T peek() {
            return this.heap.get(0);
        }

        public void push(T obj) {
            this.heap.add(obj);
            this.indexMap.put(obj, heapSize);
            // 把heapSize准备好,下一次add元素时,直接使用heapSize的位置
            this.heapInsert(heapSize++);
        }

        public T pop() {
            // 要弹出的元素
            T ans = this.heap.get(0);
            // 反向索引表中remove这个索引
            this.indexMap.remove(ans);
            // 将0位置和最后位置的元素交换
            swap(heap, 0, --heapSize);
            // 删除最后位置的元素，也即是原本0号位置的元素，pop弹出的元素
            heap.remove((int)heapSize);
            heapify(0);
            return ans;
        }


        /**
         * 删除任意位置的元素的obj
         *
         * 删除obj元素之后，把堆中最后一个元素移动到原obj位置，同时堆少一个元素，然后heapInsert或heapify重构堆
         *另一种理解：将最后一个元素与要移除的obj调换位置，然后堆少一个元素，重构堆！！！
         * @param obj
         */
        public void remove(T obj) {
            //删除元素在heap中的位置
            Integer index = this.indexMap.get(obj);
            //heap中最后一个元素
            T lastObj = heap.get(heapSize - 1);
            //反向索引表中删除obj元素
            this.indexMap.remove(obj);
            //heap中删除最后一个元素
            this.heap.remove((int) --heapSize); // 这里很坑，如果包装类型，按元素删，如果简单类型，按索引删除
            if (obj != lastObj) { // 如果删除的元素不是最后一个元素
                // 将lastObj元素放到原先obj的位置
                heap.set(index, lastObj);
                indexMap.put(lastObj, index);
                rebuild(lastObj);
            }
        }

        private void rebuild(T obj) {
            // heapInsert 和 heapify 只会执行一个方法，要么上移，要么下沉
            this.heapInsert(this.indexMap.get(obj));
            this.heapify(this.indexMap.get(obj));
        }

        // 插入数据,重构
        private void heapInsert(Integer index) {
            // 父节点位置
            //  int parent = (index - 1) >> 1;
            //a negative integer, zero, or a positive integer as the
            //first argument is less than, equal to, or greater than the second.
            // 父节点的值比子节点值小
            while (comparator.compare(heap.get((index - 1) / 2), heap.get(index)) < 0) {
                swap(heap, (index - 1) / 2, index);
                index = (index - 1) / 2;
            }
        }

        // 堆下沉，重构
        private void heapify(Integer index) {
            // 删除堆中某个节点之后，比较左右子节点的大小，将较大的那个子节点上移
            // index的左子节点
            int left = index * 2 + 1;
            while (left < heapSize) { // 条件成立,说明index有左子节点
                // 1.如果右子节点存在,比较左右节点的大小,将较大的那个节点标记为maxIndex
                int maxIndex = left + 1 < heapSize &&
                        comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ? left + 1 : left;
                // 2.再比较父节点与maxIndex的大小
                maxIndex = comparator.compare(heap.get(index), heap.get(maxIndex)) > 0 ? index : maxIndex;
                if (maxIndex == index) { // 说明父节点大，不需要移动
                    break;
                }
                // 3. 交换父、子节点的元素
                swap(heap, index, maxIndex);
                // 4. 再比较新的index节点
                index = maxIndex;
                left = index * 2 + 1;
            }

        }

        private void swap(List<T> heap, int i, int j) {
            T t1 = heap.get(i);
            T t2 = heap.get(j);
            heap.set(i, t2);
            heap.set(j, t1);

            indexMap.put(t1, j);
            indexMap.put(t2, i);
        }

    }


    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {

        HeapEnhancer<Integer> heap = new HeapEnhancer<Integer>(new IntegerComparator());
//        {8, 4, 6, 2, 1, 5}
        heap.push(4);
        heap.push(6);
        heap.push(2);
        heap.push(5);
        heap.push(1);
        heap.push(8);

        System.out.println(heap.peek());

        heap.remove(6);
        System.out.println(heap.pop());
        System.out.println(heap);

    }
}
