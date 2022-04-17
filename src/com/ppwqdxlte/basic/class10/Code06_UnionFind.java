package com.ppwqdxlte.basic.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author:李罡毛
 * @date:2021/8/2 23:02
 * 【并查集】数据结构，衍生出一些算法
 */
public class Code06_UnionFind {

    private static class Node<T>{
        T value;
        public Node(T v){
            value = v;
        }
    }
    //并查集 ，一种数据结构
    private static class UnionFind<T>{

        public HashMap<T,Node<T>> nodes;        //所有点的集合
        public HashMap<Node<T>,Node<T>> parentMap;//k son v parent
        public HashMap<Node<T>,Integer> sizeMap;//k 代表点 v 集合大小

        public UnionFind(List<T> values){
            nodes = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T v : values) {
                Node<T> node = new Node<>(v);
                nodes.put(v,node);
                parentMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<T> findFather(Node<T> cur){
            Stack<Node<T>> path = new Stack<>();
            while (cur != parentMap.get(cur)){
                path.push(cur);
                cur = parentMap.get(cur);
            }
            //此时cur就是代表点
            //!!!还没完，扁平化集合，使以后查询时间复杂度O(1)
            while (!path.isEmpty()){
                parentMap.put(path.pop(),cur);
            }
            return cur;
        }
        public boolean isSameSet(T a,T b){
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }
        public void union(T a,T b){
            Node<T> aHead = findFather(nodes.get(a));
            Node<T> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<T> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<T> small = big == aHead ? bHead : aHead;
                parentMap.put(small,big);
                sizeMap.put(big,aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets(){
            return sizeMap.size();
        }
    }

}
