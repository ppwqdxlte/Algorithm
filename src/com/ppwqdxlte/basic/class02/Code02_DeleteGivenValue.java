package com.ppwqdxlte.basic.class02;
import java.util.List;

import static com.ppwqdxlte.basic.class02.Code01_ReverseList.*;
/**
 * @author:李罡毛
 * @date:2021/7/9 0:38
 */
public class Code02_DeleteGivenValue {

    public static Code01_ReverseList.Node deleteGivenValueInLinkedList(Node head, int value){
        //找到第一个不为value的结点
        while (head!=null){
            if (head.value != value){
                break;
            }else {
                head = head.next;
            }
        }
        //删掉指定值的结点
        Node pre = head;
        Node cur = head;
        while (cur!=null){
            if (cur.value == value){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    public static DoubleNode deleteGivenValueInDoubleLinkedList(DoubleNode head,int value){
        //找到第一个不为value的结点作为head
        while (head!=null){
            if (head.value!=value){
                break;
            }else {
                head = head.next;
            }
        }
        //删掉所有值为value的结点
        DoubleNode pre = head;
        DoubleNode cur = head;
        while (cur!=null){
            if (cur.value == value){
                pre.next = cur.next;
                cur.last = null;
                cur.next.last = pre;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 100;
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node node1 = generateRandomLinkedList(maxSize,maxValue);
            List<Integer> node1Order = getLinkedListOriginOrder(node1);
            int value1 = node1Order.get((int)((node1Order.size())*Math.random()));
            node1 = deleteGivenValueInLinkedList(node1,value1);
            List<Integer> node1NewOrder = getLinkedListOriginOrder(node1);
            System.out.println("value is:\t"+value1);
            printNodeOrder(node1Order);
            printNodeOrder(node1NewOrder);
            System.out.println("-----------------------------------");
            DoubleNode doubleNode1 = generateDoubleRandomLinkedList(maxSize,maxValue);
            List<Integer> doubleNode1Order = getDoubleListOriginOrder(doubleNode1);
            int doubleValue1 = doubleNode1Order.get((int)((doubleNode1Order.size())*Math.random()));
            doubleNode1 = deleteGivenValueInDoubleLinkedList(doubleNode1,doubleValue1);
            List<Integer> doubleNode1NewOrder = getDoubleListOriginOrder(doubleNode1);
            System.out.println("value is:\t"+doubleValue1);
            printNodeOrder(doubleNode1Order);
            printNodeOrder(doubleNode1NewOrder);
            System.out.println("==========================================");
        }
    }

    public static void printNodeOrder(List<Integer> node1Order) {
        for (int i = 0; i < node1Order.size(); i++) {
            System.out.print(node1Order.get(i)+"\t");
        }
        System.out.println();
    }
}
