package contest.year2022.m10.m10d15;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    public int[] productQueries(int n, int[][] queries) {
        int temp = n,m = queries.length,MOD = 1000000007;
        int[] res = new int[m];
        List<Integer> list = new ArrayList<>();
        while (temp > 0) {
            int log2 =  1 << (int)(Math.log(temp)/Math.log(2));
            list.add(0,log2);
            temp -= log2;
        }

        for (int i = 0; i < m; i++) {
            long ans = 1;
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                ans = (ans * list.get(j)) % MOD;
            }
            res[i] = (int) ans;
        }

        return res;
    }
}
