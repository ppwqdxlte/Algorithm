package com.ppwqdxlte.basic.class11;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static com.ppwqdxlte.basic.class11.GraphGenerator.*;
/**
 * @author:李罡毛
 * @date:2021/8/4 21:00
 * 宽度优先搜索  BFS 即 Breadth-First Search
 */
public class Code01_BFS {
    /**宽度打印树
     * @param head 树里面的某个结点
     */
    public static void breadthFirstPrintTree(Node<Integer> head){
        if (head == null) return;
        System.out.println("-----------??????------------");
        Queue<Node<Integer>> queue = new LinkedList<>();
        HashSet<Node<Integer>> addedSet = new HashSet<>();
        queue.add(head);
        addedSet.add(head);
        Node<Integer> cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.println(cur.value);
            if (!cur.nexts.isEmpty()){
                for (Node<Integer> next : cur.nexts) {
                    if (!addedSet.contains(next)){
                        queue.add(next);
                        addedSet.add(next);
                    }
                }
            }
        }
    }
    /**宽度打印森林
     * @param graph 要遍历的图，里面可能是森林
     */
    public static void breadthFirstPrintForest(Graph<Integer> graph){
        if (graph == null || graph.nodes.size() == 0) return;
        System.out.println("===================================");
        Queue<Node<Integer>> nodeQueue = new LinkedList<>();
        HashSet<Node<Integer>> addedSet = new HashSet<>();//所有已经处理过的结点
        //图可能不止一颗，也许是一片森林，所以要考虑所有入度为0的头结点
        for (Integer i: graph.nodes.keySet()) {
            Node<Integer> n = graph.nodes.get(i);
            if (addedSet.contains(n)) continue;
            nodeQueue.add(n);
            addedSet.add(n);
            Node<Integer> cur = null;
            while (!nodeQueue.isEmpty()){
                cur = nodeQueue.poll();
                System.out.println(cur.value);
                if (!cur.nexts.isEmpty()){
                    for (Node<Integer> next : cur.nexts){
                        if (addedSet.contains(next)) continue;
                            nodeQueue.add(next);
                            addedSet.add(next);
                    }
                }
            }
        }
        System.out.println("按照宽度优先遍历，一共找到了："+addedSet.size());
    }


    public static void main(String[] args) {
        Node<Integer> h1 = new Node<>(12);
        Node<Integer> n11 = new Node<>(3);
        Node<Integer> n12 = new Node<>(9);
        Node<Integer> n13 = new Node<>(98);
        Node<Integer> n14 = new Node<>(9);
        Node<Integer> n15 = new Node<>(34);
        Node<Integer> n16 = new Node<>(100);
        h1.in = 2;h1.out = 3;h1.nexts.add(n11);h1.nexts.add(n12);h1.nexts.add(n13);
        n11.in = 2;n11.out = 1;n11.nexts.add(n16);
        n12.in = 2;
        n13.in = 2;
        n14.in = 1;n14.out = 3;n14.nexts.add(h1);n14.nexts.add(n12);n14.nexts.add(n15);
        n15.in = 1;n15.out = 2;n15.nexts.add(h1);n15.nexts.add(n11);
        n16.in = 1;n16.out = 2;n16.nexts.add(n13);n16.nexts.add(n14);
        Edge<Integer> eh1 = new Edge<>(1,h1,n12);
        Edge<Integer> eh2 = new Edge<>(2,h1,n13);
        Edge<Integer> eh3 = new Edge<>(3,h1,n11);
        Edge<Integer> e11 = new Edge<>(4,n11,n16);
        Edge<Integer> e61 = new Edge<>(5,n16,n13);
        Edge<Integer> e62 = new Edge<>(6,n16,n14);
        Edge<Integer> e41 = new Edge<>(7,n14,n15);
        Edge<Integer> e51 = new Edge<>(8,n15,h1);
        Edge<Integer> e42 = new Edge<>(9,n14,h1);
        Edge<Integer> e43 = new Edge<>(10,n14,n12);
        h1.edges.add(eh1);h1.edges.add(eh2);h1.edges.add(eh3);
        n11.edges.add(e11);
        n16.edges.add(e61);n16.edges.add(e62);
        n15.edges.add(e51);
        n14.edges.add(e41);n14.edges.add(e42);n14.edges.add(e43);

        Node<Integer> h2 = new Node<>(12);
        Node<Integer> n21 = new Node<>(3);
        Node<Integer> n22 = new Node<>(9);
        Node<Integer> n23 = new Node<>(98);
        Node<Integer> n24 = new Node<>(9);
        Node<Integer> n25 = new Node<>(34);
        Node<Integer> n26 = new Node<>(100);
        h2.in = 2;h2.out = 3;h2.nexts.add(n21);h2.nexts.add(n22);h2.nexts.add(n23);
        n21.in = 2;n21.out = 1;n21.nexts.add(n26);
        n22.in = 2;
        n23.in = 2;
        n24.in = 1;n24.out = 3;n24.nexts.add(h2);n24.nexts.add(n22);n24.nexts.add(n25);
        n25.in = 1;n25.out = 2;n25.nexts.add(h2);n25.nexts.add(n21);
        n26.in = 1;n26.out = 2;n26.nexts.add(n23);n26.nexts.add(n24);
        Edge<Integer> eh21 = new Edge<>(1,h2,n22);
        Edge<Integer> eh22 = new Edge<>(2,h2,n23);
        Edge<Integer> eh23 = new Edge<>(3,h2,n21);
        Edge<Integer> e211 = new Edge<>(4,n21,n26);
        Edge<Integer> e261 = new Edge<>(5,n26,n23);
        Edge<Integer> e262 = new Edge<>(6,n26,n24);
        Edge<Integer> e241 = new Edge<>(7,n24,n25);
        Edge<Integer> e251 = new Edge<>(8,n25,h2);
        Edge<Integer> e242 = new Edge<>(9,n24,h2);
        Edge<Integer> e243 = new Edge<>(10,n24,n22);
        h2.edges.add(eh21);h2.edges.add(eh22);h2.edges.add(eh23);
        n21.edges.add(e211);
        n26.edges.add(e261);n16.edges.add(e262);
        n25.edges.add(e251);
        n24.edges.add(e241);n24.edges.add(e242);n24.edges.add(e243);

        Graph<Integer> graph = new Graph<>();

        breadthFirstPrintTree(h1);
        breadthFirstPrintTree(h2);
//        System.out.println(new Integer(12) == new Integer(12));
        try {
//            Integer i1 = Integer.class.getConstructor(int.class).newInstance(10);
//            Integer i2 = Integer.class.getConstructor(int.class).newInstance(10);
//            System.out.println(i1 == i2);
            System.out.println(graph.nodes.size());
            addTreeToGraph(graph,h1);
            System.out.println(graph.nodes.size());
            addTreeToGraph(graph,h2);
            System.out.println(graph.nodes.size());
            breadthFirstPrintForest(graph);

//            HashMap<Integer,Integer> hashMap = new HashMap<>();
//            hashMap.put(Integer.class.getConstructor(int.class).newInstance(10),10);
//            hashMap.put(Integer.class.getConstructor(int.class).newInstance(10),10);
//            hashMap.put(Integer.class.getConstructor(int.class).newInstance(10),10);
//            hashMap.put(Integer.class.getConstructor(int.class).newInstance(10),10);
//            System.out.println(hashMap.size());//1~~~!!!
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        int leafLimit = 10;
        int valueLimit = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            try {
                Node<Integer> head1 = generateDirectedTree(leafLimit,valueLimit);
                Node<Integer> head2 = generateDirectedTree(leafLimit,valueLimit);
                Node<Integer> head3 = generateDirectedTree(leafLimit,valueLimit);
                breadthFirstPrintTree(head1);
                breadthFirstPrintTree(head2);
                breadthFirstPrintTree(head3);
                Graph<Integer> graph1 = new Graph<>();
                addTreeToGraph(graph1,head1);
                addTreeToGraph(graph1,head2);
                addTreeToGraph(graph1,head3);
                breadthFirstPrintForest(graph1);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }
}
