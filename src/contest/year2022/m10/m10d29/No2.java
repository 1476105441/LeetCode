package contest.year2022.m10.m10d29;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int m = queries.length,n = dictionary.length,len;
        List<String> res = new ArrayList<>();
        char[][] q = new char[m][],d = new char[n][];

        for (int i = 0; i < m; i++) {
            q[i] = queries[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            d[i] = dictionary[i].toCharArray();
        }
        len = q[0].length;

        for (int i = 0; i < m; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                int c = 0;
                for (int k = 0; k < len; k++) {
                    if(c > 2)
                        break;
                    if(q[i][k] != d[j][k])
                        c++;
                }
                if (c <= 2) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                res.add(queries[i]);
        }

        return res;
    }
}
