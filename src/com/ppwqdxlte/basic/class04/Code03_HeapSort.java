package com.ppwqdxlte.basic.class04;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/15 10:40
 * 堆排序，如果堆添加删除元素，每次增删都自适应，那么堆排序就是把一个给定的数组，一股脑儿地排序，且按照堆的方式排序
 */
public class Code03_HeapSort {
    // 堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        //O(N*logN)  重新一个个加一遍，让arr变成大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        //O(N)  如果数组元素从右到左方向，遍历下沉，那么复杂度会简单多了
        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify(arr,i,arr.length);
        }
        /*
        //这是升序
        for (int i = arr.length - 1,heapSize = arr.length; i >= 0 && heapSize >0; i--,heapSize--) {
            int max = heapPop(arr,heapSize);
            arr[i] = max;
        }*/
        //这是降序
        int[] arrOp = copyIntArray(arr);//arr经过上步的处理后变成了 大根堆 BigRootHeap
        for (int i = 0,heapSize = arr.length; i < arrOp.length && heapSize > 0 ; i++,heapSize--) {
            int max = heapPop(arrOp,heapSize);
            arr[i] = max;
        }
    }
    // arr[index]刚来的数，往上
    public static void heapInsert(int[] arr,int i){
        while (i > 0){
            int fatherIndex = (i - 1)>>1;
            if (arr[fatherIndex] < arr[i]) swap(arr,i,fatherIndex);
            i = fatherIndex;
        }
    }
    // arr[index]位置的数，能否往下移动
    public static void heapify(int[] arr,int i,int heapSize){
        if (i >= heapSize) return;
        while (i < heapSize){
            int maxIndex = i;
            if (2*i + 2 > heapSize){//i没有子结点
                return;
            }else if (2*i + 2 == heapSize){//i只有左节点
                if (arr[i] < arr[2*i + 1]) {
                    swap(arr,i,2*i + 1);
                    maxIndex = 2*i + 1;
                    i = maxIndex;
                }else return;
            }else {//i有左右两个结点
                maxIndex = arr[2*i + 1] < arr[2*i + 2] ? 2*i + 2 : 2*i + 1;
                if (arr[i] < arr[maxIndex]) {
                    swap(arr,i,maxIndex);
                    i = maxIndex;
                }else {
                    return;
                }
            }
        }
    }
    //弹出最大值
    public static int heapPop(int[] arr,int heapSize){
        int max = arr[0];
        swap(arr,0,heapSize - 1);
        heapify(arr,0,--heapSize);
        return max;
    }
    public static void main(String[] args) {
        int ms = 100;
        int mv = 100;
        int tt = 10;
        Code03_HeapSort sort = new Code03_HeapSort();
        for (int i = 0; i < tt; i++) {
            int[] arr = generateRandomIntArray(ms,mv);
            int[] arr1 = copyIntArray(arr);
            int[] arr2 = copyIntArray(arr);
//            printIntArray(arr);
            heapSort(arr1);
            arraysDescendingSort(arr2);
            if (!isEqual(arr1,arr2)){
                printIntArray(arr1);
                printIntArray(arr2);
                System.out.println("Ooops!");
                break;
            }
//            printIntArray(arr1);
//            printIntArray(arr2);
//            System.out.println("==================");
        }
    }
}
