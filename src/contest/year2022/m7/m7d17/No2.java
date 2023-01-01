package contest.year2022.m7.m7d17;

import java.util.HashMap;
import java.util.Map;

public class No2 {
    public int maximumSum(int[] nums) {
        int n = nums.length,res = -1;
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int count = count(nums[i]);
            Integer temp = map.get(count);
            if(temp == null)
                map.put(count,nums[i]);
            else {
                res = Math.max(res,nums[i] + temp);
                map.put(count,Math.max(temp,nums[i]));
            }
        }

        return res;
    }
    public int count(int val){
        int res = 0;
        while (val > 0) {
            res += val % 10;
            val /= 10;
        }
        return res;
    }
}
