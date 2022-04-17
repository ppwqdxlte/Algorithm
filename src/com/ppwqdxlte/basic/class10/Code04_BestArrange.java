package com.ppwqdxlte.basic.class10;

import java.util.*;

/**
 * @author:李罡毛
 * @date:2021/8/2 19:25
 * 【提问】一堆会议，怎么安排最多？串行，且有开始结束时间
 */
public class Code04_BestArrange {

    private static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString(){
            return "["+start+","+end+"]";
        }
    }
    //全枚举，所有情况都尝试！
    public int bestArrange1(Program[] programs){
        if (programs == null || programs.length == 0) return 0;
        return process(programs,0,0);
    }
    // 还剩下的会议都放在programs里
    // done之前已经安排了多少会议的数量
    // timeLine目前来到的时间点是什么
    // 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
    // 返回能安排的最多会议数量
    /**
     * @param programs 尚未安排的会议
     * @param timeLine 时间线来到此处
     * @param done 已经安排了的会议个数
     * @return 可能安排的最多会议个数
     */
    private int process(Program[] programs, int done, int timeLine){
        if (programs.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine){ //筛选条件！！！
                Program[] next = copyButExcept(programs,i);
                max = Math.max(max,process(next,done + 1,programs[i].end));
            }
        }
        return max;
    }


    public Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    private static class MyComp implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end ;
        }
    }
    //贪心方式
    public int bestArrange2(Program[] programs){
        if (programs == null || programs.length == 0) return 0;
        Arrays.sort(programs,new MyComp());
        int max = 0;
        int timeLine = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start < timeLine) continue;
            timeLine = programs[i].end;
            max ++;
        }
        return max;
    }

    // for test
    public Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }
    // for test
    private void printPrograms(Program[] programs){
        System.out.println("----------------");
        for (Program p : programs) {
            System.out.print(p+"\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code04_BestArrange test = new Code04_BestArrange();

        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 10000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = test.generatePrograms(programSize, timeMax);
            int m1 = test.bestArrange1(programs);
            int m2 = test.bestArrange2(programs);
            if (m1 != m2) {
                test.printPrograms(programs);
                System.out.println(m1+"\t"+m2+"\tOops!");
            }
        }
        System.out.println("finish!");
    }
}
