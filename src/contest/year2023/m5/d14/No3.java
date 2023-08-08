package contest.year2023.m5.d14;

import java.util.Arrays;

/**
 * No2684.矩阵中移动的最大次数
 *
 * @author wjs 2023/8/9
 */
public class No3 {
    //暴力解法，超时
    /*int[][] grid;
    int m,n;
    public int maxMoves(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for(int i=0;i < m;i++) {
            res = Math.max(res,dfs(i,0,0));
        }
        return res;
    }

    private int dfs(int li, int lj, int pre) {
        if(li < 0 || li >= m || lj < 0 || lj >= n || pre >= grid[li][lj]) {
            return -1;
        }
        int val = -1;
        val = Math.max(val,dfs(li-1,lj+1,grid[li][lj]));
        val = Math.max(val,dfs(li,lj+1,grid[li][lj]));
        val = Math.max(val,dfs(li+1,lj+1,grid[li][lj]));
        return val + 1;
    }*/

    //动态规划，16ms
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0;i < m;i++) {
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i < m;i++) {
            dp[i][0] = 0;
        }
        int res = 0;
        for(int i=1;i < n;i++) {
            for(int j=0;j < m;j++) {
                if(j > 0 && grid[j-1][i-1] < grid[j][i] && dp[j-1][i-1] != -1) {
                    dp[j][i] = Math.max(dp[j][i],dp[j-1][i-1]+1);
                    res = Math.max(dp[j][i],res);
                }
                if(grid[j][i-1] < grid[j][i] && dp[j][i-1] != -1) {
                    dp[j][i] = Math.max(dp[j][i],dp[j][i-1]+1);
                    res = Math.max(dp[j][i],res);
                }
                if(j < m-1 && grid[j+1][i-1] < grid[j][i] && dp[j+1][i-1] != -1) {
                    dp[j][i] = Math.max(dp[j][i],dp[j+1][i-1]+1);
                    res = Math.max(dp[j][i],res);
                }
            }
        }
        return res;
    }
}
