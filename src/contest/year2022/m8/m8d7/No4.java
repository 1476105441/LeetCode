package contest.year2022.m8.m8d7;

public class No4 {
    /**
     *      最长理想子序列
     */

    //思考方向出错了，当然就想不出来了
    //以每个元素为考虑点，计算以当前元素结尾的最长子序列长度
    /*public int longestIdealString(String s, int k) {
        char[] sc = s.toCharArray();
        int n = sc.length,res = 0;
        int[] map = new int[26];

        for (int i = 0; i < n; i++) {
            int loc = sc[i] - 'a',max = 0;
            for (int j = loc; j < 26 && j <= loc + k; j++) {
                if (map[j] > max) {
                    max = map[j];
                }
            }
            for (int j = loc-1; j >= 0 && j >= loc - k; j--) {
                if (map[j] > max) {
                    max = map[j];
                }
            }
            map[loc] = max+1;
            if (max + 1 > res) {
                res = max+1;
            }
        }

        return res;
    }*/

    public int longestIdealString(String s, int k) {
        char[] sc = s.toCharArray();
        int n = sc.length,res = 0;
        int[] map = new int[26];

        for (int i = 0; i < n; i++) {
            int loc = sc[i] - 'a',max = 0;
            for (int j = Math.max(loc - k,0); j < 26 && j <= loc + k; j++) {
                if (map[j] > max) {
                    max = map[j];
                }
            }

            map[loc] = max+1;
            if (max + 1 > res) {
                res = max+1;
            }
        }

        return res;
    }
}
