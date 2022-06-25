package 复习.回溯;

public class No11 {
    /**
     *      通配符匹配
     */

    //暴力，将每种情况都遍历一遍，理论上
    //是可以完成的，但是超出了时间限制
    /*char[] sChar,pChar;
    public boolean isMatch(String s, String p) {
        sChar = s.toCharArray();
        pChar = p.toCharArray();

        return dfs(0,0);
    }

    public boolean dfs(int loc1,int loc2){
        if (loc1 == sChar.length) {
            if (loc2 == pChar.length) {
                return true;
            }
            for (int i = loc2; i < pChar.length; i++) {
                if (pChar[i] != '*') {
                    return false;
                }
            }
            return true;
        }
        if (loc2 == pChar.length) {
            return false;
        }

        if (sChar[loc1] == pChar[loc2] || pChar[loc2] == '?') {
            return dfs(loc1+1,loc2+1);
        } else if (pChar[loc2] == '*') {
            if (dfs(loc1 + 1, loc2)) {
                return true;
            }
            if (dfs(loc1, loc2 + 1)) {
                return true;
            }
            return dfs(loc1+1,loc2+1);
        }
        return false;
    }*/

    //重新思考一下，如何使用动态规划？
    //使用二维数组，dp[i][j]代表p中
    //的前j个字符串是可以匹配s中的前i
    //个字符串

    /**
     * 要注意下标的细节！！！
     */
    /*public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        boolean[][] dp = new boolean[sChar.length+1][pChar.length+1];
        
        //处理边界
        dp[0][0] = true;
        for (int i = 1; i < sChar.length+1; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i < pChar.length+1; i++) {
            dp[0][i] = pChar[i-1] == '*' && dp[0][i-1];
        }
        
        for (int i = 1; i < sChar.length+1; i++) {
            for (int j = 1; j < pChar.length+1; j++) {
                if (pChar[j-1] == sChar[i-1] || pChar[j-1] == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pChar[j-1] == '*') {
                    //前一个是使用这个任意匹配符，后一个是不使用
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[sChar.length][pChar.length];
    }*/


    //解法二：贪心算法
    /*
    这个贪心算法很难思考到（至少对于我来说）
    大概思路是这样的：
        1、先考虑一些情况，比如p的前后都有
        '*'时，中间是一些字符串，那么我们就
        只需要在s中匹配中间的字符串就可以了，
        成功的条件是s中所有元素被匹配完，且p
        剩下的元素都是*或者p的所有元素也刚好
        匹配完成。
        2、然后由中间的情况推广到一般的情况，
        如果中间有很多个被*分割开的字符串，那
        么就像1一样，对每个字符串分别进行匹配，
        贪心的思想主要体现在尽可能的匹配当前字
        符串（注意是匹配p中的字符串，这是因为p
        中的字符串是以*开头的，如果匹配失败，可
        以用前一个*来匹配s中的当前字符），这样
        尽可能的匹配当前字符串就会给后一个字符
        串留下更多机会匹配后续的字符串
        3、然后再扩展到边界情况（以下有无*都是
        针对p来说），当两边有一边没有*的情况时，
        如果是左边没有*，那么匹配就需要注意，这
        时候第一个字符串的匹配就必须完全匹配的上，
        就不能像之前一样匹配不上就换下一个字符了。
        如果是右边没有*，那么匹配结束时p必须全部
        匹配完成，才算是成功匹配。
    */
    /*public boolean isMatch(String s,String p){
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        int s1 = -1,s2 = 0,p1 = -1,p2 = 0;
        int l1 = sChar.length,l2 = pChar.length;

        //如果结尾不是*，则需要做预处理，先将结尾的字符串
        //匹配上，匹配不上，直接为false
        while (l2 > 0 && pChar[l2 - 1] != '*' && l1 > 0) {
            if (pChar[l2 - 1] == sChar[l1 - 1] || pChar[l2-1] == '?') {
                l2--;
                l1--;
            }else{
                return false;
            }
        }
        if (l2 == 0) {
            return l1 == 0;
        }

        while (s2 < l1 && p2 < l2) {
            if (sChar[s2] == pChar[p2] || pChar[p2] == '?') {
                s2++;
                p2++;
            } else if (pChar[p2] == '*') {
                s1 = s2;
                p1 = p2+1;
                p2++;
            }else{
                if (s1 == -1 || s1+1 == l1) {
                    return false;
                }
                s2 = s1+1;
                s1 = s2;
                p2 = p1;
            }
        }
        //如果s2匹配完了
        if (s2 == l1) {
            for (int i = p2; i < l2; i++) {
                if (pChar[i] != '*') {
                    return false;
                }
            }
        }

        return true;
    }*/


    //为了熟练掌握，再重新写一遍
    public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray(), pChar = p.toCharArray();
        int s1 = -1, s2 = 0, p1 = -1, p2 = 0;
        int l1 = sChar.length, l2 = pChar.length;

        while (l2 > 0 && l1 > 0 && pChar[l2 - 1] != '*') {
            if (sChar[l1 - 1] == pChar[l2 - 1] || pChar[l2 - 1] == '?') {
                l1--;
                l2--;
            } else {
                return false;
            }
        }
        if (l2 == 0) {
            return l1 == 0;
        }

        //开始匹配
        while (p2 < l2 && s2 < l1) {
            if (pChar[p2] == '*') {
                s1 = s2;
                p2++;
                p1 = p2;
            } else if (sChar[s2] == pChar[p2] || pChar[p2] == '?') {
                p2++;
                s2++;
            } else {
                if (s1 == -1 || s1 + 1 == l1) {
                    return false;
                }
                s1++;
                s2 = s1;
                p2 = p1;
            }
        }

        if (s2 == l1) {
            for (int i = p2; i < l2; i++) {
                if (pChar[i] != '*') {
                    return false;
                }
            }
        }

        return true;
    }
}
