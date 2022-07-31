package qinfeng.zheng.date_20210826;

/**
 * @Author ZhengQinfeng
 * @Date 2021/8/28 10:26
 * @dec
 */
public class A_05_数组实现栈和队列 {


    private static class MyStack<T> {
        Object[] arr = null;
        int size = 0;

        public MyStack(int limit) {
            this.arr = new Object[limit];
        }

        public void push(T value) {
            if (size > arr.length) {
                throw new RuntimeException("栈满！");
            }
            arr[size++] = value;
        }

        public T pop() {
            if (size <= 0) {
                throw new RuntimeException("栈空！");
            }
            return (T) arr[--size];

        }


    }

    /**
     * 通过数组来实现队列的功能
     */
    private static class MyQueue<T> {
        Object[] arr;
        int limit;
        int pushIndex; // push元素的指针
        int popIndex;  // pop元素的指针
        int size;    // 元素的个数！！！！！！！！！！！！

        public MyQueue(int limit) {
            this.arr = new Object[limit];
            this.limit = limit;
            this.pushIndex = 0;
            this.popIndex = 0;
            this.size = 0;
        }

        public void push(T value) {
            if (size >= limit) {
                throw new RuntimeException("队列已满！");
            }

            arr[pushIndex] = value;
            size++;
            this.pushIndex = nextIndex(this.pushIndex);
        }


        public T pop() {
            if (size <= 0) {
                throw new RuntimeException("队列已空！");
            }
            T value = (T) arr[popIndex];
            size--;
            this.popIndex = nextIndex(popIndex);

            return value;

        }

        // 所以是个环形数组
        private int nextIndex(int index) {
            return index < limit - 1 ? (index + 1) : 0;
        }

    }


    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<String>(3);
        stack.push("A1");
        stack.push("A2");
        stack.push("A3");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        System.out.println("=================");


        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.push(1);
        queue.push(2);
        queue.push(3);
//        queue.push(4);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}

