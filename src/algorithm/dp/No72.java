package algorithm.dp;

public class No72 {
    //编辑距离
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray(),w2 = word2.toCharArray();
        int m = w1.length,n = w2.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i <= m;i++){
            dp[i][0] = i;
        }
        for(int i = 0;i <= n;i++){
            dp[0][i] = i;
        }
        for(int i = 1;i <= m;i++){
            for(int j = 1;j <= n;j++){
                dp[i][j] = 1 + dp[i-1][j];
                dp[i][j] = Math.min(dp[i][j],1 + dp[i][j-1]);
                if(w1[i-1] == w2[j-1]){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1]);
                } else {
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1]+1);
                }
            }
        }
        return dp[m][n];
    }
}
