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


    //成功，9ms
    //dp解法，对于一个结点，有两种可能的选择：选择减半或者不减半，如何选择取决于相邻的节点
    //将题目转为不变的值：计算出每个点经过的次数，将其权值转换为原本的值乘以经过的次数
    List<List<Integer>> e;
    int[] count;
    int n;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        count = new int[n];
        this.n = n;
        e = new ArrayList<>(n);
        for(int i=0; i < n; i++) {
            e.add(new ArrayList<>());
        }
        for(int i=0;i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            e.get(x).add(y);
            e.get(y).add(x);
        }
        boolean[] set = new boolean[n];
        for(int i=0;i < trips.length; i++) {
            dfs(trips[i][0],trips[i][1],set);
        }
        for(int i=0; i < n; i++) {
            //System.out.print(count[i]+" ");
            count[i] = count[i] * price[i];
        }
        int[] tmp = dp(0,set);
        return Math.min(tmp[0],tmp[1]);
    }
    private int[] dp(int idx, boolean[] set) {
        set[idx] = true;
        int sum0 = count[idx],sum1 = sum0 / 2;
        for(Integer next : e.get(idx)) {
            if(!set[next]) {
                int[] tmp = dp(next,set);
                sum0 += Math.min(tmp[0],tmp[1]);
                sum1 += tmp[0];
            }
        }
        set[idx] = false;
        return new int[]{sum0,sum1};
    }
    private boolean dfs(int idx, int to, boolean[] set) {
        if(idx == to) {
            count[idx]++;
            return true;
        }
        set[idx] = true;
        for(Integer next : e.get(idx)) {
            if(!set[next]) {
                if(dfs(next,to,set)) {
                    count[idx]++;
                    set[idx] = false;
                    return true;
                }
            }
        }
        set[idx] = false;
        return false;
    }
}
