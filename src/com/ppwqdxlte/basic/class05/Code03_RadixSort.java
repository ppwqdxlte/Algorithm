package com.ppwqdxlte.basic.class05;

import java.util.LinkedList;
import java.util.Queue;
import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/16 16:56
 * 【桶排序】之【基数排序】
 * radix = 10 十进制，10就是基数。
 */
public class Code03_RadixSort {
    // only for no-negative value
    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        radixSort(arr,0,arr.length - 1,maxBits(arr));
    }
    public static void radixSort(int[] arr,int L,int R,int maxBits){
        Queue<Integer>[] buckets = new LinkedList[10];//0~9 一共10个桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int i = 1; i <= maxBits; i++) {
            for (int j = 0; j < arr.length; j++) {
//                System.out.println("getNumOnDigit("+arr[j]+","+i+"):\t"+getNumOnDigit(arr[j],i));
                buckets[getNumOnDigit(arr[j],i)].add(arr[j]);
            }
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (!buckets[j].isEmpty()){
                    arr[index++] = buckets[j].poll();
                }
            }
        }
    }
    //获得数组最大值的位数
    public static int maxBits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int e: arr) {
            max = Math.max(max,e);
        }
        int res = 0;
        while (max > 0){
            res ++;
            max /= 10;
        }
        return res;
    }
    //获取value digit数位上的数字，0~9
    public static int getNumOnDigit(int value,int digit){
        return (value / (int) Math.pow(10,(digit - 1)))%10;
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 10000;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomNonNegativeArray(maxSize,maxValue);
            int[] arr1 = copyIntArray(arr);
            int[] arr2 = copyIntArray(arr);
            radixSort(arr1);
            arraysAscendingSort(arr2);
            if (!isEqual(arr1,arr2)){
                System.out.println("Ooops!!!");
                printIntArray(arr1);
                printIntArray(arr2);
                break;
            }
        }
        
    }
}
