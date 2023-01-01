package contest.year2022.m12.d18;

import java.util.*;

public class No3 {
    /*public boolean isPossible(int n, List<List<Integer>> edges) {
        Set<Integer>[] sets = new Set[n];
        int[] count = new int[n];
        int m = edges.size();
        boolean res = true;
        for (int i = 0; i < m; i++) {
            List<Integer> list = edges.get(i);
            int x = list.get(0),y = list.get(1);
            count[x]++;
            count[y]++;
            sets[x].add(y);
            sets[y].add(x);
        }
        return res;
    }*/

    //计算出所有节点的度数
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] count = new int[n+1];
        List<List<Integer>> e = new ArrayList<>(n+1);
        for (int i = 0; i < n+1; i++) {
            e.add(new ArrayList<>());
        }
        for (List<Integer> list : edges) {
            int x = list.get(0),y = list.get(1);
            count[x]++;
            count[y]++;
            e.get(x).add(y);
            e.get(y).add(x);
        }
        List<Integer> queue = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            if((count[i] & 1) == 1)
                queue.add(i);
        }
        if(queue.size() == 0)
            return true;
        if(queue.size() > 4)
            return false;
        if(queue.size() == 2){
            int x = queue.get(0),y = queue.get(1);
            if(!e.get(x).contains(y))
                return true;
            for(int i = 1;i < n+1;i++){
                if(!e.get(i).contains(x) && !e.get(i).contains(y))
                    return true;
            }
        }

        return examine(queue,e);
    }
    public boolean examine(List<Integer> queue,List<List<Integer>> e){
        int x = queue.get(0),y = queue.get(1),z = queue.get(2),q = queue.get(3);
        if(!e.get(x).contains(y) && !e.get(z).contains(q))
            return true;
        if(!e.get(x).contains(z) && !e.get(y).contains(q))
            return true;
        return !e.get(x).contains(q) && !e.get(z).contains(y);
    }
}
