package contest.year2023.m2.d26;

import java.util.Arrays;

public class No3 {
    //失败
    /*private boolean[] set;
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length,res = 0;
        Arrays.sort(nums);
        set = new boolean[n];
        for(int i = 0;i < n;i++){
            if(set[i])
                break;
            int loc = find(nums,2 * nums[i]);
            if(loc == -1){
                break;
            }
            int j = i + 1;
            while(j < n && nums[j] == nums[i]){
                j++;
            }
            int c = j - i;
            i = j-1;
            while(c > 0 && loc < n){
                set[loc] = true;
                loc++;
                c--;
                res++;
            }
        }
        return res << 1;
    }
    private int find(int[] nums,int val){
        int l = 0,r = nums.length-1,res = -1;
        while(l <= r){
            int c = l + ((r-l) >> 1);
            if(nums[c] >= val){
                if(!set[c]){
                    res = c;
                    r = c-1;
                } else{
                    l = c+1;
                }
            } else {
                l = c+1;
            }
        }
        return res;
    }*/

    //二分区间
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length / 2 + 1; // 开区间
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(nums, mid)) left = mid;
            else right = mid;
        }
        return left * 2;
    }
    private boolean check(int[] nums, int k) {
        for (int i = 0; i < k; ++i)
            if (nums[i] * 2 > nums[nums.length - k + i])
                return false;
        return true;
    }
}




