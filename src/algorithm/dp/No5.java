package algorithm.dp;

public class No5 {
    //最长回文子串

    //解法一：dp，90ms
    /*public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int max = 0,l = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int k = j + i;
                if (chars[j] == chars[k]) {
                    dp[j][k] = j == k-1 || dp[j+1][k-1];
                }
                if (dp[j][k] && i > max) {
                    max = i;
                    l = j;
                }
            }
        }

        return new String(chars,l,max+1);
    }*/

    //解法二：中心扩散法，5ms
    /*public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int max = 0,l = 0;

        for (int i = 0; i < n; i++) {
            int len = 1;
            int j = i - 1,k = i + 1;
            while (j >= 0 && k < n && chars[j] == chars[k]) {
                len += 2;
                j--;
                k++;
            }
            if (len > max) {
                max = len;
                l = j + 1;
            }
            if (i < n - 1 && chars[i] == chars[i+1]) {
                len = 2;
                j = i - 1;
                k = i + 2;
                while (j >= 0 && k < n && chars[j] == chars[k]) {
                    len += 2;
                    j--;
                    k++;
                }
                if (len > max) {
                    max = len;
                    l = j + 1;
                }
            }
        }

        return new String(chars,l,max);
    }*/

    //解法三：马拉车算法，多次改动总算通过，3ms
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[2 * n + 1];
        char[] temp = new char[2 * n + 1];
        int m = temp.length;
        //1、将原字符串数组进行填充，这是为了统一进行处理
        for (int i = 0; i < m; i++) {
            if ((i & 1) == 1) {
                temp[i] = chars[i / 2];
            } else {
                temp[i] = '*';
            }
        }
        //2、开始中心扩散，记录最右边的中心点和臂长
        int r = 0,rLen = 0,res = 0,resLen = 0;
        for (int i = 0; i < m; i++) {
            int j,k,len = 0;
            if (r + rLen > i) {
                len = Math.min(dp[2 * r - i],r + rLen - i);
            }
            j = i - (len + 1);
            k = i + (len + 1);
            while (j >= 0 && k < m && temp[j] == temp[k]) {
                len++;
                j--;
                k++;
            }
            dp[i] = len;
            if (i + len > r + rLen) {
                r = i;
                rLen = len;
            }
            if (len > resLen) {
                res = i;
                resLen = len;
            }
        }
        int start = (res - resLen) >> 1,end = (res + resLen) >> 1;
        return s.substring(start,end);
    }
}
