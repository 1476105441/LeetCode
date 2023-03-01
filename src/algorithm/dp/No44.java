package algorithm.dp;

public class No44 {
    //通配符匹配
    //动态规划，9ms
    /*public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray(),ps = p.toCharArray();
        int m = cs.length,n = ps.length;
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 1;
        for(int i = 1;i <= n;i++){
            if(ps[i-1] == '*'){
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for(int i = 1;i <= m;i++){
            for(int j = 1;j <= n;j++){
                if(ps[j-1] == cs[i-1] || ps[j-1] == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(ps[j-1] == '*'){
                    dp[i][j] = dp[i][j-1] | dp[i-1][j];
                }
            }
        }
        return dp[m][n] == 1;
    }*/

    //写算法的时候，需要考虑极端情况
    //处理了之后进行字符串匹配，失败
    /*public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray(),ps = p.toCharArray();
        int m = cs.length,n = ps.length;
        int s1 = -1,s2 = 0,l1 = 0,l2 = 0;
        boolean flag = true;
        for(int i = 0;i < n;i++){
            if(ps[i] != '*'){
                flag = false;
            }
        }
        if(flag){
            return true;
        }
        if(ps[0] == '*'){
            s1 = 0;
        }
        while(s1 < m && l1 < m){
            if(ps[l2] == '*'){
                while(l2 < n && ps[l2] == '*'){
                    l2++;
                }
                s2 = l2;
            } else if(ps[l2] == cs[l1] || ps[l2] == '?'){
                l1++;
                l2++;
            } else {
                if(s1 == -1){
                    return false;
                }
                s1++;
                l1 = s1;
                l2 = s2;
            }
            if(l2 == n){
                return ps[n-1] == '*' ? true : l1 == m;
            }
        }
        return false;
    }*/

    //将p看作是一个模板，然后进行匹配
    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray(),pc = p.toCharArray();
        int m = sc.length,n = pc.length;
        //先将最后不为*的字符串处理了，以达成统一看作最后带*号的字符串来处理的目的
        while(m > 0 && n > 0 && pc[n-1] != '*'){
            if(sc[m-1] != pc[n-1] && pc[n-1] != '?')
                return false;
            m--;
            n--;
        }
        //如果p匹配完了，说明p的前面没有*，返回结果为s匹配完成没有
        if(n == 0){
            return m == 0;
        }

        //sr和pr是两个字符串匹配失败后的起点
        int sr = -1,pr = -1,si = 0,pi = 0;
        while(si < m && pi < n){
            if(pc[pi] == '*'){
                //匹配到这里，说明前面的字符串已经匹配完成了，s的起点更新为当前的位置
                sr = si;
                pr = pi+1;
                pi++;
            }else if(sc[si] == pc[pi] || pc[pi] == '?'){
                si++;
                pi++;
            }else{
                //如果第一个就失败了或者起始位置已经用完了，整个就算匹配失败了
                if(sr == -1 || sr+1 == m){
                    return false;
                }
                sr++;
                si = sr;
                pi = pr;
            }
        }
        //结尾处理
        while(pi < n){
            if(pc[pi] != '*'){
                return false;
            }
            pi++;
        }
        return true;
    }
}
