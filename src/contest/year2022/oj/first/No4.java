package contest.year2022.oj.first;

import java.util.Scanner;

public class No4 {
    static int[] set;
    static int[][] dp;
    static int res,mod = 998244353;
    static char[][] chars;
    public static void main(String[] args) {
        Scanner scanner =
                new Scanner(System.in);

        int n = scanner.nextInt(),m = scanner.nextInt();
        set = new int[m];
        dp = new int[m][m];

        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = scanner.nextLine();
        }
        for (int i = 0; i < n; i++) {
            chars[i] = strings[i].toCharArray();
        }

        System.out.println(res);
    }

    public static int dfs(int loc,int m){
        if(loc == m)
            return 1;
        if (set[loc] > 0) {
            set[loc]++;
            return dp[loc][m];
        }

        boolean flag = false;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            int newLoc = loc + chars[i].length;
            if (newLoc >= m || set[newLoc] == -1) {
                continue;
            }
            res += dfs(newLoc+1,m);
        }

        if (res > 0) {
            set[loc]++;
            dp[loc][m] = res;
        } else {
            set[loc] = -1;
        }

        return res;
    }
}
