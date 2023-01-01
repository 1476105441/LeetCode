package contest.year2022.m12.d18;

import java.util.HashMap;
import java.util.Map;

public class No4 {
    //计算出两个点之间的数量就可以了
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] res = new int[m];

        for (int i = 0; i < m; i++) {
            int x = queries[i][0],y = queries[i][1],xc = 0,yc = 0,temp = 0;
            Map<Integer,Integer> map = new HashMap<>();
            while (x >= 1) {
                map.put(x,xc);
                xc++;
                x = x >> 1;
            }
            while (y >= 1) {
                Integer val = map.get(y);
                if (val != null) {
                    temp = val + yc;
                    break;
                }
                yc++;
                y = y >> 1;
            }
            res[i] = temp + 1;
        }

        return res;
    }
}
