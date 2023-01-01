package contest.year2022.m9.m9d18;

public class No2 {
    /**
     *      最长的字母序连续子字符串的长度
     */

    public int longestContinuousSubstring(String s) {
        char[] c = s.toCharArray();
        int n = c.length,dp = 1,res = 1;
        for (int i = 1; i < n; i++) {
            if (c[i] == c[i - 1] + 1) {
                dp++;
            }else
                dp = 1;
            res = Math.max(dp,res);
        }
        return res;
    }
}
