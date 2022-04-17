package com.ppwqdxlte.basic.class11;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author:李罡毛
 * @date:2021/8/9 19:06
 * 【迪耶斯特拉算法】   求 源点 到 每个点 的最小路径集合
 * 注意：不是求最小生成树，只是求 [0，i]的2点之间的最小路径
 */
public class Code06_Dijkstra {

    /**迪耶斯特拉 0(N)
     * @param from 源头结点
     * @return 从 源头结点到所有 结点的最小路径 的集合,key-目的地结点 value-源头到目的地的最短距离
     */
    public HashMap<Node<Integer>,Integer> dijkstra1(Node<Integer> from){
        HashMap<Node<Integer>,Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //打过对号的点
        HashSet<Node<Integer>> touchedNodes = new HashSet<>();
        Node<Integer> minNode = getMinDistanceAndUntouchedNode(distanceMap,touchedNodes);
        while (minNode != null){
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge<Integer> edge : minNode.edges) {
                Node<Integer> to = edge.to;
                if (!distanceMap.containsKey(to)){
                    distanceMap.put(to,distance + edge.weight);
                }else {
                    distanceMap.put(to,Math.min(distanceMap.get(to),distance + edge.weight));
                }
            }
            touchedNodes.add(minNode);
            minNode = getMinDistanceAndUntouchedNode(distanceMap,touchedNodes);
        }
        return distanceMap;
    }
    private Node<Integer> getMinDistanceAndUntouchedNode(HashMap<Node<Integer>,Integer> distanceMap,HashSet<Node<Integer>> touchedNodes){
        Node<Integer> minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node<Integer>,Integer> entry : distanceMap.entrySet()) {
            if (touchedNodes.contains(entry.getKey())) continue;
            Node<Integer> node = entry.getKey();
            int distance = entry.getValue();
            if (distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**改进后的dijkstra算法，由于用到了堆结构 O(logN)，
     * @param head 源头结点
     * @return 从head出发，所有head能到达的节点，到达每个节点的最小路径记录
     */
    public HashMap<Node<Integer>,Integer> dijkstra2(Node<Integer> head){
        NodeHeap nodeHeap = new NodeHeap();
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node<Integer>,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            Entry<Node<Integer>,Integer> entry = nodeHeap.pop();
            Node<Integer> cur = entry.getKey();
            int distance = entry.getValue();
            for (Edge<Integer> edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,distance + edge.weight);
            }
            result.put(cur,distance);
        }
        return result;
    }

    public static class NodeHeap{
        /**实际堆结构*/
        private List<Node<Integer>> heap;
        /**key 某一个node， value 上面堆中的位置，适度冗余，方便处理数据*/
        private HashMap<Node<Integer>,Integer> indexMap;
        /**key 某一个节点， value 从源节点出发到该节点的目前最小距离（就是说还不是最终结果）*/
        private HashMap<Node<Integer>,Integer> distanceMap;
        /**堆上多少个点*/
        private int size;
        public NodeHeap(){
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        /**判断要不要更新，如果需要的话，就更新
         * @param node 有一个点叫node
         * @param distance 从源节点出发到达node的距离为distance
         */
        public void addOrUpdateOrIgnore(Node<Integer> node,int distance){
            if (inHeap(node)){
                distanceMap.put(node,Math.min(distance,distanceMap.get(node)));
                insertHeapify(node,indexMap.get(node));
            }
            if (!isEntered(node)){
                heap.add(node);
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }
        /**判断是否堆上有效点
         * @param node 某点
         * @return 是否添加过堆，且没被移除
         */
        private boolean inHeap(Node<Integer> node){
            return isEntered(node) && indexMap.get(node) != -1;
        }
        /**判断添加过堆没有
         * @param node 某点
         * @return 是否添加过堆
         */
        private boolean isEntered(Node<Integer> node){
            return indexMap.containsKey(node);
        }

        /**小根堆，按照距离调整插入点位置
         * @param node 新插入的点
         * @param index 插入位置
         */
        private void insertHeapify(Node<Integer> node,int index){
            while (distanceMap.get(heap.get(index)) < distanceMap.get(heap.get((index - 1)>>1))){
                swap(index,(index - 1)>>1);
                index = (index - 1)>>1;
            }
        }
        /**堆中交换2个点的位置，同时更新index Map的位置
         * @param i1 堆上位置
         * @param i2 堆上位置
         */
        private void swap(int i1,int i2){
            indexMap.put(heap.get(i1),i2);
            indexMap.put(heap.get(i2),i1);
            Node<Integer> tmp =  heap.get(i1);
            heap.set(i1,heap.get(i2));
            heap.set(i2,tmp);
        }

        /**弹出  最小距离的点、距离键值对
         * @return 结点和距离的键值对
         */
        public Entry<Node<Integer>,Integer> pop(){
            Entry<Node<Integer>,Integer> entry = new AbstractMap.SimpleEntry<>(heap.get(0),distanceMap.get(heap.get(0)));
            swap(0,size - 1);
            indexMap.put(heap.get(size - 1),-1);
            distanceMap.remove(heap.get(size - 1));
            //free C++ 同学还要把原本堆顶结点析构，对Java同学不必
            heap.remove(size - 1);
            heapify(0,--size);
            return entry;
        }
        private void heapify(int index,int heapSize){
            int left = index << 1 + 1;
            while (left < size){
                int min = left + 1 < size && distanceMap.get(heap.get(left + 1)) < distanceMap.get(heap.get(left))
                        ? left + 1 : left;
                min = distanceMap.get(heap.get(min)) < distanceMap.get(heap.get(index)) ? min : index;
                if (min == index){
                    break;
                }
                swap(min,index);
                index = min;
                left = index << 1 + 1;
            }
        }
    }
}
