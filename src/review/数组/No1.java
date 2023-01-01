package review.数组;

public class No1 {
    /**
     *      删除排序数组中的重复项
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 提示：
     *     0 <= nums.length <= 3 * 104
     *     -104 <= nums[i] <= 104
     *     nums 已按 升序 排列
     */

    public int removeDuplicates(int[] nums){
        int slow = 1,quick = 1,temp = nums[0];

        while (quick < nums.length) {
            if (nums[quick] != temp) {
                temp = nums[quick];
                nums[slow++]=nums[quick];
            }
            quick++;
        }

        return slow;
    }
}
