package review.数组;

import java.util.HashMap;
import java.util.Map;

public class No9 {
    /**
     *      两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     *
     * 提示：
     *     2 <= nums.length <= 104
     *     -109 <= nums[i] <= 109
     *     -109 <= target <= 109
     *     只会存在一个有效答案
     *
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     */

    public int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        int[] res = new int[2];
        Integer temp;

        for (int i = 0; i < nums.length; i++) {
            temp = map.get(target - nums[i]);
            if (temp != null) {
                res[0] = temp;
                res[1] = i;
                break;
            }else{
                map.put(nums[i],i);
            }
        }

        return res;
    }
}
