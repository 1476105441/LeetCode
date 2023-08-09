package contest.year2023.m5.d14;

import java.util.*;

/**
 * No2685.统计完全连通分量的数量
 *
 * @author wjs 2023/8/9
 */
public class No4 {
    //怎么判断是否为完全连通分量呢？
    //使用并查集区分连通分量，然后不同的连通分量中遍历所有的元素，查看元素是否有到其他所有点的边存在
    //成功，11ms
    public int countCompleteComponents(int n, int[][] edges) {
        int m = edges.length;
        cnt = new int[n];
        parent = new int[n];
        List<List<Integer>> e = new ArrayList<>();
        for(int i=0;i < n;i++) {
            parent[i] = i;
            cnt[i] = 1;
            e.add(new ArrayList<>());
        }
        for(int i=0;i < m;i++) {
            int x = edges[i][0], y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
            int px = getParent(x);
            int py = getParent(y);
            //合并为一个并查集
            parent[py] = px;
            cnt[px] += cnt[py];
        }
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i < n;i++) {
            int p = getParent(i);
            List<Integer> list = map.get(p);
            if(list == null) {
                list = new ArrayList<>();
                map.put(p,list);
            }
            list.add(i);
        }
        int res = 0;
        Set<Map.Entry<Integer, List<Integer>>> entries = map.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            boolean flag = true;
            List<Integer> list = entry.getValue();
            int size = list.size()-1;
            for (Integer i : list) {
                if(e.get(i).size() != size){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }
    private int[] parent;
    private int[] cnt;
    private int getParent(int loc) {
        if(loc == parent[loc]) {
            return loc;
        }
        parent[loc] = getParent(parent[loc]);
        return parent[loc];
    }
}
