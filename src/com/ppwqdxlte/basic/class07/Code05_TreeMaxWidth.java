package com.ppwqdxlte.basic.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/24 22:23
 * 求出二叉树的最大宽度
 */
public class Code05_TreeMaxWidth {

    public int maxWidthUseMap(Node head){
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public int maxWidthNoMap(Node head){
        if (head == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;
        Node c = null;
        while (!queue.isEmpty()){
            c = queue.poll();
            if (c.left != null){
                queue.add(c.left);
                nextEnd = c.left;
            }
            if (c.right != null){
                queue.add(c.right);
                nextEnd = c.right;
            }
            curLevelNodes ++;
            if (c == curEnd){
                max = Math.max(max,curLevelNodes);
                curEnd = nextEnd;
                curLevelNodes = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Code05_TreeMaxWidth test = new Code05_TreeMaxWidth();

        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (test.maxWidthUseMap(head) != test.maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
//            System.out.println(test.maxWidthNoMap(head));
//            System.out.println(test.maxWidthUseMap(head));
        }
        System.out.println("finish!");
    }

}
