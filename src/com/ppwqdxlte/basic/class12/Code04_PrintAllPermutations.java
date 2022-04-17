package com.ppwqdxlte.basic.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/8/18 20:25\
 * 打印一个字符串的全排列组合     比如 acc => acc,acc,cac,cac,cca,cca
 * 打印一个字符串的去重全排列组合，比如 acc => acc,cac,cca
 * 【注意】不重复的方法，直接用HashSet去重，或者分支限界，重复过的坚决不走，可以提高效率！
 */
public class Code04_PrintAllPermutations {

    public List<String> permutations1(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        List<Character> rest = new ArrayList<>();
        for (Character c : s.toCharArray()){
            rest.add(c);
        }
        f(rest,"",ans);
        return ans;
    }
    /**
     * @param rest 还没安排的字符
     * @param path 之前已经确定了的路径
     * @param ans 全排列的集合
     */
    private void f(List<Character> rest,String path,List<String> ans){
        if (rest.isEmpty()){
            ans.add(path);
        }else {
            for (int i = 0; i < rest.size(); i++) {//0~N-1 都有机会来到path之后的头位置
                char cur = rest.get(i);
                rest.remove(i);
                f(rest,path + cur,ans);
                rest.add(i,cur);
            }
        }
    }

    public List<String> permutation2(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        char[] chars = s.toCharArray();
        g1(chars,0,ans);
        return ans;
    }
    /**
     * @param chars 长度不变的数组，字符也是那些字符，只不过位置可能换来换去的
     * @param index 来到了哪个位置，从index...开始考虑，就是说1~index-1已经定下了，不用考虑了
     * @param ans 全排列串的集合
     */
    private void g1(char[] chars,int index,List<String> ans){
        if (index == chars.length){
            ans.add(String.valueOf(chars));
        }else {
            for (int i = index; i < chars.length; i++) {
                swap(chars,i,index);
                g1(chars,index + 1,ans);
                swap(chars,i,index);
            }
        }
    }

    public List<String> permutation3(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        char[] chars = s.toCharArray();
        g2(chars,0,ans);
        return ans;
    }
    private void g2(char[] chars,int index,List<String> ans){
        if (index == chars.length){
            ans.add(String.valueOf(chars));
        }else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                if (!visited[chars[i]]){
                    visited[chars[i]] = true;
                    swap(chars,i,index);
                    g2(chars,index + 1,ans);
                    swap(chars,i,index);
                }
            }
        }
    }

    private void swap(char[] chars,int i,int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args) {
        Code04_PrintAllPermutations test = new Code04_PrintAllPermutations();

        String s = "acc";
        List<String> ans1 = test.permutations1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = test.permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = test.permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }

}
