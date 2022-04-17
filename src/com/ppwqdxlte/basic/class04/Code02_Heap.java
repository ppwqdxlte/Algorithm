package com.ppwqdxlte.basic.class04;

import java.util.PriorityQueue;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;
import static com.ppwqdxlte.basic.class04.Code01_Comparator.*;
/**
 * @author:李罡毛
 * @date:2021/7/14 15:28
 */
public class Code02_Heap {
    //大根堆
    public static class BigRootHeap{
        private int heapSize;
        private int[] heap;
        private final int limit;
        public BigRootHeap(int limit){
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public boolean isFull(){
            return heapSize == limit;
        }
        //一次只添加一个
        public void push(int value){
            if (isFull()) throw new RuntimeException(" Heap is full !!");
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }
        //从下网上
        public void heapInsert(int[] heap,int heapSize){
            while (heapSize > 0){
                if (heap[heapSize] > heap[(heapSize - 1)>>1]){
                    swap(heap,heapSize,(heapSize - 1)>>1);
                }
                heapSize = (heapSize - 1)>>1;
            }
        }
        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop(){
            swap(heap,0,--heapSize);
            heapify(heap,0);
            return heap[heapSize];//没有真正删除
        }
        //从上往下
        public void heapify(int[] heap,int i){
            while (i < heapSize){
                int maxIndex = i;
                if (2*i + 2 < heapSize){
                    maxIndex = heap[2*i+1] < heap[2*i+2] ? 2*i+2 : 2*i+1;
                    maxIndex = heap[i] < heap[maxIndex] ? maxIndex : i;
                }else if (2*i+2 == heapSize){
                    maxIndex = heap[2*i+1] > heap[i] ? 2*i+1 : i;
                }
                if (maxIndex == i) break;
                swap(heap,maxIndex,i);
                i = maxIndex;
            }
        }
        public void swap(int[] heap,int son,int father){
            int temp = heap[son];
            heap[son] = heap[father];
            heap[father] = temp;
        }
        public void printHeap(){
            System.out.println("heap size is:"+heapSize+"\tlimit is:"+limit);
            printIntArray(heap);
        }
    }
    //for test
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        /*int ms = 100;
        int mv = 100;
        int limit = (int) ((ms + 1)*Math.random());
        BigRootHeap bigRootHeap = new BigRootHeap(limit);
        for (int i = 0; i < (limit+1)*Math.random(); i++) {
            int rand = (int) ((mv + 1)*Math.random());
            bigRootHeap.push(rand);
        }
        bigRootHeap.printHeap();*/

        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComp());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }


        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            BigRootHeap my = new BigRootHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
