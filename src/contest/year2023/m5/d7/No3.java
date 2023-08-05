package contest.year2023.m5.d7;

public class No3 {
    public int[] colorTheArray(int n, int[][] queries) {
        int m = queries.length;
        int[] nums = new int[n];
        int[] res = new int[m];
        int cnt = 0;
        for(int i=0;i < m;i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            if(nums[idx] == val) {
                res[i] = cnt;
                continue;
            }
            if(idx > 0) {
                if(nums[idx-1] == val) {
                    cnt++;
                } else if(nums[idx-1] != 0 && nums[idx-1] == nums[idx]) {
                    cnt--;
                }
            }
            if(idx < n-1) {
                if(nums[idx+1] == val) {
                    cnt++;
                } else if(nums[idx+1] != 0 && nums[idx+1] == nums[idx]) {
                    cnt--;
                }
            }
            res[i] = cnt;
            nums[idx] = val;
        }
        return res;
    }
}
