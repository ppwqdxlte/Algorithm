package com.ppwqdxlte.basic.class05;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/16 16:56
 * 【桶排序】之【计数排序】
 * 非负整数排序，且样本最大值不宜过大，O(N)，额外空间复杂度O(M)？？
 */
public class Code02_CountSort {
    // only for 0~200 value
    //桶子数量由 int[] arr最大值决定，小一点比较好，且数据分布比较集中最好，桶子就不会太多，且适合基础数值，不适合引用型数据！
    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int e: arr) {
            max = Math.max(max,e);
        }
        int[] buckets = new int[max + 1];
        for (int e: arr) {
            buckets[e] ++;
        }
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i]; j++) {
                arr[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 180;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomNonNegativeArray(maxSize,maxValue);
            int[] arr1 = copyIntArray(arr);
            int[] arr2 = copyIntArray(arr);
            countSort(arr1);
            arraysAscendingSort(arr2);
            if (!isEqual(arr1,arr2)){
                System.out.println("Ooops!!");
                printIntArray(arr);
                printIntArray(arr1);
                printIntArray(arr2);
                break;
            }
            if (i<10){
                printIntArray(arr);
                printIntArray(arr1);
                printIntArray(arr2);
            }
        }
    }
}
