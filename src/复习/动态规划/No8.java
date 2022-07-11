package 复习.动态规划;

public class No8 {
    /**
     *      最长递增子序列
     */

    //使用优先队列进行优化（好像不行吧）

    //使用暴力动态规划
    /*public int lengthOfLIS(int[] nums) {
        int max = Integer.MIN_VALUE,n = nums.length;
        int[] dp = new int[n],heap = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }*/

    //怎么将时间复杂度优化到O(nlogn)呢？
    //不是使用动态规划，而是使用贪心+二分查找
    //维护一个数组，存放长度为i的序列的最末尾
    //元素，为了让序列尽可能长，每次选择尽可能
    //小的元素作为序列末尾
    public int lengthOfLIS(int[] nums){
        int n = nums.length,len = 0;
        int[] d = new int[n];
        d[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                len++;
                d[len] = nums[i];
            }else{
                //进行二分查找，寻求序列末尾尽可能小
                int l=0,r=len,c,loc=-1;
                while (l < r) {
                    c=l+((r-l)>>1);
                    if (nums[i] < d[c]) {
                        loc = c;
                        r = c-1;
                    }else
                        l = c+1;
                }
                if (loc != -1) {
                    if (loc == 0 || nums[i] > d[loc - 1]) {
                        d[loc] = nums[i];
                    }
                }
            }
        }

        return len+1;
    }

    /*public void shift(int[] nums,int loc,int n){
        int i = loc,j = (i<<1)+1;
        while (j < n) {
            if (j < n - 1 && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }

    public void update(int[] nums, int i) {
        int parent;
        while (i > 0) {
            parent = (i-1)>>1;
            if (nums[parent] < nums[i]) {
                int temp = nums[parent];
                nums[parent] = nums[i];
                nums[i] = temp;
                i = parent;
            }else
                break;
        }
    }*/
}
