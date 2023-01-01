package contest.year2022.m7.m7d3;

public class Rewrite {
    //t1
    /*public String decodeMessage(String key, String message) {
        char[] k = key.toCharArray(),msg = message.toCharArray();
        int n = k.length,m = msg.length;
        char c = 'a';
        char[] temp = new char[26],set = new char[26];
        for (int i = 0; i < n; i++) {
            if(k[i] == ' ')
                continue;
            int loc = k[i]-'a';
            if (set[loc] == 0) {
                set[loc] = 1;
                temp[loc] = c++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (msg[i] == ' ')
                continue;
            int loc = msg[i] - 'a';
            msg[i] = temp[loc];
        }
        return new String(msg);
    }*/


    //t2
    /*public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        int up = 0,down = m-1,left = 0,right = n-1,c = 0,total = m*n;
        int i = 0,j = 0;

        while (c < total) {
            //往右走
            while (j <= right && c < total) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                } else
                    res[i][j] = -1;
                j++;
                c++;
            }
            j--;
            i++;
            up++;
            //往下走
            while (i <= down && c < total) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                } else
                    res[i][j] = -1;
                i++;
                c++;
            }
            i--;
            j--;
            right--;
            //往左走
            while (j >= left && c < total) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                } else
                    res[i][j] = -1;
                j--;
                c++;
            }
            i--;
            j++;
            down--;
            //往上走
            while (i >= up && c < total) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                } else
                    res[i][j] = -1;
                i--;
                c++;
            }
            left++;
            i++;
            j++;
        }

        return res;
    }*/


    //t3
    /*public int peopleAwareOfSecret(int n, int delay, int forget) {
        //表示第i-1天新增的人数
        int[] dp = new int[n];
        int mod = 1000000007;
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int c = 0;
            if (i - delay >= 0) {
                int limit = i-delay;
                for (int j = Math.max(0,i-forget+1); j <= limit; j++) {
                    c = (c+dp[j]) % mod;
                }
            }
            dp[i] = c;
        }

        int res = 0;
        for (int i = n-forget; i < n; i++) {
            res = (res + dp[i]) % mod;
        }
        return res;
    }*/


    //t4
    int m,n,mod,res;
    int[][] count,grid;
    public int countPaths(int[][] grid) {
        mod = 1000000007;
        n = grid[0].length;
        m = grid.length;
        res = 0;
        count = new int[m][n];
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i,j);
            }
        }
        return res;
    }
    public int dfs(int i,int j){
        if(count[i][j] > 0)
            return count[i][j];
        count[i][j] = 1;
        //往四个方向开始寻找
        if (i > 0 && grid[i][j] > grid[i-1][j]) {
            count[i][j] = (count[i][j] + dfs(i-1,j)) % mod;
        }
        if (j > 0 && grid[i][j] > grid[i][j-1]) {
            count[i][j] = (count[i][j] + dfs(i,j-1)) % mod;
        }
        if (i < m - 1 && grid[i][j] > grid[i+1][j]) {
            count[i][j] = (count[i][j] + dfs(i+1,j)) % mod;
        }
        if (j < n - 1 && grid[i][j] > grid[i][j+1]) {
            count[i][j] = (count[i][j] + dfs(i,j+1)) % mod;
        }

        res = (res + count[i][j]) % mod;
        return count[i][j];
    }
}
