package contest.year2023.m4.d9;

import java.util.Arrays;

public class No3 {
    // 2616、最小化数对的最大差值

    //一般这种找出最大值的最小值的题目，可以使用二分法解决
    //或者贪心
    /*public int minimizeMax(int[] nums, int p) {
        if(p == 0){
            return 0;
        }
        Arrays.sort(nums);
        int l=0,r=1000000000;
        while(l < r){
            int c=((r-l)>>1) + l;
            if(check(c,p,nums)){
                r = c;
            } else{
                l = c+1;
            }
        }
        return l;
    }
    private boolean check(int val,int p,int[] nums){
        int n = nums.length;
        for(int i=0;i < n;i++){
            if(i < n-1 && Math.abs(nums[i] - nums[i+1]) <= val){
                p--;
                //跳过下一个元素
                i++;
                if(p == 0){
                    return true;
                }
            }
        }
        return p == 0;
    }*/

    //重写
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        Arrays.sort(nums);
        int l=0, r=1000000000;
        while(l < r) {
            int c = l + ((r-l) >> 1);
            if(check(c,nums,p)){
                r = c;
            } else {
                l = c+1;
            }
        }
        return l;
    }
    private boolean check(int val, int[] nums, int p) {
        for(int i=0; i < nums.length-1; i++) {
            if(nums[i+1] - nums[i] <= val) {
                p--;
                i++;
                if(p == 0) return true;
            }
        }
        return p <= 0;
    }
}
