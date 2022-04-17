package com.ppwqdxlte.basic.class06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author:李罡毛
 * @date:2021/7/18 15:33
 * 【提问】找到一个单向链表，当结点为？找到？
 * 1.。。奇数----中点，      偶数----上中点；
 * 2.。。奇数----中点，      偶数----下中点；
 * 3.。。奇数----中点前一个点，偶数----上中点前一个点
 * 4.。。奇数----中点前一个点，偶数----下中点前一个点
 */
public class Code01_LinkedListMid {
    public static class Node{
        public int value;
        public Node next;
        public Node(int v){
            value = v;
        }
    }
    /**
     * @param head 链表
     * @return 中点或上中点
     */
    public Node midOrUpMid(Node head){
        if (head == null || head.next ==  null || head.next.next == null) return head;
        // 链表有3个点或以上
        Node s = head.next;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    /**
     * @param head 链表
     * @return 中点或下中点
     */
    public Node midOrDownMid(Node head){
        if (head == null || head.next == null) return head;
        Node s = head;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null){
            s = s.next;
            f = f.next.next;
        }
        if (f.next != null && f.next.next == null){
            s = s.next;
        }
        return s.next;
    }

    /**
     * @param head 链表
     * @return 中点或者上中点 的前一个点
     */
    public Node midOrUpMidPre(Node head){
        if (head == null || head.next == null || head.next.next == null) return null;
        //至少3个结点
        Node s = head.next;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null){
            if (f.next.next.next == null
                    || f.next.next.next.next == null) break;
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    /**
     * @param head 链表
     * @return 中点或者下中点 的前一个点
     */
    public Node midOrDownMidPre(Node head){
        if (head == null || head.next == null) return null;
        if (head.next.next == null) return head;
        //至少3个结点
        Node s = head;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null){
            if (f.next.next.next == null) break;
            s = s.next;
            f = f.next.next;
        }
        return s.next;
    }
    //For test 以下是上述四个方法的对照方法
    public Node right1(Node head){
        if (head == null || head.next == null || head.next.next == null) return head;
        List<Node> nodeList = linkedListToArrayList(head);
        return nodeList.get((nodeList.size()-1)>>1);
    }
    public Node right2(Node head){
        if (head == null || head.next == null) return head;
        List<Node> nodeList = linkedListToArrayList(head);
        return nodeList.get(nodeList.size()>>1);
    }
    public Node right3(Node head){
        if (head == null || head.next == null || head.next.next == null) return null;
        List<Node> nodeList = linkedListToArrayList(head);
        return nodeList.get(((nodeList.size()-1)>>1) - 1);
    }
    public Node right4(Node head){
        if (head == null || head.next == null) return null;
        if (head.next.next == null) return head;
        List<Node> nodeList = linkedListToArrayList(head);
        return nodeList.get((nodeList.size()>>1) - 1);
    }

    //For test
    public static List<Node> linkedListToArrayList(Node head){
        List<Node> nodeList = new ArrayList<>();
        Node cur = head;
        while (cur != null){
            nodeList.add(cur);
            cur = cur.next;
        }
        return nodeList;
    }
    //For test
    public static Node[] linkedListToArray(Node head){
        List<Node> nodeList = linkedListToArrayList(head);
        Node[] nodeArr = new Node[nodeList.size()];
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(nodeList.get(i).value);
        }
        return nodeArr;
    }
    //For test
    public static Node nodeArrToLinkedList(Node[] arr){
        Node cur = arr[0];
        for (int i = 1; i < arr.length; i++) {
            cur.next = arr[i];
            cur = arr[i];
        }
        return arr[0];
    }
    //For test
    public static void printLinkedList(Node node) {
        Node cur = node;
        System.out.print("Linked List: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    //For test
    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }
    //For test
    public static Node copyLinkedList(Node head){
        if (head == null) return null;
        Node newHead = new Node(head.value);
        Node ori = head;
        Node cur = newHead;
        Node pre = null;
        Node next = null;
        while (ori != null){
            pre = cur;
            next = ori.next;
            if (next != null){
                pre.next = new Node(next.value);
            }
            cur = pre.next;
            ori = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Code01_LinkedListMid t = new Code01_LinkedListMid();
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
//        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = t.midOrUpMid(test);
        ans2 = t.right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = t.midOrDownMid(test);
        ans2 = t.right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = t.midOrUpMidPre(test);
        ans2 = t.right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = t.midOrDownMidPre(test);//偶数个少1，奇数多一个
        ans2 = t.right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
    }
}
