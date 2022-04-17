package com.ppwqdxlte.basic.class12;

import java.sql.PreparedStatement;

/**
 * @author:李罡毛
 * @date:2021/8/18 22:47
 * 数字字符串 转化为 字母字符串 ，返回多少种转化方案
 * 怎么转化呢？1-A，1和0-J，...，2和6-Z，0自己啥也不是，27以上的合并啥也不是
 * 很显然是【从左往右尝试模型】
 */
public class Code05_NumericStrToLetterStr {

    public int numToLetter1(String s){
        if (s == null || s.length() == 0) return 0;
        return process(s.toCharArray(),0);
    }
    // chars[0..i-1]转化无需过问
    // chars[i.....]去转化，返回有多少种转化方法
    private int process(char[] chars,int i){
        if (i == chars.length) {
            return 1;
        }
        //i没到最后，说明还有字符
        if (chars[i] == '0'){//之前的决定有问题
            return 0;
        }
        //chars[i] != '0'
        int ways = process(chars,i + 1);//单转
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27){//双转
            ways += process(chars,i + 2);
        }
        return ways;
    }

    public int numToLetter2(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] cache = new int[s.length() + 1];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
        return proWithCache(s.toCharArray(),0,cache);
    }
    private int proWithCache(char[] chars,int index,int[] cache){
        if (cache[index] != -1){
            return cache[index];
        }
        if (index == chars.length){
            cache[index] = 1;
            return cache[index];
        }
        if (chars[index] == '0'){
            cache[index] = 0;
            return cache[index];
        }
        cache[index] = proWithCache(chars,index + 1,cache);
        if (index + 1 < chars.length && (chars[index] - '0')*10 + chars[index + 1] - '0' < 27){
            cache[index] += proWithCache(chars,index + 2,cache);
        }
        return cache[index];
    }

    public int numToLetterWithDP(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[dp.length - 1] = 0;//下面循环会越界，所以单独初始化最后一个元素
        for (int i = dp.length - 1; i >= 0; i--) {
            if (i == chars.length) {
                dp[i] = 1;
                continue;
            }
            //i没到最后，说明还有字符
            if (chars[i] == '0'){//之前的决定有问题
                dp[i] = 0;
                continue;
            }
            //chars[i] != '0'
            dp[i] = dp[i + 1];//单转
            if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27){//双转
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    // for test
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }
    // for test
    public static void main(String[] args) {
        Code05_NumericStrToLetterStr test = new Code05_NumericStrToLetterStr();

        int N = 30;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = test.numToLetter1(s);
            int ans1 = test.numToLetter2(s);
            int ans2 = test.numToLetterWithDP(s);
//            System.out.println(s+"\t"+ans0+"\t"+ans1+"\t"+ans2);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
