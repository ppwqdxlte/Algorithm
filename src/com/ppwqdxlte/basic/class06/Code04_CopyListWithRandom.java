package com.ppwqdxlte.basic.class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/7/20 22:54
 * 【提问】复制带有随机指针的链表
 */
public class Code04_CopyListWithRandom {

    public class Node{
        public int value;
        public Node next;
        public Node random;
        public Node(int v){
            value = v;
        }
    }

    public Node copyListWithRandom1(Node head){
        if (head == null) return null;
        //保存新结点和旧结点 键值对
        HashMap<Node,Node> oriNewPairs = new HashMap<>();
        Node cur = head;
        while (cur != null){
            Node copy = new Node(cur.value);
            oriNewPairs.put(cur,copy);
            cur = cur.next;
        }
        for (Node ori: oriNewPairs.keySet()) {
            oriNewPairs.get(ori).next = oriNewPairs.get(ori.next);
            oriNewPairs.get(ori).random = oriNewPairs.get(ori.random);
        }
        return oriNewPairs.get(head);
    }
    // split
    public Node copyListWithRandom2(Node head){
        if (head == null) return null;
        // copy node and link to every node:  1 -> 2 ==> 1 -> 1' -> 2
        Node ori = head;
        Node copy = null;
        while (ori != null){
            copy = new Node(ori.value);
            Node next = ori.next;
            ori.next = copy;
            copy.next = next;
            ori = next;
        }
        // set copy node rand : 1 -> 1' -> 2 -> 2' [1']'s rand is found by [1]'s rand
        ori = head;
        copy = head.next;
        while (copy != null){
            if (ori.random != null){
                copy.random = ori.random.next;
            }
            ori = copy.next;
            if (copy.next == null) break;
            copy = copy.next.next;
        }
        //split
        Node newHead = head.next;//先码住 新头结点

        ori = head;
        copy = head.next;
        Node next = copy.next;//旧结点下一个结点
        ori.next = next;
        ori = next;
        Node pre = copy;
        if (ori != null){
            copy = ori.next;
        }
        while (ori != null && copy != null){
            next = copy.next;
            ori.next = next;
            ori = next;

            copy.next = null;
            pre.next = copy;
            pre = copy;

            if (ori != null)
            copy = ori.next;
        }

        return newHead;
    }

    //For test
    public void printRandLinkedList(Node head) {
        System.out.println("------------------------------");

        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + "\t");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "\t" : cur.value+"/"+cur.random.value + "\t");
            cur = cur.next;
        }
        System.out.println();
    }
    //For test
    public Node generateLinkedListWithRandom(int maxSize,int maxValue){
        int size = (int)((maxSize + 1)*Math.random());
        if (size == 0) return null;
        Node head = new Node((int)((maxValue + 1)*Math.random()) - (int)((maxValue + 1)*Math.random()));
        Node pre = head;
        Node[] arr = new Node[size];
        arr[0] = head;
        for (int i = 1; i < size; i++) {
            Node cur = new Node((int)((maxValue + 1)*Math.random()) - (int)((maxValue + 1)*Math.random()));
            pre.next = cur;
            pre = cur;
            arr[i] = cur;
        }
        double rate = 0;
        for (int i = 0; i < size; i++) {
            rate = Math.random();
            if (rate > 0.5){
                arr[i].random = arr[(int)(size*Math.random())];
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Code04_CopyListWithRandom test = new Code04_CopyListWithRandom();
        int maxSize = 20;
        int maxValue = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node head = test.generateLinkedListWithRandom(maxSize, maxValue);
            test.printRandLinkedList(head);
            Node head1 = test.copyListWithRandom1(head);
            Node head2 = test.copyListWithRandom2(head);
            test.printRandLinkedList(head1);
            test.printRandLinkedList(head2);
        }
    }

}
