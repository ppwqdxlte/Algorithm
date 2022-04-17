package com.ppwqdxlte.basic.class11;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import static com.ppwqdxlte.basic.class11.Code03_TopologicalOrderBFS.*;
/**
 * @author:李罡毛
 * @date:2021/8/9 15:14
 * 拓扑顺序深度优先搜索
 * 用当前结点所在的分支的所有结点数来衡量深度
 */
public class Code03_TopologicalOrderDFS2 {

    public List<DirectedGraphNode> topoSort(ArrayList<DirectedGraphNode> graph) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IdentityHashMap<Integer,DirectedGraphNode> sizeMap = new IdentityHashMap<>();
        for (DirectedGraphNode cur : graph) {
            fillSizeMap(cur,sizeMap);
        }
        List<Integer> sizeArr = new ArrayList<>();
        for (Integer i : sizeMap.keySet()) {
            sizeArr.add(i);
        }
        sizeArr.sort(new Code03_TopologicalOrderDFS1.MyComp());
        List<DirectedGraphNode> ans = new ArrayList<>();
        for (Integer i : sizeArr) {
            ans.add(sizeMap.get(i));
        }
        return ans;
    }

    private Integer fillSizeMap(DirectedGraphNode node,IdentityHashMap<Integer,DirectedGraphNode> sizeMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (sizeMap.values().contains(node)){
            for (Integer i : sizeMap.keySet()) {
                if (sizeMap.get(i) == node) return i;
            }
        }
        int size = 0;
        for (DirectedGraphNode next : node.neighbors) {
            size += fillSizeMap(next,sizeMap);
        }
        sizeMap.put(Integer.class.getConstructor(int.class).newInstance(size + 1),node);
        return size + 1;
    }
}
