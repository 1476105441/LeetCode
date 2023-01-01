package contest.year2022.m7.m7d31;

public class No3 {
    /**
     *      找到离给定两个节点最近的节点
     */

    //成功完成，23ms
    /*public static int closestMeetingNode(int[] edges, int node1, int node2) {
        //特判1，两点开始时就在同一位置上
        if (node1 == node2) {
            return node1;
        }
        int x = node1,y = node2;
        Set<Integer> set1 = new HashSet<>(),set2 = new HashSet<>();
        set1.add(x);
        set2.add(y);
        //循环终止条件是两点都无路可走了
        while (x != -1 || y != -1) {
            //有可能会出现其中一个点已经走到终点而另一个点还没有走到终点的情况，所以要特判
            if (y != -1) {
                y = edges[y];
                //判断x所走的路里有没有出现y，出现了就说明两点走到了同一点上，程序的出口在这里
                if (set1.contains(y)) {
                    //此时要判断x是不是同时走到了y走过的点上，如果是的话就要取两者中的较小值
                    if (x != -1) {
                        x = edges[x];
                        if (set2.contains(x)) {
                            return Math.min(x,y);
                        }
                    }
                    return y;
                }
                //如果y已经走入了循环，而x已经走到终点了，说明两点不可能相遇于一点
                if (!set2.add(y) && x == -1) {
                    return -1;
                }
            }
            if (x != -1) {
                //由于两者走相同的步数到达相遇的点的情况在y中已经判断过了，在这里就不需要再判断一次了
                x = edges[x];
                if (set2.contains(x)) {
                    return x;
                }
                if (!set1.add(x) && y == -1) {
                    return -1;
                }
            }
        }
        return -1;
    }*/

    /*class Solution {
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            int[] d1 = calcDis(edges, node1), d2 = calcDis(edges, node2);
            int ans = -1, n = edges.length;
            for (int i = 0, minDis = n; i < n; ++i) {
                var d = Math.max(d1[i], d2[i]);
                if (d < minDis) {
                    minDis = d;
                    ans = i;
                }
            }
            return ans;
        }

        int[] calcDis(int[] edges, int x) {
            var n = edges.length;
            var dis = new int[n];
            Arrays.fill(dis, n);
            for (var d = 0; x >= 0 && dis[x] == n; x = edges[x])
                dis[x] = d++;
            return dis;
        }
    }*/

    //巨佬的思路：计算出node1和node2到其他节点的距离
    public int closestMeetingNode(int[] edges,int node1,int node2){
        int n = edges.length,res = -1,min = n;
        int[] c1 = count(edges, node1),c2 = count(edges,node2);
        for (int i = 0; i < n; i++) {
            int max = Math.max(c1[i], c2[i]);
            if (max < min) {
                res = i;
                min = max;
            }
        }
        return res;
    }
    public int[] count(int[] edges,int x){
        int n = edges.length;
        int[] dis = new int[n];
        //初始化到所有节点的距离为n
        for (int i = 0; i < n; i++) {
            dis[i] = n;
        }
        int d = 0;
        //循环要判断是否走到最后一个点了，并且判断是否已经走过这个点
        for (int i = x;i >= 0 && dis[i] == n ; i = edges[i]) {
            dis[i] = d++;
        }
        return dis;
    }
}
