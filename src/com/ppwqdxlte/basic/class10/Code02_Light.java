package com.ppwqdxlte.basic.class10;

import java.util.HashSet;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/8/1 17:05
 * 左老师说：排序、大根堆、小根堆 是 【贪心算法】 最常用的工具
 * 【提问】字符串只有X和.组成，字符串看成一个街道， X 表示墙，. 表示居民点，居民点可以放灯，墙不需放灯，比如i下标位置放灯，
 * 那么 i-1、i+1 位置都会被照亮，意思就是前后都会被照亮，那么，给定这种字符串，求出
 * 照亮所有居民点 最少的灯数
 */
public class Code02_Light {
    //暴力解：全枚举，罗列所有可能性，找出符合要求的最小值
    public int minLight1(String street){
        if (street == null || street.length() == 0) return 0;
        return process(street.toCharArray(),0,new HashSet<>());
    }
    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    private int process(char[] chars,int index,HashSet<Integer> lights){
        if (index == chars.length){// 结束的时候
            //成立条件:灯光覆盖住所有居民点，覆盖不住的方案就是错的
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'X') continue;
                if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)){
                    return Integer.MAX_VALUE;
                }
            }
            return lights.size();//没问题的话就是可行方案
        }else {// str还没结束
            int no = process(chars,index + 1,lights);//此处不点灯，判断留在下一个位置！
            int yes = Integer.MAX_VALUE;                //此处点灯，初始化，默认没覆盖全居民点
            if (chars[index] == '.'){                   //此处居民点
                lights.add(index);                      //点灯，添加进去
                yes = process(chars,index + 1,lights);
                lights.remove(index);
            }
            return Math.min(yes,no);
        }
    }

    //贪心解法
    public int minLight2(String street){
        char[] chars = street.toCharArray();
        int i = 0;
        int light = 0;
        while (i < chars.length){
            if (chars[i] == 'X'){
                i ++;
            }else {
                light ++;
                if (i + 1 == chars.length){
                    break;
                }else {
                    if (chars[i + 1] == 'X'){
                        i = i + 2;
                    }else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    //字符串排除i位置的字符返回新的字符串
    private String withoutI(String str,int i){
        if (str == null || str.length() == 0
                || i < 0 || i >= str.length()) return str;
        char[] arr = new char[str.length() - 1];
        int index = 0;
        for (char ch : arr) {
            if (index == i) index ++;
            ch = str.charAt(index);
            index ++;
        }
        return new String(arr);
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Code02_Light lightTest = new Code02_Light();

        int len = 20;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = lightTest.minLight1(test);
            int ans2 = lightTest.minLight2(test);
            if (ans1 != ans2) {
                System.out.println(test);
                System.out.println(ans1 + "\t"+ans2+"oops!");
            }
        }
        System.out.println("finish!");
    }
}
