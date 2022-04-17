package com.ppwqdxlte.basic.class11;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Stack;

import static com.ppwqdxlte.basic.class11.GraphGenerator.*;
import static com.ppwqdxlte.basic.class11.Code01_BFS.*;
/**
 * @author:李罡毛
 * @date:2021/8/6 17:44
 * 深度优先搜索 DFS 即 Depth-First Search
 */
public class Code02_DFS {

    public static void depthFirstPrintTree(Node<Integer> head){
        if (head == null) return;
        System.out.println("-----------------------------------");
        Stack<Node<Integer>> stack = new Stack<>();
        HashSet<Node<Integer>> addedNodes = new HashSet<>();
        stack.push(head);
        addedNodes.add(head);
        Node<Integer> c = null;
        while (!stack.isEmpty()){
            c = stack.pop();
            for (Node<Integer> next : c.nexts) {
                if (!addedNodes.contains(next)){
                    stack.add(next);
                    addedNodes.add(next);
                }
            }
            System.out.println(c.value);
        }
    }

    public static void depthFirstPrintForest(Graph<Integer> graph){
        if (graph == null) return;
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        HashSet<Node<Integer>> addedNodes = new HashSet<>();
        for (Node<Integer> tree : graph.nodes.values()){
            if (addedNodes.contains(tree)) continue;
            System.out.println("-----------------------------------");//跟宽度遍历不一样，外层迭代不一定代表整棵树，也许就是一部分
            Stack<Node<Integer>> stack = new Stack<>();
            stack.push(tree);
            addedNodes.add(tree);
            Node<Integer> c = null;
            while (!stack.isEmpty()){
                c = stack.pop();
                for (Node<Integer> next: c.nexts) {
                    if (!addedNodes.contains(next)){
                        stack.add(next);
                        addedNodes.add(next);
                    }
                }
                System.out.println(c.value);
            }
        }
        System.out.println("森林里一共有这么多个结点：" + addedNodes.size());
    }

    public static void main(String[] args) {
        int treeLimit = 10;
        int leafLimit = 20;
        int valueLimit = 200;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            try {
                Graph<Integer> graph = generateUndirectedGraph(treeLimit,leafLimit,valueLimit);
                System.out.println("BREADTH:");
                breadthFirstPrintForest(graph);
                System.out.println("DEPTH:");
                depthFirstPrintForest(graph);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
