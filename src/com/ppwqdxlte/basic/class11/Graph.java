package com.ppwqdxlte.basic.class11;

import java.util.HashSet;
import java.util.IdentityHashMap;

/**
 * @author:李罡毛
 * @date:2021/8/4 21:28
 * 图，这是左老师写的方便易懂的表示结构
 * 【但是！】我稍微改动了nodes的数据结构！！！不能用HashMap，因为它的key是值判断！
 * 我要的是引用判断！所以用IdentityHashMap最好最合适，
 * 【举例】整数100的2个不同包装对象，如果是HashMap自动认为此两对象相同，从而只会添加一个，
 * 而用IdentityHashMap就会把两个key都添加进去，从而不会丢失数据
 */
public class Graph<T> {
    public IdentityHashMap<T,Node<T>> nodes;
    public HashSet<Edge<T>> edges;
    public Graph(){
        nodes = new IdentityHashMap<>();
        edges = new HashSet<>();
    }
}
