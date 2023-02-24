package algorithm.dp;

public class No42 {
    //接雨水
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n],right = new int[n];
        left[0] = height[0];
        right[n-1] = height[n-1];
        for (int i = 1; i < n; i++) {
            int j = n-1-i;
            left[i] = Math.max(height[i],left[i-1]);
            right[j] = Math.max(height[j],right[j+1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(left[i],right[i]) - height[i];
        }
        return res;
    }
}
