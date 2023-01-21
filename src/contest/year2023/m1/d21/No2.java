package contest.year2023.m1.d21;

public class No2 {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        long res = 0,count = 0;
        if (k == 0) {
            for (int i = 0; i < n; i++) {
                if (nums1[i] != nums2[i]) {
                    return -1;
                }
            }
            return 0;
        }
        for (int i = 0; i < n; i++) {
            int dif = nums2[i] - nums1[i];
            if (dif % k != 0) {
                return -1;
            }
            count += dif;
            if (dif > 0) {
                //计算交换次数
                res += dif / k;
            }
        }
        return count == 0 ? res : -1;
    }
}
