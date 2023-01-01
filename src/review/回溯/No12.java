package review.回溯;

public class No12 {
    /**
     *      正则表达式匹配
     */

    //失败，考虑的不周全
    /*public boolean isMatch(String s,String p){
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        boolean[][] dp = new boolean[sChar.length+1][pChar.length+1];

        dp[0][0] = true;
        for (int i = 1; i < sChar.length + 1;i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i < pChar.length + 1; i++) {
            dp[0][i] = false;
        }

        char pre = '1';
        for (int i = 1; i < sChar.length + 1; i++) {
            for (int j = 1; j < pChar.length + 1; j++) {
                if (pChar[j - 1] == sChar[i - 1] || pChar[j - 1] == '.') {
                    pre = pChar[j-1];
                    dp[i][j] = dp[i-1][j-1];
                } else if (pChar[j - 1] == '*') {
                    //另外一种情况没有考虑进去，*可以消除前一个不对的情况
                    dp[i][j] = dp[i][j-1] || (dp[i-1][j] && (pre == sChar[i-1]||pre == '.') || dp[i-1][j-2]);
                }else{
                    dp[i][j] = false;
                }
            }
            pre = '1';
        }

        return dp[sChar.length][pChar.length];
    }*/

    //其实当我们一个方向思考的不对
    //时，可以换个方向来进行思考，
    //观看了题解之后发现这个题目可
    //以从后往前的来看
    public boolean isMatch(String s,String p){
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        boolean[][] dp = new boolean[sChar.length+1][pChar.length+1];
        dp[0][0] = true;
        for (int i = 1; i < pChar.length + 1; i++) {
            if (pChar[i - 1] == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i < sChar.length+1; i++) {
            for (int j = 1; j < pChar.length + 1; j++) {
                if (pChar[j - 1] == '*') {
                    if (pChar[j - 2] == sChar[i - 1] || pChar[j - 2] == '.') {
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }else{
                    if (pChar[j - 1] == '.' || pChar[j - 1] == sChar[i - 1]) {
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[sChar.length][pChar.length];
    }
}
