package com.ppwqdxlte.basic.class01;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/7/7 11:22
 */
public class Code05_BSNearRight {
    private static int binarySearchNearRight(int[] sortedArr,int value){
        if (sortedArr == null || sortedArr.length == 0) return -1;
        int l = 0;
        int r = sortedArr.length-1;
        int mid = 0;
        int index = -1;
        while (l<=r){
            mid = l + ((r-l)>>1);
            if (sortedArr[mid] >= value){
                index = mid;
                r=mid-1;
            }else {
                l=mid+1;
            }
        }
        return index;
    }
    private static int normalSearchNearRight(int[] sortedArr,int value){
        if (sortedArr == null || sortedArr.length == 0) return -1;
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] >= value) return i;
        }
        return -1;
    }
    private static void insertionSort(int[] arr){
        if (arr == null || arr.length <2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0 && arr[j] > arr[j+1] ; j--) {
                swap(arr,j,j+1);
            }
        }
    }
    private static void normalSort(int[] arr){
        Arrays.sort(arr);
    }
    private static void swap(int[] arr,int i1,int i2){
        if (arr == null || arr.length <2 || i1 == i2
                || arr[i1] == arr[i2]) return;
        arr[i1] = arr[i1]^arr[i2];
        arr[i2] = arr[i1]^arr[i2];
        arr[i1] = arr[i1]^arr[i2];
    }
    private static int[] generateRandomIntArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }
    private static void printIntArray(int[] arr){
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
    private static boolean isEqual(int[] arr1,int[] arr2){
        if (arr1 == null && arr2 == null) return true;
        if (arr1 == null && arr2 != null) return false;
        if (arr1 != null && arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
    private static int[] copyIntArray(int[] arr){
        if (arr == null) return null;
        int[] copition = new int[arr.length];
        for (int i = 0; i < copition.length; i++) {
            copition[i] = arr[i];
        }
        return copition;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testLoop = 10000;
        for (int i = 0; i < testLoop; i++) {
            int[] arr = generateRandomIntArray(maxSize,maxValue);
            insertionSort(arr);
            int randValue = (int)((maxValue+1)*Math.random());
            int bs,os = 0;
            bs = binarySearchNearRight(arr,randValue);
            os = normalSearchNearRight(arr,randValue);

            System.out.println("随机值："+randValue+"\t二分法找到的下标："+bs
                    +"\t普通遍历找到的下标："+os+"\t是否相等？"
                    +(bs == os));
            if (bs != os) {
                printIntArray(arr);
            }
        }
    }
}
