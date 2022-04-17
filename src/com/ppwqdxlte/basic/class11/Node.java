package com.ppwqdxlte.basic.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/8/4 21:01
 * 图的结点，图有很多表示方法和结构，这个结点是左程云讲的比较好扩展的一种
 */
public class Node<T> {
    public T value;
    public List<Edge<T>> edges;  //指向此结点的邻结点
    public List<Node<T>> nexts;    //从当前节点出发的邻结点
    public int in;  //入度
    public int out; //出度
    public Node(){ }
    public Node(T v){
        value = v;
        edges = new ArrayList<>();
        nexts = new ArrayList<>();
        in = 0;
        out = 0;
    }
}
