package 中级算法.数组和字符串;

public class No6 {
    //                  递增的三元子序列
    //给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
    //如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
    //
    //进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？

    //--------------------------------------------------------------------
    //                      暴力解法：超时
    /*
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        for (int i = 0; i + 1 < nums.length; i++) {
            for (int j = i + 1; j + 1 < nums.length; j++) {
                if (nums[j] <= nums[i]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if(nums[k] <= nums[j]) {
                        continue;
                    }
                    return true;
                }
            }
        }

        return false;
    }*/
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //                          动态规划解决:也超时
    /*public boolean increasingTriplet(int[] nums){
        int[] d = new int[nums.length];

        d[0] = 1;

        int dMax, max = 0;
        for (int i = 0; i < nums.length; i++) {
            dMax = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (d[j] + 1 > dMax) {
                        dMax = d[j] + 1;
                    }
                }
            }
            d[i] = dMax;
            if (d[i] > max) {
                max = d[i];
            }
        }

        return max >= 3;
    }*/
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //                          贪心算法
    //  真想不到了...看了题解就明白了：存储两个值，一个最小值，一个次小值，初始值都为Integer.MAX_VALUE
    //若当前值比最小值小，则更新最小值，否则，若当前值比次小值小，更新次小值。当出现值比次小值大时，就证明找
    //到了一个递增三元组，原因如下：次小值的初始值为int类型的最大值，若次小值没有被更新，则不可能有值会大于
    //次小值，当次小值被更新时，最小值一定是被更新过的，因为要先满足大于最小值，才能和次小值比较。所以说，更
    //新次小值时，在这个元素的前面一定有一个比其小的曾经最小值（可能会被更新掉），所以当有一个值大于次小值时，
    //一定是有一个递增三元组序列了。
    public boolean increasingTriplet(int[] nums){
        int min = Integer.MAX_VALUE,sMin = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= sMin) {
                sMin = nums[i];
            } else {
                return true;
            }
        }

        return false;
    }
    //--------------------------------------------------------------------------

}
