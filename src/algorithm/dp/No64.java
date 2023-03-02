package algorithm.dp;

public class No64 {
    //最小路径和

    //动态规划，2ms
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 0;i < m;i++){
            for(int j = i==0?1:0;j < n;j++){
                int temp = 2147483647;
                if(i > 0){
                    temp = dp[i-1][j];
                }
                if(j > 0){
                    temp = Math.min(dp[i][j-1],temp);
                }
                dp[i][j] = temp + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
