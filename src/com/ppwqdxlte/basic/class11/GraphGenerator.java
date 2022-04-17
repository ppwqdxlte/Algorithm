package com.ppwqdxlte.basic.class11;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.ppwqdxlte.basic.class11.Code01_BFS.*;
/**
 * @author:李罡毛
 * @date:2021/8/4 21:52
 * 图转换器，就是转换接口，把某种数据结构转换成自己的图结构
 * 任凭图的题目是啥样的数据结构，只要自己写转换接口，将不方便的机构转换成模板图结构，更方便扩展！
 * 不同结构转换，写成重载方法即可！
 */
public class GraphGenerator {
    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    public static Graph<Integer> createGraph(int[][] matrix){
        Graph<Integer> graph = new Graph<>();
        for (int[] eArr : matrix){
            Node<Integer> from = null;
            Node<Integer> to = null;
            if (!graph.nodes.containsKey(eArr[1])){
                from = new Node<>(eArr[1]);
                graph.nodes.put(from.value,from);
            }else {
                from = graph.nodes.get(eArr[1]);
            }
            if (!graph.nodes.containsKey(eArr[2])){
                to = new Node<>(eArr[2]);
                graph.nodes.put(to.value,to);
            }else {
                to = graph.nodes.get(eArr[2]);
            }
            //没必要封装，虽然-128 到 127默认返回一个地址，
            // 但是int矩阵，结点本来可以有好几个边，但每个边只能连两个结点
            Edge<Integer> edge = new Edge<>(eArr[0],from,to);
            from.out ++;
            to.in ++;
            from.nexts.add(to);
            from.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }


    /**往图里添加树
     * @param graph 图
     * @param head 树
     */
    public static void addTreeToGraph(Graph<Integer> graph,Node<Integer> head) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (head == null) return;
        Queue<Node<Integer>> queue = new LinkedList<>();
        HashSet<Node<Integer>> addedSet = new HashSet<>();
        queue.add(head);
        addedSet.add(head);
        Node<Integer> cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            //添加
            graph.nodes.put(Integer.class.getConstructor(int.class).newInstance(cur.value),cur);
            graph.edges.addAll(cur.edges);
            for (Node<Integer> next : cur.nexts) {
                if (!addedSet.contains(next)){
                    queue.add(next);
                    addedSet.add(next);
                }
            }
        }
    }
    // 但是否全连无所谓，反正是随机生成，head最终连不到的部分丢弃即可
    //N个结点，最少需要N - 1个边才能把所有结点连一起成为一棵“树”整体
    //一棵树图最多有 N(N - 1)/2 个边
    //边不会重复，有向图1条边，无向图，两点间2条边
    //对于每个node，有向图的【0 < 入度 + 出度 <= N - 1】
    //           无向图的【0 < 入度 + 出度 <= 2*(N - 1)】
    //特殊情况in + out == 0时，图里只有一个点
    //完全随机生成图，难度很大，自己数学不太好，
    // 所以人为限定条件：1.每个点in+out>=1且<=(N-1)/2
    //               2.head头结点必须out>=1
    /**随机生成只有一棵树的【有向图】
     * @param leafLimit 树最多结点数
     * @param valueLimit 结点值限制
     * @return 树
     * 这个方法有许多人为干预条件和设定，因为很难彻底标准的随机
     */
    public static Node<Integer> generateDirectedTree(int leafLimit,int valueLimit) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (Math.random() > 0.95) return null;
        IdentityHashMap<Node<Integer>,HashSet<Node<Integer>>> parentsMap = new IdentityHashMap<>();
        List<Node<Integer>> allNodes = new ArrayList<>();
        int N = (int)(Math.random()*leafLimit + 1);
        for (int i = 0; i < N; i++) {
            Node<Integer> leaf = new Node<>(Integer.class.getConstructor(int.class).newInstance(
                    (int)(Math.random()*(valueLimit + 1))));
            parentsMap.put(leaf,new HashSet<>());
        }
        allNodes.addAll(parentsMap.keySet());
        Node<Integer> c = null;
        for (int i = 0; i < N; i++) {
            c = allNodes.get(i);
            int out = (int)(Math.random()*(N-1)/2 + 1);
            for (int j = 0; j < out; j++) {
                int rand = (int)(Math.random()*N);
                if (rand == i || c.nexts.contains(allNodes.get(rand))
                    || parentsMap.get(c).contains(allNodes.get(rand))) {
                    out --;
                    continue;
                }
                c.nexts.add(allNodes.get(rand));
                c.edges.add(new Edge<>((int)(Math.random()*2*valueLimit/N + 1),c,allNodes.get(rand)));
                allNodes.get(rand).in += 1;
            }
            c.out = out;
        }
        return allNodes.get(0);
    }
    /**随机生成只有一棵树的【无向图】
     * @param leafLimit 树最多结点数
     * @param valueLimit 结点值限制
     * @return 树
     */
    public static Node<Integer> generateUndirectedTree(int leafLimit,int valueLimit) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (Math.random() > 0.95) return null;
        IdentityHashMap<Node<Integer>,HashSet<Node<Integer>>> parentsMap = new IdentityHashMap<>();
        List<Node<Integer>> allNodes = new ArrayList<>();
        int N = (int)(Math.random()*leafLimit + 1);
        for (int i = 0; i < N; i++) {
            Node<Integer> leaf = new Node<>(Integer.class.getConstructor(int.class).newInstance(
                    (int)(Math.random()*(valueLimit + 1))));
            parentsMap.put(leaf,new HashSet<>());
        }
        allNodes.addAll(parentsMap.keySet());
        Node<Integer> c = null;
        for (int i = 0; i < N; i++) {
            c = allNodes.get(i);
            int out = (int)(Math.random()*(N-1) + 1);//无向图边数的极值是有向图的2倍，你细品
            for (int j = 0; j < out; j++) {
                int rand = (int)(Math.random()*N);
                if (rand == i || c.nexts.contains(allNodes.get(rand))
                        || parentsMap.get(c).contains(allNodes.get(rand))
                        ) {
                    out --;
                    continue;
                }
                c.nexts.add(allNodes.get(rand));
                int weight = (int)(Math.random()*2*valueLimit/N + 1);
                c.edges.add(new Edge<>(weight,c,allNodes.get(rand)));
                c.in += 1;
                allNodes.get(rand).in += 1;
                allNodes.get(rand).out += 1;
                allNodes.get(rand).nexts.add(c);
                allNodes.get(rand).edges.add(new Edge<>(weight,allNodes.get(rand),c));
            }
            c.out = out;
        }
        return allNodes.get(0);
    }
    /**随机生成【有向图】(可能是森林)
     */
    public static Graph<Integer> generateDirectedGraph(int treeLimit,int leafLimit,int valueLimit) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Graph<Integer> graph = new Graph<>();
        int treeN = (int) (Math.random()*(treeLimit + 1));
        for (int i = 0; i < treeN; i++) {
            Node<Integer> tree = generateDirectedTree(leafLimit,valueLimit);
            addTreeToGraph(graph,tree);
        }
        return graph;
    }
    /**随机生成【无向图】(可能是森林)
     */
    public static Graph<Integer> generateUndirectedGraph(int treeLimit,int leafLimit,int valueLimit) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Graph<Integer> graph = new Graph<>();
        int treeN = (int) (Math.random()*(treeLimit + 1));
        for (int i = 0; i < treeN; i++) {
            Node<Integer> tree = generateUndirectedTree(leafLimit,valueLimit);
            addTreeToGraph(graph,tree);
        }
        return graph;
    }

    public static void main(String[] args) {
        int treeLimit = 10;
        int leafLimit = 20;
        int valueLimit = 200;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            try {
                /*Node<Integer> head1 = generateUndirectedTree(leafLimit,valueLimit);
                Node<Integer> head2 = generateUndirectedTree(leafLimit,valueLimit);
                Node<Integer> head3 = generateUndirectedTree(leafLimit,valueLimit);
                breadthFirstPrintTree(head1);
                breadthFirstPrintTree(head2);
                breadthFirstPrintTree(head3);
                Graph<Integer> graph = new Graph<>();
                addTreeToGraph(graph,head1);
                addTreeToGraph(graph,head2);
                addTreeToGraph(graph,head3);
                breadthFirstPrintForest(graph);*/
                Graph<Integer> graph1 = generateUndirectedGraph(treeLimit,leafLimit,valueLimit);
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
