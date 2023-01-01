package contest.year2022.m9.m9d25;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    /**
     * 找到所有好下标
     */

    //想法：维护两个单调队列
    /*public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length, l1 = 0, r1 = 0, l2 = 0, r2 = 0;
        int[] queue1 = new int[n], queue2 = new int[n];

        //初始化两个单调队列
        for (int i = 0; i < k; i++) {
            if (r1 > l1 && nums[queue1[r1 - 1]] < nums[i])
                r1 = l1;
            queue1[r1] = i;
            r1++;
        }
        for (int i = k+1; i <= k+k && i < n; i++) {
            if(r2 > l2 && nums[queue2[r2-1]] > nums[i])
                r2 = l2;
            queue2[r2] = i;
            r2++;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = k; i < n - k; i++) {
            if(r1 - l1 == k && r2 - l2 == k)
                list.add(i);
            //更新单调队列
            int j = i;
            if(r1 - l1 == k)
                l1++;
            if (r1 > l1 && nums[queue1[r1 - 1]] < nums[j]) {
                r1 = l1;
            }
            queue1[r1] = j;
            r1++;
            j = i + k + 1;
            if (j < n) {
                if(r2 - l2 == k)
                    l2++;
                if(r2 > l2 && nums[queue2[r2-1]] > nums[j])
                    r2 = l2;
                queue2[r2] = j;
                r2++;
            }
        }
        return list;
    }*/

    //重新写一遍，利用前缀和
    public static List<Integer> goodIndices(int[] nums, int k){
        int n = nums.length;
        int[] left = new int[n],right = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1])
                left[i] = left[i-1]+1;
            else
                left[i] = 1;
            if(nums[n-i-1] <= nums[n-i])
                right[n-i-1] = right[n-i]+1;
            else
                right[n-i-1] = 1;
        }
        List<Integer> res = new ArrayList<>(n);
        for (int i = k; i < n-k; i++) {
            if(left[i-1] >= k && right[i+1] >= k)
                res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        goodIndices(new int[]{2,1,1,1,3,4,1},2);
    }
}
