package review.数组;

public class No16 {
    /**
     *      最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 提示：
     *     1 <= s.length <= 1000
     *     s 仅由数字和英文字母组成
     */

    //动态规划解决：dp[i][j]表示从i到j是不是回文串
    /*public String longestPalindrome(String s){
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        String res = "";
        int max = 0;

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                dp[i][i+1] = true;
                if (max < 2) {
                    max = 2;
                    res = new String(chars,i,2);
                }
            }
            if (max == 0) {
                res = chars[i] + "";
                max = 1;
            }
        }

        for (int i = 3; i <= chars.length; i++) {
            for (int j = 0; j < chars.length - i + 1; j++) {
                int loc = i + j -1;
                dp[j][loc] = chars[j] == chars[loc] && dp[j+1][loc-1];
                if(dp[j][loc] && i > max){
                    max = i;
                    res = new String(chars,j,i);
                }
            }
        }

        return res;
    }*/

    //中心扩散法
    /*public String longestPalindrome(String s){
        char[] chars = s.toCharArray();
        int maxLength = 0;
        String res = "";

        for (int i = 0; i < chars.length; i++) {
            int j = i+1,k = i-1,l = 1;
            while (k > -1 && j < chars.length && chars[j] == chars[k]) {
                l += 2;
                k--;
                j++;
            }
            if (l > maxLength) {
                maxLength = l;
                res = new String(chars,k+1,l);
            }

            if(i < chars.length-1 && chars[i] == chars[i+1]){
                l = 2;
                j = i+2;
                k = i-1;
                while (k > -1 && j < chars.length && chars[j] == chars[k]) {
                    l += 2;
                    k--;
                    j++;
                }
                if (l > maxLength) {
                    maxLength = l;
                    res = new String(chars,k+1,l);
                }
            }
        }

        return res;
    }*/

    //马拉车算法，在中心扩散算法的基础上，增加了效率
    //引用臂长的概念
    public String longestPalindrome(String s){
        //1、将所有字符串变成奇数字符串，为了确保一般化
        char[] chars = s.toCharArray();
        char[] temp = new char[2*chars.length+1];

        for (int i = 0; i < temp.length; i++) {
            if ((i & 1) == 1) {
                temp[i] = chars[i/2];
            }else {
                temp[i] = '*';
            }
        }

        //2、开始中心扩散
        int[] length = new int[temp.length];
        int rCenter = 0,rLength = 0,mCenter = 0,mLength = 0;
        for (int i = 0; i < temp.length; i++) {
            int j,k;
            if (i < rCenter + rLength) {
                //找到对称点
                int p = 2*rCenter - i,dis = rCenter + rLength - i;

                if (length[p] < dis) {
                    length[i] = length[p];
                }else{
                    length[i] = dis;
                    j = i + dis + 1;
                    k = i - dis - 1;
                    while (k > -1 && j < temp.length && temp[j] == temp[k]) {
                        length[i]++;
                        k--;
                        j++;
                    }
                }

                if (i + length[i] > rCenter + rLength) {
                    rCenter = i;
                    rLength = length[i];
                }
            }else{
                j = i+1;
                k = i-1;
                length[i] = 0;
                while (k > -1 && j < temp.length && temp[j] == temp[k]) {
                    length[i]++;
                    k--;
                    j++;
                }
                rCenter = i;
                rLength = length[i];
            }

            if (length[i] > mLength) {
                mCenter = i;
                mLength = length[i];
            }
        }

        int begin = (mCenter - mLength) / 2,end = (mCenter + mLength) / 2;

        return s.substring(begin,end);
    }
}
