package com.ppwqdxlte.basic.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/7/12 12:40
 * 双向链表互转Node[]数组，快排逻辑没变！
 * 但我觉得这种方式很不优雅，依葫芦画瓢比较low，还是左程云的逼格高
 *
 */
public class Code08_DoubleLinkedList_ToArrayFakeQuickSort {

    public static class Node {
        public int value;
        public Node last;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node doubleLinkedListQuickSort(Node head){
        if (head == null) return null;
        if (head.last == null && head.next == null) return head;

        Node[] arr = linkedListToNodeArray(head);
        process(arr,0,arr.length - 1);

        Node newHead = nodeArrayToLinkedList(arr);
        return newHead;
    }
    public void process(Node[] arr,int L,int R){
        if (L >= R) return;// 重点，无此判断会死循环
        swap(arr,L + (int)((R - L + 1)*Math.random()),R);
        int[] equalArea = netherLandsFlag(arr,L,R);
        process(arr,L,equalArea[0] - 1);
        process(arr,equalArea[1] + 1,R);
    }
    public static void swap(Node[] arr,int x,int y){
        Node temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    public int[] netherLandsFlag(Node[] arr,int L,int R){
        if (L > R) return new int[]{-1,-1};
        if (L == R) return new int[]{L,L};
        int less = L - 1;
        int more = R;
        int i = L;
        while (i < more){
            if (arr[i].value == arr[R].value){
                i++;
            }else if (arr[i].value < arr[R].value){
                swap(arr,i++,++less);
            }else {
                swap(arr,i,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1,more};
    }

    /**链表转Node数组
     * @param head
     * @return
     */
    public static Node[] linkedListToNodeArray(Node head){
        if (head == null) return new Node[0];
        List<Node> nodes = new ArrayList<>();
        while (head != null){
            nodes.add(head);
            head = head.next;
        }
        return nodes.toArray(new Node[nodes.size()]);
    }
    /**Node数组转双向链表
     * @param
     * @return
     */
    public static Node nodeArrayToLinkedList(Node[] arr){
        if (arr == null || arr.length == 0) return null;
        Node pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i].last = pre;
            arr[i].next = null;
            pre.next = arr[i];
            pre = arr[i];
        }
        return arr[0];
    }
    /**for循环方式给双向链表排序
     * @param
     * @return
     */
    public static Node forLoopDoubleLinkedListSort(Node head){
        Node[] nodes = linkedListToNodeArray(head);
        for (int i = 0; i < nodes.length-1; i++) {
            for (int j = i; j < nodes.length; j++) {
                if (nodes[i].value > nodes[j].value){
                    swap(nodes,i,j);
                }
            }
        }
        Node newHead = nodeArrayToLinkedList(nodes);
        return newHead;
    }
    /**打印双向链表
     * @param
     */
    public static void printLinkedList(Node head){
        Node[] nodes = linkedListToNodeArray(head);
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].value+"\t");
        }
        System.out.println("\n------------------------");
    }
    /**判断两个双向链表是否相同【注意】不判断地址，只判断value、个数和顺序
     * @param
     * @return
     */
    public static boolean isEqualOfLinkedLists(Node head1,Node head2){
        Node[] nodes1 = linkedListToNodeArray(head1);
        Node[] nodes2 = linkedListToNodeArray(head2);
        if (nodes1 == null && nodes2 == null) return true;
        if (nodes1 != null && nodes2 == null) return false;
        if (nodes1 == null ) return false;
        if (nodes1.length != nodes2.length) return false;
        for (int i = 0; i < nodes1.length; i++) {
            if (nodes1[i].value != nodes2[i].value) return false;
        }
        return true;
    }
    /**生成随机双向链表
     * @param
     * @return
     */
    public static Node generateRandomDoubleLinkedList(int maxSize,int maxValue){
        int randSize = (int) ((maxSize+1)*Math.random());
        Node head = null;
        Node pre = null;
        for (int i = 0; i < randSize; i++) {
            Node randNode = new Node((int) ((maxValue+1)*Math.random()) - (int) ((maxValue+1)*Math.random()));
            randNode.last = pre;
            randNode.next = null;
            if (i == 0) {
                head = randNode;
            }
            else pre.next = randNode;
            pre = randNode;
        }
        return head;
    }
    /**复制双向链表
     * @param
     * @return
     */
    public static Node copyOfLinkedList(Node head){
        Node[] nodes = linkedListToNodeArray(head);//有妖气
        Node[] nodes2= new Node[nodes.length];
        for (int i = 0; i < nodes2.length; i++) {
            nodes2[i] = new Node(nodes[i].value);
        }
        return nodeArrayToLinkedList(nodes2);
    }
    public static void main(String[] args) {
        Code08_DoubleLinkedList_ToArrayFakeQuickSort test = new Code08_DoubleLinkedList_ToArrayFakeQuickSort();
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head1 = generateRandomDoubleLinkedList(maxSize,maxValue);
            Node head2 = copyOfLinkedList(head1);
            /*if (i<10){
                printLinkedList(head1);
            }*/
            Node newHead1 = test.doubleLinkedListQuickSort(head1);
            Node newHead2 = test.forLoopDoubleLinkedListSort(head2);
            if (newHead1 == null && newHead2 == null) continue;
            if (newHead1.value != newHead2.value){
                printLinkedList(newHead1);
                printLinkedList(newHead2);
                isSuccess = false;
                break;
            }
           /*if (i<10){
                printLinkedList(newHead1);
                printLinkedList(newHead2);
            }*/
        }
        System.out.println(isSuccess?"No problem!":"Failed");
    }
}
