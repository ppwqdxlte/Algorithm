package com.ppwqdxlte.basic.class03;

import java.util.Arrays;
import java.util.Stack;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/11 22:07
 */
public class Code07_QuickSortRecursiveAndUnrecursive {
    /**快排3.0递归版本
     * @param arr
     */
    public void recursiveQuickSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        process(arr,0,arr.length-1);
    }
    public void process(int[] arr,int L,int R){
        if (L >= R) return;// 重点，无此判断会死循环
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsPartition(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }
    public void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public int[] netherlandsPartition(int[] arr,int L,int R){
        if (arr == null || arr.length == 0) return new int[]{-1,-1};
        if (L > R) return new int[]{-1,-1};
        if (L == R) return new int[]{L,L};
        int less = L - 1;
        int more = R;
        int i = L;
        while (i < more){ //注意，下标小于 右区 左边界！！！
            if (arr[i] == arr[R]){
                i ++;
            }else if (arr[i] < arr[R]){
                swap(arr,i++,++less);
            }else {
                swap(arr,i,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1,more};
    }

    /**快排3.0非递归版本
     * @param arr
     */
    public void unrecursiveQuickSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsPartition(arr, 0, N - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, equalArea[0] - 1));
        stack.push(new Op(equalArea[1] + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l  ... op.r
            if (op.l < op.r) { //相当于process()里的 if L >= R 判断
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsPartition(arr, op.l, op.r);
                stack.push(new Op(op.l, equalArea[0] - 1));
                stack.push(new Op(equalArea[1] + 1, op.r));
            }
        }
    }
    /**
     * 非递归快排辅助类，保存 equalArea左右边界
     */
    public static class Op{
        public int l;
        public int r;
        public Op(){ }
        public Op(int l,int r){
            this.l = l;
            this.r = r;
        }
    }
    public static void main(String[] args) {
        Code07_QuickSortRecursiveAndUnrecursive test = new Code07_QuickSortRecursiveAndUnrecursive();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomIntArray(maxSize, maxValue);
            int[] arr2 = copyIntArray(arr1);
            int[] arr3 = copyIntArray(arr1);
            Arrays.sort(arr1);
            test.recursiveQuickSort(arr2);
            test.unrecursiveQuickSort(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
