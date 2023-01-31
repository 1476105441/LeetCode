package algorithm.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No22 {
    //括号生成

    //解法一：回溯+hashSet去重
    /*Set<String> set;
    int n;
    public List<String> generateParenthesis(int n) {
        set = new HashSet<>();
        this.n = n;
        List<String> res = new ArrayList<>();
        build(res,0,0,new StringBuilder());
        return res;
    }

    private void build(List<String> list,int l,int r,StringBuilder sb){
        if (l == n && r == n) {
            if (set.add(sb.toString())) {
                list.add(sb.toString());
            }
            return;
        }
        if (l < n) {
            sb.append('(');
            build(list,l+1,r,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (r < l) {
            sb.append(')');
            build(list,l,r+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/

    //解法二：仅回溯，不需要使用hashSet去重，0ms
    /*int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        List<String> res = new ArrayList<>();
        build(res,0,0,new StringBuilder());
        return res;
    }

    private void build(List<String> list,int l,int r,StringBuilder sb){
        if (l == n && r == n) {
            list.add(sb.toString());
            return;
        }
        if (l < n) {
            sb.append('(');
            build(list,l+1,r,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (r < l) {
            sb.append(')');
            build(list,l,r+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/

    //解法二：动态规划
    //主要思想是：以括号对为变量进行状态转移
    //dp数组中存储的是当前括号对数能够形成所有字符串。
    //对于第n对括号来说，它能形成多少个字符串取决于它之前的括号对。
    //所有之前的括号对只有可能出现在当前括号对的中间或者右边，那么
    //就变成了一个选择问题
    public List<String> generateParenthesis(int n) {
        List<String>[] dp = new List[n+1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[0].add("");
        dp[1].add("()");
        for (int i = 2; i <= n; i++) {
            int p = 0,q = i - 1 - p;
            while (p < i) {
                for (String ps : dp[p]) {
                    for (String qs : dp[q]) {
                        dp[i].add("(" + ps + ")" + qs);
                    }
                }
                p++;
                q--;
            }
        }
        return dp[n];
    }
}
