package com.ppwqdxlte.basic.class06;

import java.util.HashSet;

import static com.ppwqdxlte.basic.class06.Code01_LinkedListMid.*;
/**
 * @author:李罡毛
 * @date:2021/7/21 13:48
 *【提问】：两条 链表，可能有环，也可能没环，如果相交，求出第一个交点
 * 思路：先判断有没有环，分几种情形；再判断 各种情形下相交的问题
 *      1、甲乙都是直链，可能相交，可能不相交；
 *      2、甲乙一个是直链，一个有环，直链终点指向null，而闭环出不去不存在终点，这种情况不可能相交；
 *      3、甲乙都有环，因为结点只有一个next指针，故而要么不相交，要么相交且共享一个环结构，
 *          3-1、在直链上相交；
 *          3-2、在环上相交。
 */
public class Code05_FindFirstIntersectNode {
    /**主逻辑
     * @param head1
     * @param head2
     * @return
     */
    public Node getIntersectNode(Node head1,Node head2){
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 == null && loopNode2 == null)
            return findFirstIntersectNode1(head1,head2);
        if (loopNode1 != null && loopNode2 != null)
            return findFirstIntersectNode3(head1,head2,loopNode1,loopNode2);
        return null;
    }
    /**找到链表第一个入环节点，如果无环，返回null
     * 【注意！！！】debug表明，环上 2^? 个值时候，f指针无限循环，可能错过入环点！
     * 思路：直链上（包含入环点）奇数个，下述返回值.next为修正值；
     *                  偶数个，则下述返回值无误
     * @param head
     * @return
     */
    public Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null) return null;
//        HashSet<Node> nodes = new HashSet<>();这方法是最稳的，以后再修正
        Node s = head;
        Node f = head.next;
        while (f != null){
            s = s.next;
            if (f.next == null) return null;//结尾指向null必须没交点
            f = f.next.next;
            if (s == f) return s;//链长N*2的话，快慢指针总会相会，慢跑一圈，快跑两圈
        }
        return null;
    }
    /**甲乙直链无环
     * @param head1
     * @param head2
     * @return
     */
    public Node findFirstIntersectNode1(Node head1,Node head2){
        if (head1 == null || head2 == null) return null;
        int size1 = 0,size2 = 0;
        Node cur1 = head1;
        while (cur1 != null){
            size1 ++;
            if (cur1.next == null) break;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        while (cur2 != null){
            size2 ++;
            if (cur2.next == null) break;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) return null;//结尾都不一样就没有交点了
        cur1 = size1 >= size2 ? head1 : head2;//较长链
        cur2 = cur1 == head1 ? head2 : head1;//较短链
        for (int i = 0; i < Math.abs(size1 - size2); i++) {
            cur1 = cur1.next;
        }
        //此时长链剩余长度和短链一致
        while (cur1 != null){
            if (cur1 == cur2) return cur1;//找到交点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }
    /**甲乙都有环，交点有两种，1、直链上 2、环上
     * @param head1
     * @param head2
     * @return
     */
    public Node findFirstIntersectNode3(Node head1,Node head2,Node loopNode1,Node loopNode2){
        if (loopNode1 == loopNode2){//交点在直链上，不用管后边的环了
            int size1 = 0,size2 = 0;
            Node cur1 = head1;
            while (cur1 != loopNode1){
                size1 ++;
                cur1 = cur1.next;
            }
            Node cur2 = head2;
            while (cur2 != loopNode1){
                size2 ++;
                cur2 = cur2.next;
            }
            cur1 = size1 >= size2 ? head1 : head2;//较长链
            cur2 = cur1 == head1 ? head2 : head1;//较短链
            for (int i = 0; i < Math.abs(size1 - size2); i++) {
                cur1 = cur1.next;
            }
            //此时长链剩余长度和短链一致
            while (cur1 != loopNode1){
                if (cur1 == cur2) return cur1;//找到交点
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return loopNode1;
        }
        //要么不相交，要么相交且 loopNode1(其实另一个入环点也对，不过以左链为主吧)
        Node cur = loopNode1.next;
        while (cur != loopNode1){
            if (cur == loopNode2) return loopNode1;
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Code05_FindFirstIntersectNode test = new Code05_FindFirstIntersectNode();
        //两条直链相交
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        /*n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);

        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = n1.next.next; //4 -> 3
        System.out.println(test.getIntersectNode(n1,n2).value);
        //两条直链不相交
        n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);
        n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = new Node(5);
        System.out.println(test.getIntersectNode(n1,n2));
        //两个环，不相交
        n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = n1.next.next;//123453...3
        n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = n2.next;//12342....2
        System.out.println(test.getIntersectNode(n1,n2));
        //两个环，相交于直链部分
        n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = n1.next.next;//123453...3
        n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = n1.next;//12342'3'4'5'3'...3'
        System.out.println(test.getIntersectNode(n1,n2).value);
        System.out.println(test.getLoopNode(n1).value);*/
        //两个环相交，入环点不一样
        n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);
        n1.next.next.next.next.next.next = new Node(7);
        n1.next.next.next.next.next.next.next = n1.next.next.next;//7->4
//        System.out.println(test.getLoopNode(n1).value);//4
        n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = n1.next.next.next.next.next;//4->6'
//        System.out.println(test.getIntersectNode(n1,n2).value);//4
        System.out.println(test.getLoopNode(n2).value);//5 bug 应该是6，debug表明，环上 2^? 个值时候，f指针无限循环，可能错过入环点！
    }

}
