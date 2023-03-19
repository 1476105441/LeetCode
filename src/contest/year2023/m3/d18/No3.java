package contest.year2023.m3.d18;

import java.util.Arrays;

public class No3 {
    public long findScore(int[] nums) {
        int n = nums.length;
        long res = 0;
        boolean[] set = new boolean[n];
        Integer[] loc = new Integer[n];
        for(int i=0;i < n;i++){
            loc[i] = i;
        }
        Arrays.sort(loc,(x, y) -> {
            if(nums[x] != nums[y]) return nums[x]-nums[y];
            return x-y;
        });
        for(int i=0;i < n;i++){
            int index = loc[i];
            if(set[index]) continue;
            set[index] = true;
            res += nums[index];
            if(index > 0 && !set[index-1]) set[index-1] = true;
            if(index < n-1 && !set[index+1]) set[index+1] = true;
        }
        long t =1;

        return res;
    }
}
