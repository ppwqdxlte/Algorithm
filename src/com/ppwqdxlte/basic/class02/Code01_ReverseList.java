package com.ppwqdxlte.basic.class02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/7/8 16:49
 */
public class Code01_ReverseList {

    public static class Node{
        public int value;
        public Node next;
        public int getValue() {
            return value;
        }
        public Node(int value){
            this.value = value;
        }
    }
    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public int getValue() {
            return value;
        }
        public DoubleNode(int value){
            this.value = value;
        }
    }
    /**单向链表的翻转
     * @param head
     * @return 翻转后返回的头部
     */
    public static Node reverseLinkedList(Node head){
        Node originHead = null;//链表原本的head
        Node futureHead = null;//链表原本的头部的next节点作为将来的new head
        while (head != null){
            futureHead = head.next;
            head.next = originHead;
            originHead = head;
            head = futureHead;
        }
        return originHead;
        /*head = head.next; //不会带到方法外2,3,4,null
        head.next = head.next.next;//会带出方法外，影响参数属性！
        return head;//如果代码这样，那node1的value属性真的被修改成100了，对引用型传参的属性的修改是不可逆的，但是对于传参本身的引用的修改，并不会带到方法外！*/
    }
    /** 双向链表的翻转
     * @param head
     * @return 翻转后新的头结点
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
        DoubleNode originHead = null;
        DoubleNode futureHead = null;
        while (head != null){
            futureHead = head.next;
            head.next = originHead;
            head.last = futureHead;
            originHead = head;
            head = futureHead;
        }
        return originHead;
    }

    /**普通翻转单向链表的方法
     * @param head
     * @return
     */
    public static Node normalReverseLinkedList(Node head){
        if (head == null) return null;
        List<Node> originList = new ArrayList<>();
        while (head!=null){
            originList.add(head);
            head = head.next;
        }
        originList.get(0).next = null;
        int N = originList.size();
        for (int i = 1; i < N ; i++) {
            originList.get(i).next = originList.get(i-1);
        }
        return originList.get(N-1);
    }
    /**普通翻转双向链表的方法
     * @param head
     * @return
     */
    public static DoubleNode normalReverseDoubleLinkedList(DoubleNode head){
        if (head == null) return null;
        List<DoubleNode> originList = new ArrayList<>();
        while (head!=null){
            originList.add(head);
            head = head.next;
        }

        int N = originList.size();
        originList.get(0).next = null;
        DoubleNode lastNode = originList.get(0);
        for (int i = 1; i < N; i++) {
            originList.get(i).next = lastNode;
            lastNode.last = originList.get(i);
            lastNode = originList.get(i);
            originList.get(i).last = null;
        }
        return originList.get(N-1);
    }
    /**获得单向链表的没有翻转之前顺序
     * @param head
     * @return
     */
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }
    /**获得双向链表没有翻转之前的顺序
     * @param head
     * @return
     */
    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    /**检查单向链表是否已经成功翻转
     * @param originOrder 翻转之前的顺序
     * @param reversedHead 翻转后的链表head
     * @return 翻转结果是否成功
     */
    public static boolean checkLinkedListReversion(List<Integer> originOrder,Node reversedHead){
        for (int i = originOrder.size()-1; i >= 0 ; i--) {
            if (!originOrder.get(i).equals(reversedHead.getValue())) return false;
            reversedHead = reversedHead.next;
        }
        return true;
    }

    /**检查双向链表是否已经成功翻转
     * @param originOrder 翻转之前的顺序
     * @param reversedHead 翻转后的链表head
     * @return 翻转结果是否成功
     */
    public static boolean checkDoubleLinkedListReversion(List<Integer> originOrder,DoubleNode reversedHead){
        DoubleNode tail = null;
        for (int i = originOrder.size()-1; i >= 0; i--) {
            if (!originOrder.get(i).equals(reversedHead.getValue())) return false;
            tail = reversedHead;
            reversedHead = reversedHead.next;
        }
        for (int i = 0; i < originOrder.size(); i++) {
            if (!originOrder.get(i).equals(tail.getValue())) return false;
            tail = tail.last;
        }
        return true;
    }

    /**生成随机的单向链表
     * @param maxSize 不超过的最大样本规模
     * @param maxValue 样本不超过此最大值
     * @return 单向链表头部
     */
    public static Node generateRandomLinkedList(int maxSize,int maxValue){
        Node head = new Node((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random()));
        Node pre = head;
        int size = (int)((maxSize+1)*Math.random());
        for (int i = 1; i < size; i++) {
            Node cur = new Node((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random()));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    /**生成随机的双向链表
     * @param maxSize 不超过的最大样本规模
     * @param maxValue 样本不超过此最大值
     * @return 双向链表头部
     */
    public static DoubleNode generateDoubleRandomLinkedList(int maxSize,int maxValue){
        DoubleNode head = new DoubleNode((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random()));
        DoubleNode pre = head;
        int size = (int)((maxSize+1)*Math.random());
        for (int i = 1; i < size; i++) {
            DoubleNode cur = new DoubleNode((int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random()));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
        }
        return head;
    }
    
    public static void main(String[] args) {
        /*Node node1 = new Node(1);Node node2 = new Node(2);Node node3 = new Node(3);Node node4 = new Node(4);
        node1.next = node2;node2.next = node3;node3.next = node4;node4.next = null;
        Node node01 = node1;//复制node1的引用给新的node01
        System.out.println(node01);//地址@7ef20235
        System.out.println(node1.getValue());                       //1
        System.out.println(reverseLinkedList(node1).getValue());    //4
//        System.out.println(node1.next.next.getValue());             //修改翻转方法验证值传递性，发现结果变成了4
        System.out.println(node1.getValue());                       //1 常数属性值不变
        System.out.println(node1 == node01);                        //引用型参数的引用也是值传递！！出了方法也没有变！！
        System.out.println(node1);//@7ef20235
        System.out.println(node4.next.getValue());                  //3

        node1 = node2;//把2的内存物理地址给1
        node2 = node3;//把3的内存物理地址给2
        System.out.println(node1 == node3);//1，3指向的实际地址不一样
        System.out.println(node1.getValue());//2
        System.out.println(node2.getValue());//3
        System.out.println(node3.getValue());//3

        DoubleNode doubleNode1 = new DoubleNode(1);DoubleNode doubleNode2 = new DoubleNode(2);DoubleNode doubleNode3 = new DoubleNode(3);DoubleNode doubleNode4 = new DoubleNode(4);DoubleNode doubleNode5 = new DoubleNode(5);
        doubleNode1.next = doubleNode2;doubleNode2.last = doubleNode1;doubleNode2.next = doubleNode3;doubleNode3.last = doubleNode2;doubleNode3.next = doubleNode4;doubleNode4.last = doubleNode3;doubleNode4.next = doubleNode5;
        System.out.println(reverseDoubleLinkedList(doubleNode1).getValue());//5
        System.out.println(reverseDoubleLinkedList(doubleNode5).getValue());//1
        System.out.println(reverseDoubleLinkedList(doubleNode1).next.getValue());//4
        System.out.println("----------------------------------------------------");
        System.out.println(normalReverseLinkedList(node4).getValue());
        System.out.println(normalReverseDoubleLinkedList(doubleNode5).getValue());
        System.out.println("----------------------------------------------------");
        System.out.println(checkLinkedListReversion(getLinkedListOriginOrder(node1),reverseLinkedList(node1)));
        System.out.println(checkDoubleLinkedListReversion(getDoubleListOriginOrder(doubleNode1),reverseDoubleLinkedList(doubleNode1)));
*/


        int len = 50;
        int value = 100;
        int testTime = 2000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!checkLinkedListReversion(list1, node1)) {
                System.out.println("Oops1!");
            }

            Node node2 = generateRandomLinkedList(len, value);
            List<Integer> list2 = getLinkedListOriginOrder(node2);
            node2 = normalReverseLinkedList(node2);
            if (!checkLinkedListReversion(list2, node2)) {
                System.out.println("Oops2!");
            }

            DoubleNode node3 = generateDoubleRandomLinkedList(len, value);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseDoubleLinkedList(node3);
            if (!checkDoubleLinkedListReversion(list3, node3)) {
                System.out.println("Oops3!");
            }

            DoubleNode node4 = generateDoubleRandomLinkedList(len, value);
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            node4 = normalReverseDoubleLinkedList(node4);
            if (!checkDoubleLinkedListReversion(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("test finish!");

    }

}
