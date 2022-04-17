package com.ppwqdxlte.basic.class13;

/**
 * @author:李罡毛
 * @date:2021/8/20 14:26
 * N皇后问题：N个皇后，位于N*N棋盘不同行不同列且不同对角斜线，有几种摆放方法？
 */
public class Code01_NQueens {
    /**数组容器求出N皇后的解，O(N^N)
     * @param n 皇后个数
     * @return N皇后问题的拜访方法数
     */
    public int num1(int n){
        if (n < 1) return 0;
        int[] record = new int[n];//【缓存表】
        return process1(0,record);
    }
    /**第row行以前的行，不包括 row，范围0 ~ row - 1业已确定，从第row行以后算出摆法的总数
     * 列数范围 1 ~ N
     * @param row 当前的行号，变化范围从0 ~ N - 1，表示第row行
     * @param record 皇后位置的【缓存表】，元素下标表示行号，record[i]==0表示i行还没用过
     * @return row以前的不管，包括row以后的行算出所有可行的摆法数量
     */
    private int process1(int row,int[] record){
        if (row == record.length){
            return 1;
        }
        int res = 0;
        // row行的皇后，放哪一列呢？i列
        for (int i = 0; i < record.length; i++) {
            if (isValid(record,row,i)){
                record[row] = i;
                res += process1(row + 1,record);
            }
        }
        return res;
    }
    /**结合record记录，判断row行column列放皇后的合法性
     * @param record 皇后位置表
     * @param row 某行
     * @param column 某列
     * @return 是否合法
     */
    private boolean isValid(int[] record,int row,int column){
        for (int i = 0; i < row; i++) {
            if (record[i] == column || Math.abs(row - i) == Math.abs(record[i] - column)){
                return false;
            }
        }
        return true;
    }

    //请不要超过32皇后问题，因为int4个byte，1个byte8个bits，共32位，超过就没法用int类型了
    // 额外时间复杂度依然是O(N^N)，只不过是优化了常数时间
    public int num2(int n){
        if (n < 1) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit,0,0,0);
    }
    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    private int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim){
        if (colLim == limit){
            return 1;
        }
        // pos中所有是1 的位置，是可以尝试皇后的位置
        int pos = limit & ( ~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,colLim | mostRightOne,(leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }// >>>表示不带符号的右移
        return res;
    }

    public static void main(String[] args) {
        Code01_NQueens test = new Code01_NQueens();

        int n = 15;
        long start = System.currentTimeMillis();
        System.out.println(test.num1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(test.num2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
