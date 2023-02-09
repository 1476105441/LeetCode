package contest.year2023.m2.d4;

import java.util.HashSet;
import java.util.Set;

public class No2 {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set = new HashSet<>();
        int len = banned.length;
        for (int i = 0; i < len; i++) {
            set.add(banned[i]);
        }
        int sum = 0,res = 0;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                sum += i;
                if (sum > maxSum) {
                    break;
                }
                res++;
            }
        }
        return res;
    }
}
