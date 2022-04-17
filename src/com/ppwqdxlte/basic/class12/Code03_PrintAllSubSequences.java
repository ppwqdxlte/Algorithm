package com.ppwqdxlte.basic.class12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/8/18 16:02
 * 【提问】要分清楚不同问题：
 * 1、打印一个字符串的所有子串【连续串】
 * 1.5、打印一个字符串的所有不重复的子串
 * 2、打印一个字符串的所有子序列【不一定连续，但保证顺序一致】
 * 2.5、打印一个字符串的所有不重复的子序列
 */
public class Code03_PrintAllSubSequences {

    public List<String> subs(String s){
        char[] chars = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(chars,0,ans,path);
        return ans;
    }
    // str 固定参数
    // 来到了str[index]字符，index是位置
    // str[0..index-1]已经走过了！之前的决定，都在path上
    // 之前的决定已经不能改变了，就是path
    // str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
    // 把所有生成的子序列，放入到ans里去
    private void process1(char[] chars,int index,List<String> ans,String path){
        if (index == chars.length){
            ans.add(path);
        }else {
            process1(chars,index + 1,ans,path);
            process1(chars,index + 1,ans,path + chars[index]);
        }
    }

    public List<String> subsNoRepeat(String s){
        char[] chars = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(chars,0,set,path);
        List<String> ans = new ArrayList<>();
        for (String cur : set){
            ans.add(cur);
        }
        return ans;
    }
    private void process2(char[] chars,int index,HashSet<String> set,String path){
        if (index == chars.length){
            set.add(path);
        }else {
            process2(chars,index + 1,set,path);
            process2(chars,index + 1,set,path + chars[index]);
        }
    }

    public List<String> subsNoRepeat2(String s){
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process3(chars,0,ans,"");
        return ans;
    }
    private void process3(char[] chars,int index,List<String> ans,String path){
        if (index == chars.length){
            ans.add(path);
        }else {
            HashSet<String> set = new HashSet<>();

        }
    }

    public static void main(String[] args) {
        Code03_PrintAllSubSequences pass = new Code03_PrintAllSubSequences();

        String test = "acccc";
        List<String> ans1 = pass.subs(test);
        List<String> ans2 = pass.subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");
    }
}
