package practice;

import java.util.Arrays;

public class No462 {
    /**
     *      最小操作次数使数组元素相等 II
     */

    //成功
    public int minMoves2(int[] nums) {
        int n = nums.length,res = 0;

        Arrays.sort(nums);
        int val = nums[n >> 1];
        res += sum(nums,val);

        if ((n & 1) == 0) {
            res = Math.min(res,sum(nums,nums[(n >> 1) - 1]));
        }

        return res;
    }
    public int sum(int[] nums,int val){
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res += Math.abs(nums[i]-val);
        }

        return res;
    }
}
