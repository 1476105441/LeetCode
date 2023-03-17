package contest.year2023.m2.d19;

public class No4 {
    //题目要求字典序最小，那么从第一个开始构造的字符逐渐增大，可以
    //使字典序最小
    /*public String findTheString(int[][] lcp) {
        int m = lcp.length,n = lcp[0].length;
        char[] chars = new char[n];
        char c = 'a';
        //构造
        for (int i = 0; i < n; i++) {
            if (chars[i] == 0) {
                if (c > 'z') {
                    return "";
                }
                chars[i] = c;
                for (int j = i+1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        chars[j] = c;
                    }
                }
                c++;
            }
        }
        int[][] dp = new int[n+1][n+1];
        //验证
        for (int i = n-1; i > -1; i--) {
            for (int j = n-1; j > -1; j--) {
                int cnt = 0;
                if (chars[i] == chars[j]) {
                    cnt = dp[i+1][j+1] + 1;
                }
                if (cnt != lcp[i][j]) {
                    return "";
                }
                dp[i][j] = cnt;
            }
        }
        return new String(chars);
    }*/

    //思维题
    //先构造再检验？只要lcp[i][j]>0，说明i一定等于j
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] value = new char[n];
        char c = 'a';
        for(int i = 0;i < n;i++){
            if(value[i] == 0){
                value[i] = c;
                if(c > 'z'){
                    return "";
                }
                c++;
            }
            char temp = value[i];
            for(int j = i+1;j < n;j++){
                if(lcp[i][j] > 0){
                    if(value[j] != 0 && value[j] != temp){
                        return "";
                    }
                    value[j] = temp;
                }
            }
        }
        int[][] dp = new int[n+1][n+1];
        for(int i = n-1;i > -1;i--){
            for(int j = n-1;j > -1;j--){
                int temp = 0;
                if(value[i] == value[j]){
                    temp = dp[i+1][j+1] + 1;
                }
                dp[i][j] = temp;
                if(dp[i][j] != lcp[i][j]){
                    //System.out.println(dp[i][j]+" + "+lcp[i][j]);
                    return "";
                }
            }
        }
        return new String(value);
    }
}
