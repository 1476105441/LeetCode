package contest.year2022.m9.m9d11;

public class No2 {
    /**
     *      子字符串的最优划分
     */

    public int partitionString(String s) {
        char[] c = s.toCharArray();
        int n = c.length,res = 1;
        int[] set = new int[26];

        for (int i = 0; i < n; i++) {
            int loc = c[i] - 'a';
            if (set[loc] != 0) {
                set = new int[26];
                res++;
            }
            set[loc] = 1;
        }

        return res;
    }
}
