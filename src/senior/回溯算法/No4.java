package senior.回溯算法;

public class No4 {
    /**
     *          通配符匹配
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     *     s 可能为空，且只包含从 a-z 的小写字母。
     *     p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     */

    /**
     * 想法：挨个匹配当前字符，有两种情况
     * 1是当前字符是普通字符或者?,一次匹
     * 配s中的一个字符
     * 2是当前字符是*,可以匹配任意个字符
     * 当前是*时有两种情况：若后面没有别
     * 的字符，则直接匹配成功，若后面还有
     * 别的正常字符，则先匹配到为正常字符
     * 的位置挨个试
     */

    //改来改去还超时了，真垃圾
    /*char[] sChar,pChar;
    public boolean isMatch(String s, String p) {
        sChar = s.toCharArray();
        pChar = p.toCharArray();
        if ("".equals(s)) {
            return "".equals(p) || isValid(pChar,0);
        }
        int sp = 0,pp = 0;

        return dfs(0,0);
    }

    public boolean dfs(int sp,int pp){
        if (sp == sChar.length && pp == pChar.length) {
            return true;
        } else if (sp == sChar.length) {
            return isValid(pChar,pp);
        } else if (pp == pChar.length) {
            return false;
        }

        if (pChar[pp] == '*') {
            if (pp + 1 == pChar.length) {
                return true;
            }
            //如果后面接着的就是通配符，则可以当作一个通配符来使用
            while (pp < pChar.length-1 && pChar[pp + 1] == '*') {
                pp++;
            }
            //后面全都是通配符
            if (pp == pChar.length-1) {
                return true;
            }

            pp++;
            for (int i = sp; i < sChar.length; i++) {
                if (sChar[i] == pChar[pp] || pChar[pp] =='?') {
                    if(dfs(i+1,pp+1)){
                        return true;
                    }
                }
            }
            return false;
        } else if (pChar[pp] == '?') {
            return dfs(sp+1,pp+1);
        }else{
            if (sChar[sp] != pChar[pp]) {
                return false;
            }else
                return dfs(sp+1,pp+1);
        }
    }

    public boolean isValid(char[] p,int begin){
        for (int i = begin; i < p.length; i++) {
            if (p[i] != '*') {
                return false;
            }
        }
        return true;
    }*/

    /**
     * 动态规划解法
     * dp[i][j]表示字符串的前i个能够匹配
     * 模式的前j个（包括i和j）
     */
    /*public boolean isMatch(String s, String p){
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        boolean[][] dp = new boolean[sChar.length+1][pChar.length+1];

        dp[0][0] = true;
        for (int i = 1; i < pChar.length+1; i++) {
            if (pChar[i-1] == '*') {
                dp[0][i] = true;
            }else
                break;
        }

        for (int i = 1; i < sChar.length + 1; i++) {
            for (int j = 1; j < pChar.length + 1; j++) {
                if (pChar[j-1] == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                } else if (pChar[j-1] == '?' || pChar[j-1] == sChar[i-1]) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[sChar.length][pChar.length];
    }*/

    /**
     * 贪心解法
     * 这是一种归纳的思想，先将匹配的模式看
     * 做固定的情况，如：*uuu*，其中u代指
     * 其他字符，这样的话，只需要在s中匹配
     * uuu就可以了，匹配成功就是成功的，匹
     * 配失败就是失败的，但是有特殊情况需要
     * 处理，就是开头和结尾不是*时
     */

    /*public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray(), pChar = p.toCharArray();
        int pIndex = 0, sIndex = 0, pRecord = -1, sRecord = -1, length1 = sChar.length, length2 = pChar.length;

        //结尾不是*，要做特殊处理
        while (length1 > 0 && length2 > 0 && pChar[length2 - 1] != '*') {
            if (pChar[length2 - 1] != sChar[length1 - 1] && pChar[length2 - 1] != '?') {
                return false;
            }
            length1--;
            length2--;
        }

        if (length2 == 0) {
            return length1 == 0;
        }

        while (pIndex < length2 && sIndex < length1) {
            if (pChar[pIndex] == '*') {
                pIndex++;
                pRecord = pIndex;
                sRecord = sIndex;
            } else if (pChar[pIndex] == sChar[sIndex] || pChar[pIndex] == '?') {
                pIndex++;
                sIndex++;
            } else if (sRecord != -1 && sRecord + 1 < length1) {
                sRecord++;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(pChar, pIndex, length2);
    }*/

    /*public boolean allStars(char[] str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str[i] != '*') {
                return false;
            }
        }
        return true;
    }

    //按照贪心的思路再写一遍
    public boolean isMatch(String s, String p){
        char[] sChar = s.toCharArray(),pChar = p.toCharArray();
        int sLength = sChar.length,pLength = pChar.length;

        //先处理末尾情况
        while (sLength > 0 && pLength > 0 && pChar[pLength-1] != '*') {
            //末尾不是*通配符而且匹配不上，直接返回false
            if (pChar[pLength-1] != sChar[sLength-1] && pChar[pLength-1] != '?') {
                return false;
            }
            pLength--;
            sLength--;
        }
        //如果p匹配完了，不用再往后进行判断了，直接返回一个判断结果
        if (pLength == 0) {
            return sLength == 0;
        }

        //后面两个置为-1是因为要记录pChar首位不是*且匹配不上的情况
        int pIndex = 0,sIndex = 0,pRecord = -1,sRecord = -1;

        while (pIndex < pLength && sIndex < sLength) {
            if (pChar[pIndex] == '*') {
                pIndex++;
                //模式的当前字符为*，则重新记录起始位置
                pRecord = pIndex;
                sRecord = sIndex;
            } else if (pChar[pIndex] == '?' || pChar[pIndex] == sChar[sIndex]) {
                //匹配上了，直接进入下一个匹配
                pIndex++;
                sIndex++;
            } else if (pRecord != -1 && sRecord < sLength - 1) {
                sRecord++;
                sIndex = sRecord;
                pIndex = pRecord;
            }else {
                return false;
            }
        }

        return allStars(pChar,pIndex,pLength);
    }*/


    //重新写一遍
}
