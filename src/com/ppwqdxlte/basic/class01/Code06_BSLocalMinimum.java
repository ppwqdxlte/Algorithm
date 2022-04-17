package com.ppwqdxlte.basic.class01;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/7/7 12:09
 * 找出一个数组【不一定有序，可以无序】中，任意一个局部最小值
 */
public class Code06_BSLocalMinimum {
    private static int binarySearchLocalMinimum(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
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


    //for test
    public static void swap(int[] arr,int i1,int i2){
        if (arr == null || arr.length == 0) return;
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    public static int[] generateRandomIntArray(int maxSize, int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }
    public static int[] generateRandomNonNegativeArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }
    public static void printIntArray(int[] arr){
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\n"+"---------------------------");
    }
    public static boolean isEqual(int[] arr1,int[] arr2){
        if (arr1 == null && arr2 == null) return true;
        if (arr1 == null && arr2 != null) return false;
        if (arr1 != null && arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
    public static int[] copyIntArray(int[] arr){
        if (arr == null) return null;
        int[] copition = new int[arr.length];
        for (int i = 0; i < copition.length; i++) {
            copition[i] = arr[i];
        }
        return copition;
    }
    public static void arraysAscendingSort(int[] arr){
        Arrays.sort(arr);
    }
    public static void arraysDescendingSort(int[] arr){
        Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(integers,(a,b)->b-a);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = integers[i].intValue();
        }
    }
    public static void main(String[] args) {
        int[] arr1 = {120,23,53,23,12,75,35,10,100,110};
        int[] arr2 = {100,90,80,120,140,80,70,50,200};
        int localMinimum1 = binarySearchLocalMinimum(arr2);
        System.out.println(localMinimum1);
    }
}
