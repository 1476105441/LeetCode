package review.数组;

import java.util.HashSet;
import java.util.Set;

public class No4 {
    /**
     *      存在重复元素
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     */

    //使用哈希表
    public boolean containsDuplicate(int[] nums){
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }else{
                set.add(nums[i]);
            }
        }

        return false;
    }
}
