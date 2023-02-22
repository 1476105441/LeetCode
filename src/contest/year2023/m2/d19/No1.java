package contest.year2023.m2.d19;

public class No1 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int m = nums1.length,n = nums2.length,cnt = 0;
        int[] bucket = new int[1001];
        for (int i = 0; i < m; i++) {
            int idx = nums1[i][0],val = nums1[i][1];
            if (bucket[idx] == 0) {
                cnt++;
            }
            bucket[idx] += val;
        }
        for (int i = 0; i < n; i++) {
            int idx = nums2[i][0],val = nums2[i][1];
            if (bucket[idx] == 0) {
                cnt++;
            }
            bucket[idx] += val;
        }
        int[][] res = new int[cnt][2];
        int loc = 0;
        for (int i = 0; i < 1001; i++) {
            if (bucket[i] > 0) {
                res[loc][0] = i;
                res[loc][1] = bucket[i];
                loc++;
            }
        }
        return res;
    }
}
