package com.ppwqdxlte.basic.class09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/27 22:27
 * 【提问】一个二叉树里，任意两个点，找出它俩最低父节点
 */
public class Code05_LowestAncestor {

    public Node lowestAncestor1(Node head,Node o1,Node o2){
        if (head == null) return null;
        HashMap<Node,Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        fillParentMap(head,parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null){
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)){
            cur = parentMap.get(cur);
        }
        return cur;
    }
    private void fillParentMap(Node head,HashMap<Node,Node> parentMap){
        if (head.left != null){
            parentMap.put(head.left,head);
            fillParentMap(head.left,parentMap);
        }
        if (head.right != null){
            parentMap.put(head.right,head);
            fillParentMap(head.right,parentMap);
        }
    }

    private static class Info{
        public boolean findA;
        public boolean findB;
        public Node ans;
        public Info(){}
        public Info(boolean fa,boolean fb,Node an){
            findA = fa;
            findB = fb;
            ans = an;
        }
    }
    public Node lowestAncestor2(Node head,Node o1,Node o2){
        return process(head,o1,o2).ans;
    }
    private Info process(Node X,Node o1,Node o2){
        if (X == null) return new Info(false,false,null);
        Info il = process(X.left,o1,o2);
        Info ir = process(X.right,o1,o2);
        boolean findA = X == o1 || il.findA || ir.findA;
        boolean findB = X == o2 || il.findB || ir.findB;
        Node ans = null;
        if (il.ans != null){
            ans = il.ans;
        }
        if (ir.ans != null){
            ans = ir.ans;
        }
        if (il.ans == null && ir.ans == null && findA && findB){
            ans = X;
        }
        return new Info(findA,findB,ans);
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        Code05_LowestAncestor test = new Code05_LowestAncestor();

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            Node la1 = test.lowestAncestor1(head, o1, o2);
            Node la2 = test.lowestAncestor2(head, o1, o2);
            if (la1 != la2) {
                System.out.println("Oops!");
                System.out.println(o1.value+"\t"+o2.value);
                printTree(head);
                printTree(la1);
                printTree(la2);
            }
        }
        System.out.println("finish!");
    }
}
