package contest.year2023.m4.d16;

import java.util.*;

/**
 * 2646. 最小化旅行的价格总和
 *
 * @author wjs 2023/5/28
 */
public class No4 {
    //思路：总共可以将节点分为两拨来减半节点值，然后再跑dfs
    //失败，因为按批次来算，但同一批次中的节点可以和不同批次的节点组合运算
    /*List<List<Integer>> e;
    Set<Integer>[] sets;
    int[] price;
    int n;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        e = new ArrayList<>();
        this.price = price;
        this.n = n;
        for(int i=0;i < n;i++) {
            e.add(new ArrayList<>());
        }
        for(int i=0;i < edges.length;i++) {
            int x = edges[i][0], y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        partition();
        // for(Integer i : sets[1]) {
        //     System.out.print(i + " ");
        // }

        int s1 = 0, s2 = 0;
        boolean[] vis = new boolean[n];
        for(int j=0;j < trips.length;j++) {
            int from = trips[j][0], to = trips[j][1];
            int[] tmp = dfs(from,to,vis);
            s1 += tmp[0];
            s2 += tmp[1];
        }
        return Math.min(s1,s2);
    }

    private int[] dfs(int idx, int end, boolean[] vis) {
        if(idx == n) {
            return null;
        }
        if(idx == end) {
            return new int[]{getVal(end,0),getVal(end,1)};
        }
        vis[idx] = true;
        int[] res = null;
        for(Integer next : e.get(idx)) {
            if(!vis[next]) {
                res = dfs(next,end,vis);
                if(res != null) {
                    res[0] += getVal(idx,0);
                    res[1] += getVal(idx,1);
                    break;
                }
            }
        }
        vis[idx] = false;
        return res;
    }

    private int getVal(int idx, int par) {
        return sets[par].contains(idx) ? price[idx]/2 : price[idx];
    }

    private void partition() {
        sets = new Set[2];
        sets[0] = new HashSet<Integer>();
        sets[1] = new HashSet<Integer>();
        boolean[] set = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        int depth = 0;
        queue.add(0);
        set[0] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                int idx = queue.poll();
                sets[depth%2].add(idx);
                for(Integer next : e.get(idx)) {
                    if(!set[next]) {
                        set[next] = true;
                        queue.add(next);
                    }
                }
                size--;
            }
            depth++;
        }
    }*/
}
