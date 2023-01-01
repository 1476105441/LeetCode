package contest.year2022.m8.m8d6;

import java.util.*;

public class No1 {
    /**
     *      合并相似的物品
     */

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        TreeMap<Integer,Integer> treeSet = new TreeMap<>();
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < items1.length; i++) {
            treeSet.put(items1[i][0],treeSet.getOrDefault(items1[i][0],0)+items1[i][1]);
        }

        for (int i = 0; i < items2.length; i++) {
            treeSet.put(items2[i][0],treeSet.getOrDefault(items2[i][0],0)+items2[i][1]);
        }

        Set<Integer> set = treeSet.keySet();
        for (Integer key : set) {
            Integer integer = treeSet.get(key);
            List<Integer> list = new ArrayList<>();
            list.add(key);
            list.add(integer);
            res.add(list);
        }

        Collections.sort(res,(x,y)->{
            return x.get(0)-y.get(0);
        });

        return res;
    }
}
