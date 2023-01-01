package contest.year2022.m8.m8d14;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No2 {
    /**
     *      边积分最高的节点
     */

    public int edgeScore(int[] edges) {
        Map<Integer,Long> map = new HashMap<>();
        int n = edges.length,res = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            map.put(edges[i],map.getOrDefault(edges[i],0L)+i);
        }

        Set<Integer> set = map.keySet();

        for(Integer key:set){
            Long val = map.get(key);
            if (val > max) {
                res = key;
                max = val;
            } else if (val == max && key < res) {
                res = key;
            }
        }

        return res;
    }
}
