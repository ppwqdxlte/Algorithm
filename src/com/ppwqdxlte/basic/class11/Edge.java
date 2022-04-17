package com.ppwqdxlte.basic.class11;

/**
 * @author:李罡毛
 * @date:2021/8/4 21:23
 * 图的组成之一，边，可以有很多种结构表示，但是左程云写的比较好扩展，方便易懂
 */
public class Edge<T> {
    public int weight;
    public Node<T> from;
    public Node<T> to;
    public Edge(int w,Node<T> f,Node<T> t){
        weight = w;
        from = f;
        to = t;
    }
}
