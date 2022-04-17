package com.ppwqdxlte.basic.class06;

import java.util.List;
import java.util.Stack;

import static com.ppwqdxlte.basic.class06.Code01_LinkedListMid.*;
/**
 * @author:李罡毛
 * @date:2021/7/19 14:54
 * 【提问】如何判断一个链表是“回文结构”
 * 比如 abcba 123321 左右对称，就是回文结构。
 * 方法有好几种比如说：
 *      1.额外空间复杂度O(1) 找到中点或上中点，剩下的链表反转方向，从原head和尾head两边往中间对比；
 *      2.额外空间复杂度O(N/2)找到链表的中点或者上中点，剩下的部分保存Stack栈，
 *          全部装进去后，再一个个弹出从head开始比对；
 *      3.额外空间复杂度O(N)动态数组存储结点，for循环比对
 *
 */
public class Code02_IsPalindromeList {

    Code01_LinkedListMid midTool = new Code01_LinkedListMid();
    //额外空间复杂度 O(1)
    public boolean isPalindrome1(Node head){
        if (head == null || head.next == null) return true;
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // find mid node
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        // n1 中点
        n2 = n1.next; // n2 -> right part first node
        n1.next = null; // mid.next -> null
        Node n3 = null;
        while (n2 != null) { // right part convert
            n3 = n2.next; // n3 -> save next node
            n2.next = n1; // next of right node convert
            n1 = n2; // n1 move
            n2 = n3; // n2 move
        }
        // n1 right head
        n3 = n1; // n3 -> save last node
        n2 = head;// n2 -> left first node
        boolean res = true;
        while (n1 != null && n2 != null) { // check palindrome
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // left to mid
            n2 = n2.next; // right to mid
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // recover list
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
    //额外空间复杂度 O(n/2)
    public boolean isPalindrome2(Node head){
        if (head == null) return true;
        Node midNode = midTool.midOrUpMid(head);
        Stack<Node> stack = new Stack<>();
        Node n = midNode.next;
        while (n != null){
            stack.add(n);
            n = n.next;
        }
        n = head;
        while (!stack.isEmpty()){
            if (stack.pop().value != n.value) {
                return false;
            }else {
                n = n.next;
            }
        }
        return true;
    }
    //额外空间复杂度 O(n)
    public boolean isPalindrome3(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.add(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            if (stack.pop().value != head.value) return false;
            head = head.next;
        }
        return true;
    }
    //额外空间复杂度 O(n)
    public boolean contrast(Node head){
        List<Node> nodes = linkedListToArrayList(head);
        int midIndex = (nodes.size() - 1)>>1;
        for (int i = 0,j = nodes.size()-1; i <= midIndex && j >= midIndex ; i++,j--) {
            if (nodes.get(i).value != nodes.get(j).value) return false;
        }
        return true;
    }
    public static void main(String[] args) {

        Code02_IsPalindromeList test =  new Code02_IsPalindromeList();

        Node head = null;
        System.out.println("========null==============");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        System.out.println("==========1==============");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        System.out.println("=========1 -> 2============");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(1);
        System.out.println("=========1 -> 1===========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        System.out.println("=========1 -> 2 -> 3==========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        System.out.println("=========1 -> 2 -> 1==========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        System.out.println("========1 -> 2 -> 3 ->1==========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        System.out.println("========1 -> 2 -> 2 ->1=========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        System.out.println("========1 -> 2 -> 3 -> 2 ->1==========");
        printLinkedList(head);
        System.out.print(test.isPalindrome1(head) + " | ");
        System.out.print(test.isPalindrome2(head) + " | ");
        System.out.println(test.isPalindrome3(head) + " | ");
        printLinkedList(head);
    }
}
