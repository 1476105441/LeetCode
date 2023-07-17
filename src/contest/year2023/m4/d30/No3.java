package contest.year2023.m4.d30;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2662.前往目标的最小代价
 *
 * @author wjs 2023/7/13
 */
public class No3 {
    //最小代价怎么选？
    //dijkstra算法解法
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Map<Long,Integer> dis = new HashMap<>();
        Set<Long> vis = new HashSet<>();
        int min = target[0] - start[0] + target[1] - start[1];
        int sx = start[0], sy = start[1], tx = target[0], ty = target[1];
        long end = ((long)tx << 32) + ty;
        //便于结束循环
        dis.put(end,Integer.MAX_VALUE);
        for(int[] tmp : specialRoads) {
            //计算到达每个终点的距离
            int d = Math.abs(sx - tmp[0]) + Math.abs(sy - tmp[1]) + tmp[4];
            long key = ((long)tmp[2] << 32) + tmp[3];
            dis.put(key,Math.min(dis.getOrDefault(key,Integer.MAX_VALUE),d));
        }
        long p = ((long)tx << 32 + ty);
        int v = 0;
        for(;;) {
            int tmpV = Integer.MAX_VALUE;
            //选出未遍历的集合中距离最小的节点
            for(Long key : dis.keySet()) {
                if(!vis.contains(key)) {
                    int y = (int)((long)key), x = (int)((key >> 32) & Integer.MAX_VALUE);
                    if(tmpV >= dis.get(key)) {
                        tmpV = dis.get(key);
                        p = key;
                    }
                }
            }
            int py = (int)p, px = (int)((p >> 32) & Integer.MAX_VALUE);
            //System.out.println(tmpV+" "+px+" "+py);
            if(tmpV < Integer.MAX_VALUE) {
                vis.add(p);
                v = tmpV;
                min = Math.min(min,v + tx - px + ty - py);
                //System.out.println(min);
            }
            if(p == end) {
                return min;
            }
            for(int[] tmp : specialRoads) {
                long tp = ((long)tmp[2] << 32) + tmp[3];
                if(!vis.contains(tp)) {
                    dis.put(tp,Math.min(dis.get(tp),v + Math.abs(px-tmp[0]) + Math.abs(py-tmp[1]) + tmp[4]));
                }
            }
        }
    }
}
