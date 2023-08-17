package contest.year2023.m5.d21;

import java.util.*;

/**
 * 2699.修改图中的边权
 *
 * @author wjs 2023/8/15
 */
public class No4 {
    //想的太复杂，失败
    /*List<List<int[]>> e;
    boolean[] vis;
    TreeSet<Edge> fillEdge;
    int destination;
    Map<Location,int[]> map;
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        int m = edges.length;
        e = new ArrayList<>();
        vis = new boolean[n];
        this.destination = destination;
        fillEdge = new TreeSet<>();
        map = new HashMap<>();
        for(int i=0;i < n;i++) {
            e.add(new ArrayList<>());
        }
        for(int i=0;i < m;i++) {
            int x = edges[i][0], y = edges[i][1], val = edges[i][2];
            e.get(x).add(new int[]{y,val});
            e.get(y).add(new int[]{x,val});
            Location l = new Location();
            l.x = x;
            l.y = y;
            map.put(l,edges[i]);
        }
        dfs(source,0,new ArrayList<>());
        Edge first = fillEdge.first();
        if (first.val > target) {
            return new int[][]{};
        } else if (first.val == target) {
            if (!first.edges.isEmpty()) {
                return new int[][]{};
            }
        } else {
            if (first.edges.isEmpty()) {
                return new int[][]{};
            }
            int size = first.edges.size();
            int dif = target - first.val, span = dif / size, mod = dif % size;
            int[] ints = map.get(new Location(first.edges.get(0)));
            ints[2] = span + mod;
            for (int i = 1; i < size; i++) {
                ints = map.get(new Location(first.edges.get(i)));
                ints[2] = span;
            }
        }
        for (int i = 0; i < m; i++) {
            if (edges[i][2] == -1) {
                edges[i][2] = 1;
            }
        }
        return edges;
    }
    private void dfs(int loc, int val, List<int[]> list) {
        if (loc == destination) {
            Edge edge = new Edge();
            edge.val = val;
            edge.edges = new ArrayList<>(list);
            fillEdge.add(edge);
            return;
        }
        vis[loc] = true;
        for (int[] dir : e.get(loc)) {
            int next = dir[0], v = dir[1];
            if(!vis[next]) {
                if(v != -1) {
                    dfs(next,val + v,list);
                } else {
                    list.add(new int[]{loc,next});
                    dfs(next,val,list);
                    list.remove(list.size()-1);
                }
            }
        }
    }
    class Edge implements Comparable<Edge>{
        int val;
        List<int[]> edges = new ArrayList<>(1024);

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return val == edge.val && Objects.equals(edges, edge.edges);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, edges);
        }

        @Override
        public int compareTo(Edge o) {
            if (this.val != o.val) {
                return this.val - o.val;
            }
            return this.edges.size() - o.edges.size();
        }
    }
    class Location {
        int x,y;

        public Location(int[] loc) {
            x = loc[0];
            y = loc[1];
        }

        public Location() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }*/

    //两次dijkstra遍历
    List<List<int[]>> e;
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        int m = edges.length;
        e = new ArrayList<>();
        int[][] dis = new int[n][2];
        for(int i=0;i < n;i++) {
            e.add(new ArrayList<>());
            if(i != source){
                dis[i][0] = dis[i][1] = Integer.MAX_VALUE;
            }
        }
        for(int i=0;i < m;i++) {
            int x = edges[i][0], y = edges[i][1];
            e.get(x).add(new int[]{y,i});
            e.get(y).add(new int[]{x,i});
        }
        //将-1的边全部改为1，跑一遍dijkstra算法
        int v = target - dijkstra(dis,edges,destination,0,0);
        if(v < 0) {
            //System.out.println(dis[destination][1]);
            return new int[][]{};
        }

        v = target - dijkstra(dis,edges,destination,v,1);
        if(v > 0) {
            //System.out.println(dis[destination][1]);
            return new int[][]{};
        }
        for(int i=0;i < m;i++) {
            if(edges[i][2] == -1) {
                edges[i][2] = 1;
            }
        }
        return edges;
    }
    private int dijkstra(int[][] dis, int[][] edges, int destination, int v, int k) {
        int n = dis.length;
        boolean[] vis = new boolean[n];
        for(;;) {
            int x = -1;
            //寻找当前最短路径的点
            for(int i=0;i < n;i++) {
                if(!vis[i] && (x < 0 || dis[i][k] < dis[x][k])) {
                    x = i;
                }
            }
            //到达了终点
            if(x == destination) {
                return dis[x][k];
            }
            vis[x] = true;
            //用当前点更新最短路径
            for(int[] tmp : e.get(x)) {
                int y = tmp[0], idx = tmp[1];
                int w = edges[idx][2];
                if(edges[idx][2] == -1) {
                    w = 1;
                }
                if(k == 1 && edges[idx][2] == -1) {
                    //注意w可能是小于0的
                    w = v - dis[x][k] + dis[y][0];
                    if(w > 1) {
                        edges[idx][2] = w;
                    } else {
                        w = 1;
                    }
                }
                dis[y][k] = Math.min(dis[y][k],dis[x][k]+w);
            }
        }
    }
}
