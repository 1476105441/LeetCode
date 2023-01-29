package algorithm.dp;

public class No10 {
    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray(),pc = p.toCharArray();
        int m = sc.length,n = pc.length;
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            if (pc[i-1] == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (pc[j - 1] == '*') {
                    if (pc[j - 2] == sc[i - 1] || pc[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                } else if (pc[j - 1] == '.' || pc[j - 1] == sc[i - 1]) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
