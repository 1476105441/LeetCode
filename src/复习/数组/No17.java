package 复习.数组;

public class No17 {
    /**
     *      递增的三元子序列
     * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
     * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
     *
     * 提示：
     *     1 <= nums.length <= 5 * 105
     *     -231 <= nums[i] <= 231 - 1
     *
     * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
     */

    //贪心解法就很简单了
    public boolean increasingTriplet(int[] nums){
        int min = Integer.MAX_VALUE,second = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= second) {
                second = nums[i];
            } else{
                return true;
            }
        }

        return false;
    }
}
