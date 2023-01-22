package contest.year2023.m1.d22;

import java.util.HashSet;
import java.util.Set;

public class No4 {
    //超时
    /*public int minCost(int[] nums, int k) {
        int n = nums.length,res = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = k;
        }
        for (int i = 1; i < n; i++) {
            int l = 0,r = l + i;
            while (r < n) {
                int temp = count(nums,l,r) + k;
                int c = r - l;
                for (int j = 1; j < c; j++) {
                    temp = Math.min(temp,dp[l][r-j] + dp[r-j+1][r]);
                    temp = Math.min(temp,dp[l][l+j-1] + dp[l+j][r]);
                }
                dp[l][r] = temp;
                r++;
                l++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("   ");
            }
            for (int j = i; j < n; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }
        return dp[0][n-1];
    }
    private int count(int[] nums,int s,int e){
        int res = 0;
        int[] bucket = new int[1001];
        for (int i = s; i <= e; i++) {
            if (bucket[nums[i]] > 0) {
                if (bucket[nums[i]] == 1) {
                    res += 2;
                } else {
                    res++;
                }
            }
            bucket[nums[i]]++;
        }
        return res;
    }*/

    //超时
    /*public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = k;
        }
        for (int i = 1; i < n; i++) {
            int l = 0, r = l + i, len = 0;
            int[] set = new int[1001];
            for (int j = 0; j < r; j++) {
                if (set[nums[j]] > 0) {
                    if (set[nums[j]] == 1) {
                        len += 2;
                    } else {
                        len++;
                    }
                }
                set[nums[j]]++;
            }
            while (r < n) {
                if (set[nums[r]] > 0) {
                    if (set[nums[r]] == 1) {
                        len += 2;
                    } else {
                        len++;
                    }
                }
                set[nums[r]]++;
                int temp = len + k;
                int c = r - l;
                for (int j = 1; j < c; j++) {
                    temp = Math.min(temp, dp[l][r - j] + dp[r - j + 1][r]);
                    temp = Math.min(temp, dp[l][l + j - 1] + dp[l + j][r]);
                }
                dp[l][r] = temp;
                if (set[nums[l]] > 1) {
                    if (set[nums[l]] == 2) {
                        len -= 2;
                    } else {
                        len--;
                    }
                }
                set[nums[l]]--;
                r++;
                l++;
            }
        }
        return dp[0][n - 1];
    }*/

    //dp的思路错了，方向错了
    //成功，28ms
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] set = new int[1001];
            int c = 0,temp = Integer.MAX_VALUE;
            for (int j = i; j > -1; j--) {
                if (set[nums[j]] > 0) {
                    if (set[nums[j]] == 1) {
                        c += 2;
                    } else {
                        c++;
                    }
                }
                set[nums[j]]++;
                temp = Math.min(temp,dp[j] + c);
            }
            dp[i+1] = temp + k;
        }
        return dp[n];
    }
}
