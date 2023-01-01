package contest.year2022.m7.m7d31;

public class No4 {
    /**
     *      图中的最长环
     */

    //最初版本，失败
    /*public int longestCycle(int[] edges) {
        boolean[] flag = new boolean[edges.length];
        int res = -1;

        for (int i = 0; i < edges.length; i++) {
            if (!flag[i]) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                flag[i] = true;
                int j = edges[i],c = 0;
                while (j != -1) {
                    if (!set.add(j)) {
                        res = Math.max(res,c);
                        break;
                    }
                    c++;
                    flag[j] = true;
                    j = edges[j];
                }
            }
        }

        return res;
    }*/

    //可以通过用例，但是超时
    /*public int longestCycle(int[] edges) {
        boolean[] flag = new boolean[edges.length];
        int res = -1;

        for (int i = 0; i < edges.length; i++) {
            if (!flag[i]) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                flag[i] = true;
                int j = edges[i],c;
                while (j != -1) {
                    if (!set.add(j)) {
                        c = 1;
                        int t = edges[j];
                        while (t != j) {
                            c++;
                            t = edges[t];
                        }
                        res = Math.max(res,c);
                        break;
                    }
                    flag[j] = true;
                    j = edges[j];
                }
            }
        }

        return res;
    }*/

    //使用类似深搜+记忆化，68ms
    /*public int longestCycle(int[] edges) {
        int[] flag = new int[edges.length];
        int res = -1;

        for (int i = 0; i < edges.length; i++) {
            if (flag[i] == 0) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                flag[i] = 1;
                int j = edges[i],c;
                while (j != -1 && flag[j] != 2) {
                    if (!set.add(j)) {
                        c = 1;
                        flag[j] = 2;
                        int t = edges[j];
                        while (t != j) {
                            flag[t] = 2;
                            c++;
                            t = edges[t];
                        }
                        res = Math.max(res,c);
                        break;
                    }
                    flag[j] = 1;
                    j = edges[j];
                }
            }
        }

        return res;
    }*/

    //巨佬的做法，使用时间戳
    public int longestCycle(int[] edges)  {
        int n = edges.length,clock = 1,res = -1;
        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            if (time[i] == 0) {
                int start = clock,k;
                for (k = i;k >= 0; k = edges[k]) {
                    if (time[k] > 0) {
                        if(time[k] >= start)
                            res = Math.max(res,clock-time[k]);
                        break;
                    }
                    time[k] = clock++;
                }
            }
        }

        return res;
    }
}
