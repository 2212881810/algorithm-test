package qinfeng.zheng.date_20210914;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author ZhengQinfeng
 * @Date 2021/9/14 21:52
 * @dec 1）有若干个样本a、b、c、d…类型假设是V
 * 2）在并查集中一开始认为每个样本都在单独的集合里
 * 3）用户可以在任何时候调用如下两个方法：
 * boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 * void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * 4） isSameSet和union方法的代价越低越好
 */
public class A_01_并查集_hash表实现 {

    // 将元素v用Node来包装一下
    public static class Node<V> {
        V v;

        public Node(V v) {
            this.v = v;
        }
    }

    public static class UnionFind<V> {
        // 保存元素v与其包装类Node的映射关系
        public HashMap<V, Node<V>> nodes;

        //保存Node与其代表节点的映射关系
        public HashMap<Node<V>, Node<V>> parents;

        // 以Node为代表节点的集合中有多少个元素
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            this.nodes = new HashMap<>();
            this.parents = new HashMap<>();
            this.sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> cur = new Node<>(value);
                nodes.put(value, cur);
                parents.put(cur, cur); // 初始化时自己就是自己的代表节点
                sizeMap.put(cur, 1); // 每个元素各自成为一个单独的集合，元素个数为1
            }
        }

        //给定一个节点cur,找到它的代表节点
        public Node<V> findFather(Node<V> cur) {

            Stack<Node> stack = new Stack<>();
            while (cur != parents.get(cur)) {// 因为最开始的时候设定了自己是自己的代表节点！！！
                stack.push(cur);
                cur = parents.get(cur);
            }

            // 优化性能,将cur所有父链上的节点的代表节点直接弄成cur【此的cur并不是入参的cur】
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        // 判断a,b两个元素是否在同一个集合中
        public boolean isSameSet(V a, V b) {
            // == 比较的是对象地址
            // a,b 两个元素一定在初始化集合中的某两个元素
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }


        // 将a,b两个元素合并成一个集合
        public void union(V a, V b) {
            // 找到a,b两个元素的代表节点ahead和bhead
            Node ahead = findFather(nodes.get(a));
            Node bhead = findFather(nodes.get(b));
            if (ahead != bhead) { // 说明a,b两个元素没有在同一个集合中，需要合并
                // 查看ahead和bhead两个集合中元素个数
                int acount = sizeMap.get(ahead);
                int bcount = sizeMap.get(bhead);

                //将集合元素多个那个代表节点标记成Big
                Node big = acount > bcount ? ahead : bhead;
                Node small = big == ahead ? bhead : ahead;

                // 集合元素较少的代表节点变成big,这样在下一次调用findFather方法，会将链上的所有节点的代表节点变成big节点
                parents.put(small, big);
                //修改以big节点为代表节点的集合元素个数
                sizeMap.put(big, acount + bcount);
                // 以small为代表节点的集合不存在了，它成了big集合中的一部分
                sizeMap.remove(small);
            }

        }

        // 集合个数
        public int sets() {
            return sizeMap.size();
        }
    }


}
