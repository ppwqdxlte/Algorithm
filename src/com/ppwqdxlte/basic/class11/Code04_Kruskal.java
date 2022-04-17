package com.ppwqdxlte.basic.class11;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/8/9 16:01
 * 用Kruskal算法生成最小生成树 Minimum Spanning Tree,MST
 * 一个有 n 个结点的连通图的生成树是原图的极小连通子图，且包含原图中的所有 n 个结点，并且有保持图连通的最少的边。
 * 最小生成树可以用kruskal（克鲁斯卡尔）算法或prim（普里姆）算法求出。
 */
public class Code04_Kruskal {
    //并查集
    public static class UnionFind{
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node,Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Node,Integer> sizeMap;
        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }
        public void buildSets(Collection<Node<Integer>> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        private Node findRoot(Node n){
            Stack<Node> ancestors = new Stack<>();
            while (n != fatherMap.get(n)){
                ancestors.push(fatherMap.get(n));
                n = fatherMap.get(n);
            }//出来n等于root
            //扁平化操作
            while (!ancestors.isEmpty()){
                fatherMap.put(ancestors.pop(),n);
            }
            return n;
        }
        public boolean isSameSet(Node a,Node b){
            return findRoot(a) == findRoot(b);
        }
        public void union(Node a,Node b){
            if (a == null || b == null) return;
            Node aRoot = findRoot(a);
            Node bRoot = findRoot(b);
            if (aRoot == bRoot) return;//根节点相同就是一个集合，不用合并了
            int aSetSize = sizeMap.get(aRoot);
            int bSetSize = sizeMap.get(bRoot);
            boolean aLTb = aSetSize < bSetSize;
            fatherMap.put(aLTb ? aRoot : bRoot,aLTb ? bRoot : aRoot);
            sizeMap.put(aLTb?bRoot:aRoot,sizeMap.get(b)+sizeMap.get(a));
            sizeMap.remove(aLTb?aRoot:bRoot);
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge<Integer>> kruskalMST(Graph<Integer> graph){
        UnionFind unionFind = new UnionFind();
        unionFind.buildSets(graph.nodes.values());
        //从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge<Integer>> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);
        Set<Edge<Integer>> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){                 //M 条边
            Edge<Integer> edge = priorityQueue.poll();    //O(logM)
            if (!unionFind.isSameSet(edge.from,edge.to)) {//O(1)
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }
}
