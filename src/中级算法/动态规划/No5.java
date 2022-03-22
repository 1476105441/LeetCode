package 中级算法.动态规划;

public class No5 {
    //                          最长上升子序列
    //给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    //子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

    //----------------------------------------------------------------
    //动态规划解决，以当前下标为i的元素结尾的最长上升子序列的长度为：
    //在i之前的，值比i元素小的元素（下标为j）的结尾的最长上升子序列
    //的长度最大值加一
    /*public int lengthOfLIS(int[] nums){
        int[] dp = new int[nums.length];
        int res = -1,max;

        for (int i = 0; i < nums.length; i++) {
            max = 0;
            for (int j = i-1; j > -1; j--) {
                if (nums[i] > nums[j] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if (dp[i] > res) {
                res = dp[i];
            }
        }

        return res;
    }*/
    //-----------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //                              贪心+二分查找
    //此题还有更优解，将时间复杂度降低到nlog(n)
    //  贪心策略：为了让递增子序列更加长，每次选择最后一位（子序列的最后一位）时都要选择尽可能小的那个
    //这样选择可以让后续再添加时能够添加更多的元素
    //  代码实现：使用数组d[i]来存储长度为i的子序列的最小末尾值，此举是为了方便后续更新长度为i的子序
    //列的末尾值，使用变量len存储当前的最大递增子序列的长度，遍历数组中的每一个元素。若是当前元素nums[i]
    //大于d[len]则使len + 1，并将nums[i]赋值给增值之后的d[len]；若是当前元素不大于d[len]，则在1-(len-1)
    //中寻找第一个比nums[i]小的d[j]，并将nums[i]赋值给d[j],此举为更新最小的结尾元素
    public int lengthOfLIS(int[] nums){
        int[] d = new int[nums.length];
        int len = 0;
        d[len++] = nums[0];

        for (int i = 1; i < nums.length && len < nums.length; i++) {

            if (nums[i] > d[len-1]) {
                d[len++] = nums[i];
            }else{
                int j = halfSearch(d,0,len-1,nums[i]);
                if (j == -1) {
                    //出现-1时还要判断是否是小于第一个元素的情况
                    //若是的话，就更新第一个值，不是的话就说明在
                    //查找比nums[i]的过程中d[]数组中有元素的值
                    //跟nums[i]相等
                    if(nums[i] < d[0]){
                        d[0] = nums[i];
                    }
                }else{
                    d[j] = nums[i];
                }
            }

        }

        return len;
    }

    //  此方法的返回值只有两种，-1和非-1的数字（即找到了要更换的下标）
    //而返回-1时并不是只有在最右边的情况，也有在中间时d[i]的值与target的
    //值相同的情况而导致进入下层循环，最终也会导致出现-1，但是此时并不是
    //想要的需要替换的结果，因为我一开始想法是出现-1时只会是在最右边需要
    //替换的情况，这种想法是错误的
    public int halfSearch(int[] nums,int low,int high,int target){
        if (low > high) {
            return -1;
        }

        int center = low + (high - low)/2;

        if (target > nums[center] && target < nums[center + 1]) {
            return center + 1;
        } else if (target > nums[center + 1]) {
            return halfSearch(nums,center+1,high,target);
        } else {
            return halfSearch(nums,low,center-1,target);
        }

    }
    //在做这个的时候出现了反复错误的情况，因为一点小错误卡了我一个小时，反思反思吧
    //这个错误就是在查找d[0]~d[len-1]时，出现了疏漏，没有考虑到情况的冲突
}
