package contest.year2022.m12.d18;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No1 {
    static int[][] dp;
    static {
        dp = new int[101][3];
        dp[0][0] = 1;
        for (int i = 1; i < 101; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < 3; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
    }
    public int similarPairs(String[] words) {
        int n = words.length,res = 0;
        char[][] chars = new char[n][];
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            chars[i] = words[i].toCharArray();
            int[] set = new int[26];
            for (int j = 0; j < chars[i].length; j++) {
                set[chars[i][j] - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if(set[j] > 0)
                    sb.append(1);
                else
                    sb.append(0);
            }
            map.put(sb.toString(),map.getOrDefault(sb.toString(),0)+1);
        }

        Set<String> set = map.keySet();
        for (String key : set) {
            res += dp[map.get(key)][2];
        }
        return res;
    }
}
