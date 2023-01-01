package contest.year2022.m11.d26;

public class No4 {
    //想用排列组合的方式解决此问题，失败，考虑的太简单了
    /*long[][] dp = new long[10001][3];
    int mod = 1000000007;
    {
        dp[0][0] = 1;
        for (int i = 1; i < 10001; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= 2; j++) {
                dp[i][j] = (dp[i-1][j]+dp[i-1][j-1])%mod;
            }
        }
    }
    public int countPalindromes(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        long res = 0;
        long[] set1 = new long[10],set2 = new long[10];

        for (int i = 0; i < n; i++) {
            int loc = chars[i]-'0';
            set2[loc]++;
        }
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>(10);
            int loc = chars[i]-'0';
            set2[loc]--;
            for (int j = 0; j < 10; j++) {
                if (set1[j] > 0 && set2[j] > 0) {
                    temp.add(j);
                }
            }
            int size = temp.size();
            for (int j = 0; j < size; j++) {
                if (set1[temp.get(j)] >= 2 && set2[temp.get(j)] >= 2) {
                    res = res + dp[(int) set1[temp.get(j)]][2] * dp[(int) set2[temp.get(j)]][2] % mod;
                    res = res % mod;
                }
                for (int k = j+1; k < size; k++) {
                    res =( res + (set1[temp.get(j)] * set1[temp.get(k)] % mod) * (set2[temp.get(j)] * set2[temp.get(k)] % mod) ) % mod;
                }
            }
            set1[loc]++;
        }

        return (int) res;
    }*/

    //动态规划？怎么状态转移呢？
    //看了题解之后发现和我第一个想法类似，就是先预处理统计了数对的出现情况
    public int countPalindromes(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length,mod = 1000000007;
        long res = 0;
        long[][] suf = new long[10][10],pre = new long[10][10];
        int[] c1 = new int[10],c2 = new int[10];

        for (int i = n-1; i > -1; i--) {
            int loc = chars[i] - '0';
            for (int j = 0; j < 10; j++) {
                //加上当前后面数字j的个数，因为有几个数字j，就能构成几个 loc-j 数对
                suf[loc][j] += c1[j];
            }
            c1[loc]++;
        }

        for (int i = 0; i < n; i++) {
            int loc = chars[i] - '0';
            //遍历到当前数字时，要去掉当前数字对后缀的影响
            c1[loc]--;
            for (int j = 0; j < 10; j++) {
                suf[loc][j] -= c1[j];
            }

            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if(pre[j][k] > 0 && suf[k][j] > 0)
                        res = (res + (pre[j][k] * suf[k][j]) % mod) % mod;
                }
            }

            //添加结果结束后，将当前数字添加进前缀中
            for (int j = 0; j < 10; j++) {
                pre[j][loc] += c2[j];
            }
            c2[loc]++;
        }

        return (int) res;
    }
}
