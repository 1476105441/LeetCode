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
    //效率瓶颈在于频繁的移动数组
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

    //解法二：基于一个简单的事实，由于取值的范围很小，
    //可以统计滑动窗口中每个值出现的次数，第x大的数满
    //足：小于其的数量 < x ，小于等于其的数量 >= x
    //成功，52ms，还不够快
    /*public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        final int BIAS = 50;
        int[] cnt = new int[2*BIAS+1];
        int[] res = new int[n-k+1];
        for(int i=0;i < k;i++) {
            int loc = nums[i]+BIAS;
            cnt[loc]++;
        }
        int l = 0, r = k-1;
        while(r < n) {
            int sum = 0;
            for(int i=0;i < cnt.length;i++) {
                if(sum < x && sum + cnt[i] >= x) {
                    res[l] = i < 50 ? i-50 : 0;
                    break;
                }
                sum += cnt[i];
            }
            cnt[nums[l]+BIAS]--;
            l++;
            r++;
            if(r < n) {
                cnt[nums[r]+BIAS]++;
            }
        }
        return res;
    }*/

    //优化了一下，40多ms
    /*public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        final int BIAS = 50;
        int[] cnt = new int[2*BIAS+1];
        int[] res = new int[n-k+1];
        for(int i=0;i < k;i++) {
            int loc = nums[i]+BIAS;
            cnt[loc]++;
        }
        int l = 0, r = k-1;
        while(r < n) {
            int sum = 0;
            //在这里并不需要遍历全部的元素，只需要遍历负数就行了
            for(int i=0;i < BIAS;i++) {
                if(sum < x && sum + cnt[i] >= x) {
                    res[l] = i-50;
                    break;
                }
                sum += cnt[i];
            }
            cnt[nums[l]+BIAS]--;
            l++;
            r++;
            if(r < n) {
                cnt[nums[r]+BIAS]++;
            }
        }
        return res;
    }*/

    //再次优化，30多ms
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        final int BIAS = 50;
        int[] cnt = new int[2*BIAS+1];
        int[] res = new int[n-k+1];
        for(int i=0;i < k;i++) {
            int loc = nums[i]+BIAS;
            cnt[loc]++;
        }
        int l = 0, r = k-1;
        while(r < n) {
            int sum = 0;
            for(int i=0;i < BIAS;i++) {
                //优化了判断逻辑，只要找到第一个大于x的值就可以了
                sum += cnt[i];
                if(sum >= x) {
                    res[l] = i-50;
                    break;
                }
            }
            cnt[nums[l]+BIAS]--;
            l++;
            r++;
            if(r < n) {
                cnt[nums[r]+BIAS]++;
            }
        }
        return res;
    }
}
