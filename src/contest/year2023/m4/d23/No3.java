package contest.year2023.m4.d23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2653.滑动子数组的美丽值
 *
 * @author wjs 2023/6/11
 */
public class No3 {

    //二分查找，1000多ms，不应该啊
    /*List<Integer> stack;
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        stack = new ArrayList<>(k);
        int[] res = new int[n-k+1];
        for(int i=0;i < k;i++) {
            stack.add(nums[i]);
        }
        Collections.sort(stack);
        int l = 0, r = k-1;
        while(r < n) {
            int tmp = stack.get(x-1);
            res[l] = tmp < 0 ? tmp : 0;
            int loc = find(nums[l]);
            //System.out.println(loc);
            stack.remove(loc);
            r++;
            l++;
            if(r < n){
                loc = find(nums[r]);
                stack.add(loc+1,nums[r]);
            }
        }
        return res;
    }
    private int find(int val) {
        int l = 0, r = stack.size() - 1;
        int res = -1;
        while(l <= r) {
            int c = l + ((r-l) >> 1);
            if(stack.get(c) <= val) {
                res = c;
                l = c+1;
            } else {
                r = c-1;
            }
        }
        return res;
    }*/
}
