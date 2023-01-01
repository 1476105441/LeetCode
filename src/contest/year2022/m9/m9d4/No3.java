package contest.year2022.m9.m9d4;

import java.util.LinkedList;

public class No3 {
    /**
     *      最长优雅子数组
     */

    public int longestNiceSubarray(int[] nums) {
        int res = 1,n = nums.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(nums[0]);
        for (int i = 1; i < n; i++) {
            int s = queue.size();
            for (int j = 0; j < s && !queue.isEmpty();) {
                Integer tmp = queue.get(j);
                if ((tmp & nums[i]) != 0) {
                    queue.remove(j);
                    s--;
                }else
                    j++;
            }
            queue.add(nums[i]);
            s = queue.size();
            if(s > res)
                res = s;
        }
        return res;
    }
}
