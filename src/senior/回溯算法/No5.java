package senior.回溯算法;

public class No5 {
    /**
     *          正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *     '.' 匹配任意单个字符
     *     '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * 提示：
     *     1 <= s.length <= 20
     *     1 <= p.length <= 30
     *     s 只包含从 a-z 的小写字母。
     *     p 只包含从 a-z 的小写字母，以及字符 . 和 *。
     *     保证每次出现字符 * 时，前面都匹配到有效的字符
     */

    /**
     * 解法一：动态规划解法
     * 用二维数组dp[i][j]表示s的前i个字符串能否和p的前j个字符串匹配
     * '*'表示前面那一个元素可以匹配0次或者多次
     */

    //注意边界问题
    /*public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        char pre = pChar[0];
        int m = sChar.length,n = pChar.length;
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        for (int i = 2; i <= n; i++) {
            if (pChar[i - 1] == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pChar[j-1] == '*') {
                    dp[i][j] = ((pChar[j-2] == sChar[i-1] || pChar[j-2] == '.') && dp[i-1][j]) || dp[i][j-2];
                } else {
                    dp[i][j] = dp[i-1][j-1] && (pChar[j-1] == sChar[i-1] || pChar[j-1] == '.');
                }
            }
        }

        return dp[m][n];
    }*/


    //******************重写*****************
    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray(),pc = p.toCharArray();
        int m = sc.length,n = pc.length;
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        //处理边界
        for (int i = 1; i < n+1; i++) {
            if(pc[i-1] == '*')
                dp[0][i] = dp[0][i-2];
        }

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (pc[i - 1] == '*') {
                    dp[i][j] = (pc[j-2] == sc[i-1] || pc[j-2] == '.') && dp[i-1][j] || dp[i][j-2];
                }else{
                    dp[i][j] = (pc[j-1] == sc[i-1] || pc[j-1] == '.') && dp[i-1][j-1];
                }
            }
        }

        return dp[m][n];
    }
}
