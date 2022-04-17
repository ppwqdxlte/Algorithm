package com.ppwqdxlte.basic.class10;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/7/27 22:29
 * 【提问】一个字符串数组，所有字符串拼在一起，求出最小的字典序
 */
public class Code01_LowestLexicoGraphy {

    public String lowestString1(String[] strs){
        if (strs == null || strs.length == 0) return "";
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "":ans.first();//返回第一个字典序最小的拼接后的总字符串
    }
    // strs中所有字符串全排列，返回所有可能的结果
    private TreeSet<String> process(String[] strs){
        TreeSet<String> ans = new TreeSet<>();
        if (strs == null || strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String h = strs[i];//作头
            TreeSet<String> noHans = process(delH(strs,h));
            for (String str : noHans) {
                ans.add(h + str);
            }
        }
        return ans;
    }
    private String[] delH(String[] strs,String dStr){

        boolean hasDStr = false;
        int i = 0;
        for (; i < strs.length; i++) {
            if (strs[i].equals(dStr)) {
                hasDStr = true;
                break;
            }
        }
        if (!hasDStr) return strs;
        String[] newStrs = new String[strs.length - 1];
        for (int j = 0; j < i; j++) {
            newStrs[j] = strs[j];
        }
        for (; i < newStrs.length; i++) {
            newStrs[i] = strs[i + 1];
        }
        return newStrs;
    }

    //字符串字典序比较器
    private static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }
    public String lowestString2(String[] strs){
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs,new MyComparator());
        String ans = "" ;
        for (String str :
                strs) {
            ans += str;
        }
        return ans;
    }

    public String lowestString3(String[] strs){
        if (strs == null || strs.length == 0) return "";
        List<String> allPaths = new ArrayList<>();
        List<String> used = new ArrayList<>();
        String path = "";
        process3(strs,used,path,allPaths);
        allPaths.sort(new MyComparator());
        return allPaths.size() > 0 ? allPaths.get(0) : "";
    }
    private void process3(String[] strs,List<String> used,String path,List<String> all){
        if (used.size() == strs.length) {
            all.add(path);
        }else {
            for (String str : strs) {
                boolean isContains = false;
                for (String u :
                        used) {
                    if (u == str){
                        isContains = true;//不仅要比较值，更要比较地址！！！
                        break;
                    }
                }
                if (!isContains){
                    used.add(str);
                    process3(strs,used,path + str,all);
                    used.remove(str);
                }
            }
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return new String(ans);
    }
    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }
    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }
    // for test
    public static void printStrArr(String[] strs) {
        System.out.println("``````````````````````````STRING ARR");
        for (String str : strs) {
            System.out.print(str + ",");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Code01_LowestLexicoGraphy test = new Code01_LowestLexicoGraphy();

        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            String[] arr3 = copyStringArray(arr1);
            String ls1 = test.lowestString1(arr1);
            String ls2 = test.lowestString2(arr2);
            String ls3 = test.lowestString3(arr3);
            if (!ls1.equals(ls2) || !ls1.equals(ls3)) {
                System.out.println("Oops!");
                printStrArr(arr1);
                System.out.println("ls1:\t"+ls1);
                System.out.println("ls2:\t"+ls2);
                System.out.println("ls3:\t"+ls3);
            }
        }
        System.out.println("finish!");
    }
}
