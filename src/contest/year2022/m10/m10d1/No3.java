package contest.year2022.m10.m10d1;

public class No3 {
    /**
     *      所有数对的异或和
     */

    /*public int xorAllNums(int[] nums1, int[] nums2) {
        int n = nums1.length,m = nums2.length;
        List<Integer> list = new ArrayList<>(m+n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.add(nums1[i] ^ nums2[j]);
            }
        }

        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res ^= list.get(i);
        }
        return res;
    }*/

    public int xorAllNums(int[] nums1, int[] nums2) {
        int n = nums1.length,m = nums2.length;

        int res = 0;
        if ((m & 1) == 1) {
            for (int i = 0; i < n; i++) {
                res ^= nums1[i];
            }
        }
        if ((n & 1) == 1) {
            for (int i = 0; i < m; i++) {
                res ^= nums2[i];
            }
        }

        return res;
    }
}
