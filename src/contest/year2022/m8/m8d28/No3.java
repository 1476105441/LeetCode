package contest.year2022.m8.m8d28;

public class No3 {
    /**
     *      收集垃圾的最少总时间
     */

    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length,g = 0,p = 0,m = 0,res = 0;

        for (int i = 0; i < n; i++) {
            char[] c = garbage[i].toCharArray();
            for(int j = 0;j < c.length;j++){
                if (c[j] == 'M') {
                    while (m < i) {
                        res += travel[m];
                        m++;
                    }
                    res++;
                } else if (c[j] == 'G') {
                    while (g < i) {
                        res += travel[g];
                        g++;
                    }
                    res++;
                } else if (c[j] == 'P') {
                    while (p < i) {
                        res += travel[p];
                        p++;
                    }
                    res++;
                }
            }
        }

        return res;
    }
}
