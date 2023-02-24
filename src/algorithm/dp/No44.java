package algorithm.dp;

public class No44 {
    //通配符匹配
    //动态规划，9ms
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray(),ps = p.toCharArray();
        int m = cs.length,n = ps.length;
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 1;
        for(int i = 1;i <= n;i++){
            if(ps[i-1] == '*'){
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for(int i = 1;i <= m;i++){
            for(int j = 1;j <= n;j++){
                if(ps[j-1] == cs[i-1] || ps[j-1] == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(ps[j-1] == '*'){
                    dp[i][j] = dp[i][j-1] | dp[i-1][j];
                }
            }
        }
        return dp[m][n] == 1;
    }
}
