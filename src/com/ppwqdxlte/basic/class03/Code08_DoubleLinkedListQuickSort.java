package com.ppwqdxlte.basic.class03;

import static com.ppwqdxlte.basic.class03.Code08_DoubleLinkedList_ToArrayFakeQuickSort.*;

/**
 * @author:李罡毛
 * @date:2021/7/12 17:33
 * 左程云写的双向链表快排
 *
 * // 双向链表的随机快速排序
 * // 课上没有讲，因为这是群里同学问的问题
 * // 作为补充放在这，有需要的同学可以看看
 * // 和课上讲的数组的经典快速排序在算法上没有区别
 * // 但是coding需要更小心
 */
public class Code08_DoubleLinkedListQuickSort {

    /**双向链表的快排方法
     * @param h 待排序的双向链表头结点
     * @return 排好序的双向链表头结点
     */
    public Node quickSort(Node h){
        if (h == null) return null;
        int N = 0;
        Node c = h;
        Node e = null;
        while (c != null){
            N ++;
            e = c;
            c = c.next;
        }
        return process(h,e,N).h;
    }
    // L...R是一个双向链表的头和尾,
    // L的last指针指向null，R的next指针指向null
    // 也就是说L的左边没有，R的右边也没节点
    // 就是一个正常的双向链表，一共有N个节点
    // 将这一段用随机快排的方式排好序
    // 返回排好序之后的双向链表的头和尾(HeadTail)
    public HeadTail process(Node L,Node R,int N){
        if (L == null) return null;
        if (L == R) return new HeadTail(L,R);

        int randIndex = (int)(N*Math.random());
        Node randomNode = L;
        while (randIndex-- != 0){
            randomNode = L.next;
        }
//        System.out.println(randNode.value+"\t"+L.value+"\t"+R.value);
        // 把随机节点从原来的环境里分离出来
        // 比如 a(L) -> b -> c -> d(R), 如果randomNode = c，那么调整之后
        // a(L) -> b -> d(R), c会被挖出来，randomNode = c
        if (randomNode == L || randomNode == R) {
            if (randomNode == L) {
                L = randomNode.next;
                L.last = null;
            } else {
                randomNode.last.next = null;
            }
        } else { // randomNode一定是中间的节点
            randomNode.last.next = randomNode.next;
            randomNode.next.last = randomNode.last;
        }
        randomNode.last = null;
        randomNode.next = null;

        Info info = partition(L,randomNode);

        HeadTail lht = process(info.lh,info.lt,info.ls);
        HeadTail rht = process(info.rh,info.rt,info.rs);

        // 左部分排好序、右部分排好序
        // 把它们串在一起
        if (lht != null) {
            lht.t.next = info.eh;
            info.eh.last = lht.t;
        }
        if (rht != null) {
            info.et.next = rht.h;
            rht.h.last = info.et;
        }
        // 返回排好序之后总的头和总的尾
        Node h = lht != null ? lht.h : info.eh;
        Node t = rht != null ? rht.t : info.et;
        return new HeadTail(h, t);
    }
    public static class HeadTail{
        public Node h;
        public Node t;
        public HeadTail(Node h,Node t){
            this.h = h;
            this.t = t;
        }
    }
    public static class Info{
        public Node lh;
        public Node lt;
        public int ls;
        public Node eh;
        public Node et;
        public Node rh;
        public Node rt;
        public int rs;
        public Info(Node lh,Node lt,int ls,Node eh,Node et,Node rh,Node rt,int rs){
            this.lh = lh;
            this.lt = lt;
            this.ls = ls;
            this.eh = eh;
            this.et = et;
            this.rh = rh;
            this.rt = rt;
            this.rs = rs;
        }
    }
    // (L....一直到空)，是一个双向链表
    // pivot是一个不在(L....一直到空)的独立节点，它作为划分值
    // 根据荷兰国旗问题的划分方式，把(L....一直到空)划分成:
    // <pivot 、 =pivot 、 >pivot 三个部分，然后把pivot融进=pivot的部分
    // 比如 4(L)->6->7->1->5->0->9->null pivot=5(这个5和链表中的5，是不同的节点)
    // 调整完成后:
    // 4->1->0 小于的部分
    // 5->5 等于的部分
    // 6->7->9 大于的部分
    // 三个部分是断开的
    // 然后返回Info：
    // 小于部分的头、尾、节点个数 : lh,lt,ls
    // 大于部分的头、尾、节点个数 : rh,rt,rs
    // 等于部分的头、尾 : eh,et
    public Info partition(Node L,Node randNode){
        if (L == null) return null;
        Node lh = null,lt = null,rh = null,rt = null,eh = randNode,et = randNode;
        int ls = 0,rs = 0;
        Node temp = L;
        while (temp != null){
            Node next = temp.next;
            if (temp.value < randNode.value){
                ls ++;
                if (lh == null){
                    lh = temp;
                    lt = temp;
                    lh.last = null;
                    lh.next = null;
                }else {
                    lt.next = temp;
                    temp.last = lt;
                    temp.next = null;
                    lt = temp;
                }
            }else if (temp.value > randNode.value){
                rs ++;
                if (rh == null){
                    rh = temp;
                    rt = temp;
                    rh.last = null;
                    rh.next = null;
                }else {
                    rt.next = temp;
                    temp.last = rt;
                    temp.next = null;
                    rt = temp;
                }
            }else {
                et.next = temp;
                temp.last = et;
                temp.next = null;
                et = temp;
            }
            temp = next;
        }
        return new Info(lh,lt,ls,eh,et,rh,rt,rs);
    }

    public static void main(String[] args) {
        Code08_DoubleLinkedListQuickSort test = new Code08_DoubleLinkedListQuickSort();
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head1 = generateRandomDoubleLinkedList(maxSize,maxValue);
            Node head2 = copyOfLinkedList(head1);
            if (i<10){
                printLinkedList(head1);
            }
            Node newHead1 = test.quickSort(head1);
            Node newHead2 = forLoopDoubleLinkedListSort(head2);
            if (newHead1 == null && newHead2 == null) continue;
            if (newHead1.value != newHead2.value){
                printLinkedList(newHead1);
                printLinkedList(newHead2);
                isSuccess = false;
                break;
            }
           if (i<10){
                printLinkedList(newHead1);
                printLinkedList(newHead2);
               System.out.println("~~~~~~~~~~~~~~~~~~~");
            }
        }
        System.out.println(isSuccess?"No problem!":"Failed");
    }
}
