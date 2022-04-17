package com.ppwqdxlte.basic.class04;

import java.util.Arrays;
import java.util.PriorityQueue;

import static com.ppwqdxlte.basic.class01.Code06_BSLocalMinimum.*;

/**
 * @author:李罡毛
 * @date:2021/7/15 18:49
 * 【提问】有一个数组，如果排序之后，每个元素的位置偏移量 <= K，怎么给它排序？
 */
public class Code04_SortArrayDistanceLessK {

    public void sortArrDistLessK(int[] arr,int k){
        if (k == 0) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
    // for test
    public int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Code04_SortArrayDistanceLessK sort = new Code04_SortArrayDistanceLessK();
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = sort.randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyIntArray(arr);
            int[] arr2 = copyIntArray(arr);
            sort.sortArrDistLessK(arr1, k);
            arraysAscendingSort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printIntArray(arr);
                printIntArray(arr1);
                printIntArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
