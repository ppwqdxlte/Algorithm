package com.ppwqdxlte.basic.class10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:李罡毛
 * @date:2021/8/2 16:55
 * 哈夫曼树
 * 【提问】一个数组，元素和作为一整块大金子，分成数组大小那么多的块，每块金子分别对应元素的重量，
 * 假设一刀切成a，b，代价就是a+b，那么求切分的最小总代价
 */
public class Code03_LessMoneySplitGold {
    //暴力全罗列，找出最小开销
    public int lessMoney1(int[] arr){
        if (arr == null || arr.length == 0) return 0;
        return process(arr,0);
    }
    // 等待合并的数都在arr里
    // pre:之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价，剩最后一块就不用分了，所以最后一块没代价
    private int process(int[] arr,int pre){
        if (arr.length == 1){
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans,process(copyAndMergeTwo(arr,i,j),pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }
    private int[] copyAndMergeTwo(int[] arr,int i,int j){
        int[] newArr = new int[arr.length - 1];
        int arri = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j){
                newArr[arri ++] = arr[k];
            }
        }
        newArr[arri] = arr[i] + arr[j];
        return newArr;
    }


    private static class MyComp implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    //贪心解法，一步步看，
    public int lessMoney2(int[] arr){
        if (arr == null || arr.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int e : arr) {
            queue.add(e);
        }
        int sum = 0;
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
    // for test
    public static void printArr(int[] arr){
        System.out.println("----------------------");
        for (int e : arr) {
            System.out.print("\t"+e);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code03_LessMoneySplitGold test = new Code03_LessMoneySplitGold();

        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int lm1 = test.lessMoney1(arr);
            int lm2 = test.lessMoney2(arr);
            if (lm1 != lm2) {
                printArr(arr);
                System.out.println(lm1+"\t"+lm2+"\tOops!");
            }
        }
        System.out.println("finish!");
    }

}
