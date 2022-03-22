package 中级算法.排序和搜索;

public class No7 {
    //              搜索旋转排序数组
    //整数数组 nums 按升序排列，数组中的值 互不相同 。
    //在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
    //给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

    //二分查找再加逻辑判断？
    int res;

    public int search(int[] nums, int target) {
        res = -1;
        halfSearch(nums,0,nums.length-1,target);
        return res;
    }

    //从首尾判断当前是否需要再到另一边边查找
    public void halfSearch(int[] nums, int low, int high, int target) {
        if (low > high) {
            return;
        }

        int center = low + (high - low) / 2;

        if (nums[center] == target) {
            res = center;
        } else if (nums[center] > target) {
            halfSearch(nums, low, center - 1, target);
            if (nums[low] > nums[high]) {
                halfSearch(nums, center + 1, high, target);
            }
        } else {
            halfSearch(nums, center + 1, high, target);
            if (nums[low] > nums[high]) {
                halfSearch(nums, low, center - 1, target);
            }
        }
    }
}
