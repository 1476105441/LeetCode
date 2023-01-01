package contest.year2022.m8.m8d14;

import java.util.Arrays;

public class No4 {
    /**
     *      统计特殊整数
     */

    //晚上再看这个题目
    //想用数学归纳的方法解决，失败
    /*public int countSpecialNumbers(int n) {
        int b = (int) Math.log10(n),res = 0,y = n;
        int[] dp = new int[b],tmp = new int[10];
        if (b == 0) {
            return n;
        }
        dp[0] = 9;
        for (int i = 1; i < b; i++) {
            int c = 9, j = i - 1, temp = 9;
            while (j >= 0) {
                c *= temp;
                temp--;
                j--;
            }
            dp[i] = dp[i - 1] + c;
        }

        res += dp[b-1];
        //获取最高位计算当前可以有多少种选择
        int pow = (int) Math.pow(10,b),temp = y / pow,c = 0;
        c = temp;
        tmp[c]++;
        y %= pow;
        while (pow > 0) {
            pow /= 10;
            temp = y / pow+1;
            int loc = temp;
            for (int i = temp-1; i >= 0; i--) {
                temp -= tmp[i];
                if (temp <= 0) {
                    return res;
                }
            }
            c *= temp;
            tmp[loc]++;
            y %= pow;
        }
        return res + c;
    }*/

    //解法：数位dp
    //写一下个人对这个题目的理解：
    //dp数组只记录不受限制，并且前面
    //有集合被选择的情况下的组合数
    int[][] dp;
    char[] s;
    public int countSpecialNumbers(int n){
        s = String.valueOf(n).toCharArray();
        dp = new int[s.length][1<<10];
        //初始化dp数组
        for (int i = 0; i < s.length; i++) {
            Arrays.fill(dp[i],-1);
        }
        return recursion(0,0,true,false);
    }
    public int recursion(int i,int mask,boolean isLimit,boolean isNum){
        if(i == s.length)
            return isNum ? 1 : 0;
        if(!isLimit && isNum && dp[i][mask] > 0)
            return dp[i][mask];
        int res = 0;
        //如果前一个不是数字，则优先跳过当前，进入下一个，这是为了从“底部”开始统计
        if(!isNum)
            res = recursion(i+1,mask,false,false);
        //如果前面是数字，则当前数字可以从0开始选择，如果不是数字，则不能选择0。
        //如果受到限制，则当前所选择的数字只能选到最大的数值，如果不受限制，则可以在0-9内任选。
        int b = isNum ? 0 : 1,up = isLimit ? s[i]-'0' : 9;
        for (;b <= up;b++){
            if (((mask >> b) & 1) == 0) {
                res += recursion(i+1,mask | (1 << b),isLimit && b == up,true);
            }
        }
        if(!isLimit && isNum)
            dp[i][mask] = res;
        return res;
    }
}
