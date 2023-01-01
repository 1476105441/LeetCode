package middle.排序和搜索;

public class No4 {
    //                  寻找峰值
    //峰值元素是指其值严格大于左右相邻值的元素。
    //给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    //你可以假设 nums[-1] = nums[n] = -∞ 。
    //你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

    //------------------------------------------------------------
    //二分查找，找大的那边好像一定是可以的？
    public int findPeakElement(int[] nums) {
        int center,low = 0,high = nums.length-1,res = 0;

        while (low <= high) {
            center = low + (high - low) / 2;

            if (center > 0 && center < nums.length-1) {
                if (nums[center] > nums[center - 1] && nums[center] > nums[center + 1]) {
                    res = center;
                    break;
                } else if (nums[center] < nums[center - 1]) {
                    high = center - 1;
                }else{
                    low = center + 1;
                }
            } else if (center == 0) {
                if (center < nums.length - 1) {
                    if (nums[center] > nums[center + 1]) {
                        break;
                    }else{
                        high = 2;
                    }
                }else{
                    break;
                }
            } else {
                if (center > 0) {
                    if (nums[center] > nums[center - 1]) {
                        res = center;
                        break;
                    }
                }
            }

        }

        return res;
    }
    //------------------------------------------------------------
}
