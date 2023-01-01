package contest.year2022.m10.m10d9;

public class No4 {
    /**
     *      矩阵中和能被 K 整除的路径
     */

    /*int[][] grid;
    int m,n,k;
    public int numberOfPaths(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        this.k = k;
        return 0;
    }

    public void dfs(int row,int col,int val){
        if(row < 0 || col < 0 || row >= m || col >= n)
            return;
        if(row == m-1 && col == n-1){

        }
    }*/


    public int numberOfPaths(int[][] grid, int k){
        int m = grid.length,n = grid[0].length,MOD = 1000000007;
        int[][][] dp = new int[m][n][k];

        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    if (j + 1 < n) {
                        int loc = (l+grid[i][j+1])%k;
                        dp[i][j+1][loc] = (dp[i][j+1][loc] + dp[i][j][l]) % MOD;
                    }
                    if (i + 1 < m) {
                        int loc = (l+grid[i+1][j])%k;
                        dp[i+1][j][loc] = (dp[i+1][j][loc] + dp[i][j][l]) % MOD;
                    }
                }
            }
        }

        return dp[m-1][n-1][0];
    }
}
