package contest.year2023.m7.d2;

import java.util.HashMap;
import java.util.Map;

/**
 * 2762. 不间断子数组
 *
 * @author wjs 2023/7/4
 */
public class No3 {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int min = nums[0], max = nums[0];
        map.put(nums[0],1);
        res = 1;
        int l=0, r=1;
        while(r < n) {
            int v1 = Math.abs(nums[r]-min), v2 = Math.abs(nums[r]-max);
            if(v1 > 2 && v2 > 2) {
                map.clear();
                l = r;
                res++;
                min = nums[r];
                max = nums[r];
                map.put(nums[r],1);
            } else if(v1 > 2 || v2 > 2) {
                while(l < r && (v1 > 2 || v2 > 2)) {
                    map.put(nums[l],map.get(nums[l]) - 1);
                    if(map.getOrDefault(min,0) == 0) {
                        min = map.get(min+1) == 0 ? max : min+1;
                    }
                    if(map.getOrDefault(max,0) == 0) {
                        max = map.get(max-1) == 0? min : max-1;
                    }
                    v1 = Math.abs(nums[r]-min); v2 = Math.abs(nums[r]-max);
                    l++;
                }
                if(l == r) {
                    min = nums[r];
                    max = nums[r];
                    res++;
                    map.put(nums[r],1);
                } else {
                    max = Math.max(max,nums[r]);
                    min = Math.min(min,nums[r]);
                    map.put(nums[r],map.getOrDefault(nums[r],0) + 1);
                    res += r-l+1;
                }
            } else {
                max = Math.max(max,nums[r]);
                min = Math.min(min,nums[r]);
                map.put(nums[r],map.getOrDefault(nums[r],0) + 1);
                res += r-l+1;
            }
            r++;
        }
        return res;
    }
}
