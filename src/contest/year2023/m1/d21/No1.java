package contest.year2023.m1.d21;

import java.util.HashSet;
import java.util.Set;

public class No1 {
    public int getCommon(int[] nums1, int[] nums2) {
        int n1 = nums1.length,n2 = nums2.length,res = -1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n1; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < n2; i++) {
            if (set.contains(nums2[i])) {
                res = nums2[i];
                break;
            }
        }
        return res;
    }
}
