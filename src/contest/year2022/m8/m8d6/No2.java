package contest.year2022.m8.m8d6;

import java.util.*;

public class No2 {
    /**
     *      统计坏数对的数目
     */

    //j = 5 , i = 2
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long res = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int tmp = i - nums[i];
            Integer v = map.get(tmp);
            if (v == null) {
                v = 0;
                res += i;
            }else
                res += i-v;
            map.put(tmp,v+1);
        }

        return res;
    }
}
