package com.ppwqdxlte.basic.class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author : 李罡毛
 * @date :2021/7/23 23:03
 * 序列化和重建二叉树
 */
public class Code04_SerializeAndReconstructBT {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     */
    /**【先序】序列化二叉树
     * @param head 二叉树
     * @return 先序队列
     */
    public Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }
    private void pres(Node head,Queue<String> ans){
        if (head == null){
            ans.add(null);//null占位，维持树形结构
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }
    /**【中序】序列化二叉树
     *  !!!  但是不太准
     * @param head 二叉树
     * @return 中序队列
     */
    public Queue<String> inSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        ins(head,ans);
        return ans;
    }
    private void ins(Node head,Queue<String> ans){
        if (head == null){
            ans.add(null);
        }else {
            ins(head.left,ans);
            ans.add(String.valueOf(head.value));
            ins(head.right,ans);
        }
    }
    /**【后序】序列化二叉树
     * @param head 二叉树
     * @return 后序队列
     */
    public Queue<String> posSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        poss(head,ans);
        return ans;
    }
    private void poss(Node head,Queue<String> ans){
        if (head == null){
            ans.add(null);
        }else {
            poss(head.left,ans);
            poss(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }
    /**宽度 level序列化二叉树
     * @param head 二叉树
     * @return 宽度队列
     */
    public Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll(); // head 父   子
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }
    /**用【先序】队列构建二叉树
     * @param queue 先序队列
     * @return 二叉树
     */
    public Node buildByPreQueue(Queue<String> queue){
        return preb(queue);
    }
    private Node preb(Queue<String> queue){
        if (queue == null || queue.isEmpty()) return null;
        Node head = strToNode(queue.poll());
        if (head == null) return null;
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }
    /**用【后序】队列构建二叉树
     * @param queue 后序队列
     * @return 二叉树
     */
    public Node buildByPosQueue(Queue<String> queue){
        if (queue == null || queue.isEmpty()) return null;
        Stack<String> stack =  new Stack<>();
        while (!queue.isEmpty()){
            stack.push(queue.poll());
        }
        return posb(stack);
    }
    private Node posb(Stack<String> stack){
        String value = stack.pop();
        if (value == null) return null;
        Node head = strToNode(value);
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }
    /**用【宽度】队列构建二叉树
     * @param levelList 宽度队列
     * @return 二叉树
     */
    public Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = strToNode(levelList.poll());
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = strToNode(levelList.poll());
            node.right = strToNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Code04_SerializeAndReconstructBT test = new Code04_SerializeAndReconstructBT();
        Code03_LevelTraversalBT levelPrint = new Code03_LevelTraversalBT();
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 300000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
//            printTree(head);
            Queue<String> pre = test.preSerial(head);
            Queue<String> in = test.inSerial(head);
            Queue<String> pos = test.posSerial(head);
            Queue<String> level = test.levelSerial(head);
            Node preBuild = test.buildByPreQueue(pre);
            Node posBuild = test.buildByPosQueue(pos);
            Node levelBuild = test.buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
//            printTree(preBuild);
//            printTree(posBuild);
//            printTree(levelBuild);
        }
        System.out.println("test finish!");
    }
}
