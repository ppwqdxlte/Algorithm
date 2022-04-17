package com.ppwqdxlte.basic.class11;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static com.ppwqdxlte.basic.class11.Code04_Kruskal.*;
/**
 * @author:李罡毛
 * @date:2021/8/9 17:31
 * 用Prim算法求最小生成树
 */
public class Code05_Prim {

    public Set<Edge<Integer>> primMST(Graph<Integer> graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge<Integer>> edgeQueue = new PriorityQueue<>(new EdgeComparator());
        // 哪些点被解锁出来了
        HashSet<Node<Integer>> nodes = new HashSet<>();
        Set<Edge<Integer>> result = new HashSet<>();// 依次挑选的的边在result里
        for (Node<Integer> node : graph.nodes.values()) {// 随便挑了一个点，预防森林
            // node 是开始点
            if (!nodes.contains(node)){
                nodes.add(node);
                edgeQueue.addAll(node.edges);// 由一个点，解锁所有相连的边
            }
            while (!edgeQueue.isEmpty()){
                Edge<Integer> edge = edgeQueue.poll();// 弹出解锁的边中，最小的边
                Node<Integer> to = edge.to;
                if (!nodes.contains(to)){
                    nodes.add(to);
                    result.add(edge);
                    edgeQueue.addAll(to.edges);
                }
            }
        }
        return result;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length;//结点N个，size = N
        int[] distances = new int[size];//从源节点0到所有结点的距离集合
        boolean[] visit = new boolean[size];//true标记访问过结点咯，以后不再碰它
        visit[0] = true;//源节点是0位置，以后不再碰他
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];//从0-i的距离，代码看到这里才知道目的就是从源结点到i的距离
        }
        int sum = 0;//
        for (int i = 1; i < size; i++) {//因为碰过0了，所以从1结点以后开始算
            int minPath = Integer.MAX_VALUE;//默认不连通，距离无穷大
            int minIndex = -1;//默认还没找到最小距离结点
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {//没碰过j，且0-j的样本距离<此时测算的最小距离
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {//找不着了，就直接返回了
                return sum;
            }
            visit[minIndex] = true;//标记已阅
            sum += minPath;//更新最小联通路径的值
            for (int j = 0; j < size; j++) {//既然找到了最小路径结点，那么判断并更新其它点的距离
                //没访问过j点，且j点的样本距离>minIndex~j的距离，那么取消原距离，替换成较小的边长度！
                if (!visit[j] && distances[j] > graph[minIndex][j]) {//
                    distances[j] = graph[minIndex][j];//
                }
            }
        }
        return sum;
    }
}

