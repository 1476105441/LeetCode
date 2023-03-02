package algorithm.dp;

public class No62 {
    //不同路径2

    //动态规划，0ms
    public int uniquePathsWithObstacles(int[][] g) {
        int m = g.length,n = g[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = g[0][0] == 0 ? 1 : 0;
        for(int i = 0;i < m;i++){
            for(int j = i== 0 ? 1 : 0;j < n;j++){
                if(g[i][j] != 1){
                    if(i > 0){
                        dp[i][j] += dp[i-1][j];
                    }
                    if(j > 0){
                        dp[i][j] += dp[i][j-1];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }
}
