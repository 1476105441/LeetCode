package contest.year2022.m11.d6;

import java.util.Arrays;
import java.util.List;

public class No4 {
    /**
     * 最小移动总距离
     */

    //最主要的点在于：按照坐标顺序选取的机器人的维修工厂序号是非严格单调递增的
    //所以我们需要做的，就是找出每个工厂应该安排哪些机器人
    /*public long minimumTotalDistance1(List<Integer> robot,int[][] factory){
        //先给机器人和工厂排序
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(x -> x[0]));
        int n = robot.size(),m = factory.length;
        long inf = 1000000000000L;
        //f[i][j]代表机器人i之前的所有机器人都已经安排完成，并且把当前机器人i安排到第j个工厂所需要花费的总距离
        //g[i][j]代表机器人i到所有的工厂j'中的最小总距离，即g[i][j] = min(f[i][j']) , 0 <= j' <= j
        //d(l,r,x)代表把机器人l到机器人r全部交给工厂x处理所需要的总距离
        //状态转移方程为：f[i][j] = min (f[i'][j']+d(i'+1,i,j))
        //解读一下这个方程：要使得f[i][j]最小，那么就需要看当前工厂是不是容纳之前的机器人时，会使总距离最小，其
        //中，在j之前的工厂并不是一定要维修机器人，而是看哪种情况下能够获得最小值
        long[][] f = new long[n+1][m+1],g = new long[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = g[i][j] = inf;
            }
        }
        f[0][0] = 0;
        for (int i = 0; i <= m; i++) {
            g[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                long d = 0;
                for (int k = i-1; k >= 0 && i - k <= factory[j-1][1]; k--) {
                    d += Math.abs(robot.get(k)-factory[j-1][0]);
                    f[i][j] = Math.min(f[i][j],g[k][j-1] + d);
                }
            }
            for (int j = 1; j <= m; j++) {
                g[i][j] = Math.min(g[i][j-1],f[i][j]);
            }
        }
        return g[n][m];
    }*/

    //解法一：动态规划
    /*public long minimumTotalDistance(List<Integer> robot,int[][] factory){
        int n = robot.size(),m = factory.length;
        long INF = 10000000000000L;
        Collections.sort(robot);
        Arrays.sort(factory,Comparator.comparingInt(x->x[0]));
        long[][] f = new long[n+1][m+1],g = new long[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = g[i][j] = INF;
            }
        }
        //初始化动态规划数组
        f[0][0] = 0;
        for (int i = 0; i <= m; i++) {
            g[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                long d = 0;
                //从当前机器人开始，往前遍历是否要将机器人k放入到工厂j中去维修
                for (int k = i-1; k >= 0 && i-k <= factory[j-1][1]; k--) {
                    d += Math.abs(robot.get(k) - factory[j-1][0]); //相当于是d(l,r,x)，不断的累计距离
                    //为什么这里是g[k][j-1]而不是g[k-1][j-1]？ 干，我明白了，因为这里的下标是不对
                    //等的，robot中和g[][]中的下标不对等，比如下标1，robot.get(1)获得的是相当于
                    //g中下标为2的元素的值
                    f[i][j] = Math.min(f[i][j],g[k][j-1]+d);
                }
            }
            for (int j = 1; j <= m; j++) {
                g[i][j] = Math.min(g[i][j-1],f[i][j]);
            }
        }

        //因为g[i][j]代表的是给定机器人i的情况下，改变工厂j'使 0 <= j' <= j，得到的最小的f[i][j']就是g[i][j]
        //所以直接返回g[n][m]即可
        return g[n][m];
    }*/


    //解法二：记忆化搜索，41ms
    /*long[][] f;
    long inf = (long) 1e13;
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int n = robot.size(), m = factory.length;
        Collections.sort(robot);
        Arrays.sort(factory,Comparator.comparingInt(x->x[0]));
        f = new long[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i],inf);
        }

        return dfs(0,0,robot,factory);
    }
    public long dfs(int i,int j,List<Integer> robot,int[][] factory){
        int n = robot.size(), m = factory.length;
        if(i == n)
            return 0;
        //当工厂已经遍历到最后一个时，要特殊判断了
        if (j == m - 1) {
            if(n-i > factory[j][1])
                return inf;
            long d = 0;
            for (int k = i; k < n; k++) {
                d += Math.abs(robot.get(k) - factory[j][0]);
            }
            f[i][j] = d;
            return d;
        }
        if(f[i][j] != inf)
            return f[i][j];
        //当前工厂先不做任何处理
        long res = dfs(i,j+1,robot,factory);
        long d = 0;
        int limit = Math.min(n,i+factory[j][1]);
        //当前工厂开始容纳机器人，计算出其中最小的总距离
        for (int k = i; k < limit; k++) {
            //容纳机器人k，累加距离要加上k到当前工厂的距离
            d += Math.abs(robot.get(k) - factory[j][0]);
            //dfs计算后面的最小总距离，然后判断得出当前的最小总距离
            res = Math.min(dfs(k+1,j+1,robot,factory)+d,res);
        }
        f[i][j] = res;
        return res;
    }*/


    //大佬的解法
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        int[] r = robot.stream().mapToInt(i -> i).toArray();
        Arrays.sort(r);
        int m = r.length;
        long[] f = new long[m + 1];
        Arrays.fill(f, (long) 1e18);
        f[0] = 0;
        for (int[] fa : factory)
            for (int j = m; j > 0; j--) {
                long cost = 0L;
                for (int k = 1; k <= Math.min(j, fa[1]); ++k) {
                    cost += Math.abs(r[j - k] - fa[0]);
                    f[j] = Math.min(f[j], f[j - k] + cost);
                }
            }
        return f[m];
    }
}
