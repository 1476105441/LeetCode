package junior.动态规划;

public class LeetCode5 {
    //动态规划解决
    public static String longestPalindrome(String s) {
        //字符串长度
        int len = s.length();
        if (len < 2) {
            return s;
        }

        //表示从i字符到j字符是否是一个回文字符串
        boolean[][] dp = new boolean[len][len];
        //i是前面的字符，j是后面第j个字符，l是第i个字符到第j个字符的长度，flag是标记已得到的最长回文串的长度
        int i = 0, j, l, flag = 1, begin =0;
        //returnString是用来存放最长回文子串的
        String returnString ;
        //设置单个字符为真
        for (i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //遍历得到子串是否为回文子串
        for (l = 2; l <= len; l++) {
            //j=l+i-1;
            for (i = 0; i < len + 1 - l; i++) {
                j = l + i - 1;
                //判断第i个字符和第j个字符是否相等
                if (s.charAt(i) == s.charAt(j)) {
                    if (l == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    //若第i个字符到第j个字符是回文串
                    if (dp[i][j] == true) {
                        if (l > flag) {
                            //更新flag
                            flag = l;
                            begin = i;
                        }
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        returnString = s.substring(begin,begin+flag);
        return returnString;
    }

    public static void main(String[] args) {
        String s = "ac";
        System.out.println(longestPalindrome(s));
    }
}
