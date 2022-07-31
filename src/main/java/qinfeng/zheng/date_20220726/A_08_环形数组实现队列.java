package qinfeng.zheng.date_20220726;

import java.util.Arrays;

public class A_08_环形数组实现队列 {


    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(3);
        myQueue.put(1);
        myQueue.put(2);
        myQueue.put(3);
        System.out.println(Arrays.toString(myQueue.arr));
        System.out.println(myQueue.pop());
        myQueue.put(4);
        System.out.println(Arrays.toString(myQueue.arr));
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }

    static class MyQueue {
        int[] arr;
        /**
         * 数组中最多能存储的元数个数
         */
        int limit;
        /**
         * 当前已经存储的元数个数
         */
        int size;
        /**
         * 下一次put操作的index
         */
        int putI;
        /**
         * 下一次pop操作的index
         */
        int popI;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.limit = limit;
        }

        public void put(int ele) {
            if (this.size >= this.limit) {
                throw new RuntimeException("队列已满!");
            }

            this.arr[this.putI] = ele;
            size++;
            this.putI = nextIndex(this.putI);
        }

        public int pop() {
            if (this.size <= 0) {
                throw new RuntimeException("队列已空~");
            }

            int value = this.arr[this.popI];
            size--;
            this.popI = nextIndex(this.popI);
            return value;
        }

        private int nextIndex(int index) {

            return (index + 1) < this.limit ? index + 1 : 0;

        }


    }


}
