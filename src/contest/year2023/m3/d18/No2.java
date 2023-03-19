package contest.year2023.m3.d18;

import java.util.Arrays;

public class No2 {
    public int maximizeGreatness(int[] nums) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);
        int l = 0,r = 0;
        while(r < n){
            while(r < n && nums[r] <= nums[l]){
                r++;
            }
            if(r == n) break;
            res++;
            l++;
            r++;
        }
        return res;
    }
}
