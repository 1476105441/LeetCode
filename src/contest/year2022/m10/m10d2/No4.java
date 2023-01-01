package contest.year2022.m10.m10d2;

public class No4 {
    /**
     *      对字母串可执行的最大删除数
     */

    //失败
    /*public int deleteString(String s) {
        char[] c = s.toCharArray();
        int n = c.length-1,l = 0,r = 0,res = 1,m = l+((n-l)>>1);
        while (r < m) {
            int size = r-l+1;
            if(examine(c,l,r+1,size)){
                res++;
                l = r+1;
                m = l+((n-l)>>1);
            }
            r++;
        }
        return res;
    }
    public boolean examine(char[] c,int l1,int l2,int size){
        for (int i = 0; i < size; i++) {
            if(c[l1] != c[l2])
                return false;
            l1++;
            l2++;
        }
        return true;
    }*/

    //超时
    /*public int deleteString(String s) {
        char[] c = s.toCharArray();
        int n = c.length,res = 1;
        int[] dp = new int[n];

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            int m = i+((n-i)>>1);
            for (int j = i; j < m; j++) {
                int size = j-i+1;
                if(examine(c,i,j+1,size)){
                    flag = true;
                    dp[j+1] = dp[i]+1;
                }
            }
            if(!flag)
                break;
            res = Math.max(res,dp[i]+1);
        }

        return res;
    }
    public boolean examine(char[] c,int l1,int l2,int size){
        for (int i = 0; i < size; i++) {
            if(c[l1] != c[l2])
                return false;
            l1++;
            l2++;
        }
        return true;
    }*/


    //加了特判，过了，不过肯定会rejudge
    //还是有错误的，能过只是因为测试用例不完整
    /*public int deleteString(String s) {
        char[] c = s.toCharArray();
        int n = c.length,res = 1;
        boolean flag = true;
        char pre = c[0];

        for (int i = 1; i < n; i++) {
            if (c[i] != pre) {
                flag = false;
                break;
            }
        }
        if(flag)
            return n;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int m = i+((n-i)>>1);
            for (int j = i; j < m; j++) {
                int size = j-i+1;
                if(examine(c,i,j+1,size)){
                    flag = true;
                    dp[j+1] = dp[i]+1;
                }
            }
            if(!flag)
                break;
            res = Math.max(res,dp[i]+1);
        }

        return res;
    }
    public boolean examine(char[] c,int l1,int l2,int size){
        for (int i = 0; i < size; i++) {
            if(c[l1] != c[l2])
                return false;
            l1++;
            l2++;
        }
        return true;
    }*/


    //dp,怎么状态转移？
    //成功归成功了，但是187ms，慢了很多
    //再次测试后发现并没有慢很多，130-180ms之间
    /*public int deleteString(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[][] dp = new int[n+1][n+1];
        int[] temp = new int[n];

        //所有字符相同的情况特殊判断
        char pre = c[0];
        boolean flag = true;
        for (int i = 1; i < n; i++) {
            if (c[i] != pre) {
                flag = false;
                break;
            }
        }
        if(flag)
            return n;

        //计算前缀的状态转移，dp[i][j]代表以i开始的前缀
        //和以j开始的前缀相等的长度
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i+1][j+1]+1;
                }
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; i + 2*j <= n; j++) {
                if(dp[i][i+j] >= j)
                    temp[i] = Math.max(temp[i],temp[i+j]);
            }
            temp[i]++;
        }

        return temp[0];
    }*/


    //和上面的解法没什么太大的差距，只是概率问题导致时慢时快，130-180ms
    /*public int deleteString(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[][] dp = new int[n+1][n+1];
        int[] temp = new int[n];

        //所有字符相同的情况特殊判断

        if(examine(c))
            return n;

        //计算前缀的状态转移，dp[i][j]代表以i开始的前缀
        //和以j开始的前缀相等的长度
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i+1][j+1]+1;
                }
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; i + 2*j <= n; j++) {
                if(dp[i][i+j] >= j)
                    temp[i] = Math.max(temp[i],temp[i+j]);
            }
            temp[i]++;
        }

        return temp[0];
    }
    private boolean examine(char[] c){
        for (int i = 1; i < c.length; i++) {
            if (c[i] != c[0]) {
                return false;
            }
        }
        return true;
    }*/

}
