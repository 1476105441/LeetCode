package review.数组;

import java.util.HashMap;
import java.util.Map;

public class No20 {
    /**
     *      四数相加II
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     *     0 <= i, j, k, l < n
     *     nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * 提示：
     *     n == nums1.length
     *     n == nums2.length
     *     n == nums3.length
     *     n == nums4.length
     *     1 <= n <= 200
     *     -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
     */

    public int fourSumCount(int[] nums1,int[] nums2,int[] nums3,int[] nums4){
        Map<Integer,Integer> map = new HashMap<>();
        int n = nums1.length,res = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums1[i] + nums2[j];
                map.put(temp,map.getOrDefault(temp,0)+1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums3[i] + nums4[j];
                Integer value = map.get(-temp);
                if (value != null) {
                    res += value;
                }
            }
        }

        return res;
    }
}
