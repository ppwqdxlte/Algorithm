package com.ppwqdxlte.basic.class11;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/8/6 21:16
 * 拓扑排序宽度优先遍历
 * 【注意】所需的结点结构，只需要nexts和value，不需要in，out，edges这三个
 * 什么是拓扑排序呢？对一个有向无环图(Directed Acyclic Graph简称DAG)G进行拓扑排序，是将G中所有顶点排成一个线性序列，
 * 使得图中任意一对顶点u和v，若边<u,v>∈E(G)，则u在线性序列中出现在v之前。
 * 通常，这样的线性序列称为满足拓扑次序(Topological Order)的序列，简称拓扑序列。
 * 简单的说，由某个集合上的一个偏序得到该集合上的一个全序，这个操作称之为拓扑排序。
 *
 * 一个较大的工程往往被划分成许多子工程，我们把这些子工程称作活动(activity)。在整个工程中，有些子工程(活动)必须在其它有关子工程完成之后才能开始，
 * 也就是说，一个子工程的开始是以它的所有前序子工程的结束为先决条件的，但有些子工程没有先决条件，可以安排在任何时间开始。
 * 为了形象地反映出整个工程中各个子工程(活动)之间的先后关系，可用一个有向图来表示，图中的顶点代表活动(子工程)，
 * 图中的有向边代表活动的先后关系，即有向边的起点的活动是终点活动的前序活动，只有当起点活动完成之后，其终点活动才能进行。
 * 通常，我们把这种顶点表示活动、边表示活动间先后关系的有向图称做顶点活动网(Activity On Vertex network)，简称AOV网。
 *
 * Java中最明显的拓扑顺序就是依赖关系！！！例如 A中引用了B，就是A对B有依赖，B成A方能成
 */
public class Code03_TopologicalOrderBFS {

    public static class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int x){
            label = x;
            neighbors = new ArrayList<>();
        }
    }
    public List<DirectedGraphNode> topoSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode,Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode cur : graph){
            indegreeMap.put(cur,0);
        }
        for (DirectedGraphNode cur : indegreeMap.keySet()) {
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next,indegreeMap.get(next) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroIn= new LinkedList<>();
        for (DirectedGraphNode cur : indegreeMap.keySet()) {
            if (indegreeMap.get(cur) == 0){
                zeroIn.add(cur);
            }
        }
        List<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroIn.isEmpty()){
            DirectedGraphNode cur = zeroIn.poll();
            ans.add(cur);
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next,indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0){
                    zeroIn.offer(next);
                }
            }
        }
        return ans;
    }
}
