package contest.year2023.m2.d6;

import java.util.Arrays;

public class No2 {
    /*public long countFairPairs(int[] nums, int lower, int upper) {
        long res = 0;
        int n = nums.length,l = 0,r = n-1;
        Arrays.sort(nums);
        while (r > 0 &&nums[0] + nums[r] > upper) {
            r--;
        }
        while (l < n && nums[l] + nums[0] < lower) {
            l++;
        }
        if (l < r) {
            res += r - l;
            if (0 < l) {
                res++;
            }
        }
        for (int i = 1; i < n; i++) {
            while (l > 0 && nums[l-1] + nums[i] >= lower) {
                l--;
            }
            while (r > 0 && nums[i] + nums[r] > upper) {
                r--;
            }
            if (l == 0 && r == 0) {
                break;
            }
            if (l < r) {
                res += r - l;
                if (i < l || i > r) {
                    res++;
                }
            }
        }
        return res;
    }*/

    //二分查找
    public long countFairPairs(int[] nums, int lower, int upper) {
        long res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int dif1 = lower - nums[i],dif2 = upper - nums[i];
            int l = findLt(nums, dif1, false), r = findLt(nums,dif2,true);
            if (l != -1 && r != -1 && nums[l] + nums[i] <= upper && nums[r] + nums[i] >= lower) {
                res += r - l;
                if (i < l || i > r) {
                    res++;
                }
            }
        }
        return res >> 1;
    }
    //flag为true表示找小于等于的，false表示找大于等于的，同时还要特殊考虑相等的情况
    private int findLt(int[] nums,int val,boolean flag){
        int n = nums.length,l = 0,r = n-1;
        int res = -1;
        while (l <= r) {
            int c = l + ((r-l) >> 1);
            if (nums[c] > val) {
                if (!flag) res = c;
                r = c - 1;
            } else if (nums[c] < val) {
                if(flag) res = c;
                l = c + 1;
            } else {
                res = c;
                if (flag) {
                    for (int i = c + 1; i < n && nums[i] == nums[c]; i++) {
                        res = i;
                    }
                } else {
                    for (int i = c - 1; i > -1 && nums[i] == nums[c]; i--) {
                        res = i;
                    }
                }
                break;
            }
        }
        return res;
    }
}
