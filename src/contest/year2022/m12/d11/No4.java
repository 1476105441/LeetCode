package contest.year2022.m12.d11;

import java.util.Arrays;
import java.util.PriorityQueue;

public class No4 {
    //使用线段树维护区间的值？并不行
    /*public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length,n = grid[0].length,k = queries.length;
        int[] res = new int[k];

        return res;
    }*/

    //使用并查集
    /*private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[] parent,size;
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length,n = grid[0].length,mn = m * n,k = queries.length;
        parent = new int[mn];
        size = new int[mn];
        int[][] temp = new int[mn][3];
        int[] res = new int[k];
        Integer[] qt = new Integer[k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int loc = i * n + j;
                temp[loc][0] = grid[i][j];
                temp[loc][1] = i;
                temp[loc][2] = j;
                parent[loc] = loc;
                size[loc] = 1;
            }
        }
        for (int i = 0; i < k; i++) {
            qt[i] = i;
        }
        Arrays.sort(temp,(x,y) -> x[0]-y[0]);
        Arrays.sort(qt,(x,y) -> queries[x] - queries[y]);
        int j = 0;
        for (int i = 0; i < k; i++) {
            int val = queries[qt[i]];
            //进入循环说明比当前查询的值小
            for (; j < mn && temp[j][0] < val; j++) {
                for (int[] dir : dirs) {
                    int x = temp[j][1] + dir[0],y = temp[j][2] + dir[1];
                    //如果没超出范围，并且满足小于val，合并到一个集合中
                    if (x > -1 && x < m && y > -1 && y < n && grid[x][y] < val) {
                        int loc1 = temp[j][1] * n + temp[j][2],loc2 = x * n + y;
                        int p1 = find(loc1),p2 = find(loc2);
                        if (p1 != p2) {
                            if (size[p1] > size[p2]) {
                                parent[p2] = p1;
                                size[p1] += size[p2];
                            } else {
                                parent[p1] = p2;
                                size[p2] += size[p1];
                            }
                        }
                    }
                }
            }
            //只有比第一个元素大的时候才能加上并查集的大小
            if(val > grid[0][0])
                res[qt[i]] = size[find(0)];
        }

        return res;
    }
    public int find(int x){
        if(x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }*/


    //使用堆，主要思想也是对查询排序，但是从起点开始维护一个最小
    //堆，如果当前堆顶比当前查询要小，则将当前堆顶出堆，并将周围
    //没有入堆的元素放入堆
    int[][] ints = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length,n = grid[0].length,k = queries.length,mn = m*n;
        int[] res = new int[k];
        Integer[] temp = new Integer[k];
        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> x[0]-y[0]);
        queue.add(new int[]{grid[0][0],0,0});
        grid[0][0] = 0;
        for (int i = 0; i < k; i++) {
            temp[i] = i;
        }
        Arrays.sort(temp,(x,y) -> queries[x] - queries[y]);
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && queue.peek()[0] < queries[temp[i]]) {
                cnt++;
                int[] poll = queue.poll();
                for (int[] t : ints) {
                    int x = poll[1]+t[0],y = poll[2]+t[1];
                    if (x > -1 && x < m && y > -1 && y < n && grid[x][y] > 0) {
                        queue.add(new int[]{grid[x][y],x,y});
                        grid[x][y] = 0;
                    }
                }
            }
            res[temp[i]] = cnt;
        }
        return res;
    }
}
