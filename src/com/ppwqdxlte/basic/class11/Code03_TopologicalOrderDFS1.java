package com.ppwqdxlte.basic.class11;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.ppwqdxlte.basic.class11.Code03_TopologicalOrderBFS.*;
/**
 * @author:李罡毛
 * @date:2021/8/8 15:21
 * 拓扑顺序深度优先搜索
 * 高度优先，根肯定最高，叶子肯定最低了，所谓深度优先，本质就是递归方式
 */
public class Code03_TopologicalOrderDFS1 {

    public static class MyComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public List<DirectedGraphNode> topoSort(ArrayList<DirectedGraphNode> graph) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IdentityHashMap<Integer,DirectedGraphNode> deepMap = new IdentityHashMap<>();
        for (DirectedGraphNode cur : graph) {
           fillDeepMap(cur,deepMap);
        }
        List<Integer> deepArr = new ArrayList<>();
        for (Integer deep : deepMap.keySet()) {
            deepArr.add(deep);
        }
        deepArr.sort(new MyComp());
        List<DirectedGraphNode> ans = new ArrayList<>();
        for (Integer deep : deepArr) {
            ans.add(deepMap.get(deep));
        }
        return ans;
    }

    private Integer fillDeepMap(DirectedGraphNode node,IdentityHashMap<Integer,DirectedGraphNode> deepMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //深度表若已包含node就无需填充了，直接返回即可
        if (deepMap.values().contains(node)) {
            for (Integer i :
                    deepMap.keySet()) {
                if (deepMap.get(i) == node) return i;
            }
        }
        //深度表没有node需要填充
        int follow = 0;
        for (DirectedGraphNode next : node.neighbors) {
            follow = Math.max(follow,fillDeepMap(next,deepMap));
        }
        deepMap.put(Integer.class.getConstructor(int.class).newInstance(follow + 1),node);
        return follow + 1;
    }
}
