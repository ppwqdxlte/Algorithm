package com.ppwqdxlte.basic.class06;

import static com.ppwqdxlte.basic.class06.Code01_LinkedListMid.*;

/**
 * @author:李罡毛
 * @date:2021/7/20 14:23
 * 【提问】单向链表，给个 pivot中轴 值，Node value 比它小放左边，等于放中间，大于放右边
 *  使用两种算法：
 *      1、荷兰国旗式，链转数组
 *      2、三条链表分别存储 小 等 大
 */
public class Code03_SmallerEqualBigger {

    public Node listPartition1(Node head,int pivot){
        if (head == null || head.next == null) return head;
        Node[] nodes = linkedListToArray(head);
        arrPartition(nodes,pivot);
        return nodeArrToLinkedList(nodes);
    }
    public void arrPartition(Node[] nodes,int pivot){
        if (nodes == null || nodes.length == 0) return;
        int less = -1;
        int more = nodes.length;
        int i = 0;
        while (i < more){
            if (nodes[i].value < pivot){
                swap(nodes,++less,i++);
            }else if (nodes[i].value > pivot){
                swap(nodes,--more,i);
            }else {
                i++;
            }
        }
    }
    public Node listPartition2(Node head,int pivot){
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        Node next = null; // save next node
        // every node distributed to three lists
        while (head != null){
            next = head.next;
            head.next = null;
            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = sH;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = eH;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = mH;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        head = sH != null ? sH : eH != null ? eH : mH;
        if (sH != null) sT.next = eH != null ? eH : mH;
        if (eH != null) eT.next = mH;
        return head;
    }

    public static void main(String[] args) {
        Code03_SmallerEqualBigger test = new Code03_SmallerEqualBigger();
        // 7    9   1   8   5   2   5
        Node head = new Node(7);
        head.next = new Node(9);
        head.next.next = new Node(1);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(5);
        Node head1 = copyLinkedList(head);
        Node head2 = copyLinkedList(head);

        printLinkedList(head);
        head1 = test.listPartition1(head1, 5);
        head2 = test.listPartition2(head2, 6);
        printLinkedList(head1);
        printLinkedList(head2);
    }
}
