package senior.其他;

import java.util.*;

public class No3 {
    /**
     *      天际线问题
     */

    //完全没有思路，看题解
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length,loc = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temp.add(buildings[i][0]);
            temp.add(buildings[i][1]);
        }
        Collections.sort(temp);
        //因为优先队列存是最小堆算法，所以compare方法给一个相反compare函数，使之堆顶是最大值
        PriorityQueue<int[]> p = new PriorityQueue<>((a,b)->b[1]-a[1]);
        List<List<Integer>> res = new ArrayList<>();

        for (Integer i : temp){
            //把符合条件的建筑加入到优先队列中
            while (loc < n && buildings[loc][0] <= i) {
                p.offer(new int[]{buildings[loc][1],buildings[loc][2]});
                loc++;
            }
            //筛掉不符合条件的队头
            while (!p.isEmpty() && p.peek()[0] <= i) {
                p.poll();
            }
            //得到关键点的高度
            int max = p.isEmpty() ? 0:p.peek()[1];
            if (res.isEmpty() || max != res.get(res.size() - 1).get(1)) {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                l.add(max);
                res.add(l);
            }
        }

        return res;
    }

}
/*class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }
}*/

