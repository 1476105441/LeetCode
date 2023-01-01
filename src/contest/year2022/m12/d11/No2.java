package contest.year2022.m12.d11;

import java.util.HashMap;
import java.util.Map;

public class No2 {
    /*public int longestSquareStreak(int[] nums) {
        int n = nums.length,res = -1;
        int[] bucket = new int[100001];
        Map<Integer,Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            bucket[nums[i]]++;
        }
        for (int i = 0; i < n; i++) {
            int x = (int) Math.sqrt(nums[i]), y = nums[i] * nums[i];
            Integer xp = dp.get(x),yp = dp.get(y);
            if (nums[i] % x == 0 && xp != null) {
                dp.put(nums[i],xp-1);
            } else if (yp != null) {
                dp.put(nums[i],yp+1);
                res = Math.max(res,yp+1);
            } else{
                int c = 1,now;
                while (y < 100001 && bucket[y] > 0) {
                    now = y;
                    y = now * now;
                    c++;
                }
                dp.put(nums[i],c);
                if(c != 1)
                    res = Math.max(res,c);
            }
        }
        return res;
    }*/
    public int longestSquareStreak(int[] nums) {
        int n = nums.length,res = -1;
        int[] bucket = new int[100001];
        Map<Integer,Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            bucket[nums[i]]++;
        }
        for (int i = 0; i < n; i++) {
            int x = (int) Math.sqrt(nums[i]);
            long y = (long)nums[i] * nums[i];
            Integer xp = dp.get(x),yp = dp.get(y);
            if (nums[i] % x == 0 && xp != null) {
                dp.put(nums[i],xp-1);
            } else if (yp != null) {
                dp.put(nums[i],yp+1);
                res = Math.max(res,yp+1);
            } else{
                int c = 1;
                long now;
                while (y > 0 && y < 100001 && bucket[(int) y] > 0) {
                    now = y;
                    y = now * now;
                    c++;
                }
                dp.put(nums[i],c);
                if(c != 1)
                    res = Math.max(res,c);
            }
        }
        return res;
    }
}
