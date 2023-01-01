package junior.数组;

import java.util.Arrays;

public class No4 {
    //                       存在重复元素
    //  给定一个整数数组，判断是否存在重复元素。
    // 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

    //-----------------------------------------------------------------------------------
    //                        使用set集合
    //思想：将数组中的元素依次加入set集合中，如果有重复的，set.add()函数会返回false，这时返回true即可
    //public boolean containsDuplicate(int[] nums){
    //        if (nums.length <= 1) {
    //            return false;
    //        }
    //        Set<Integer> set = new HashSet<>();
    //        for (int num:nums) {
    //            if (!set.add(num)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //}
    //-----------------------------------------------------------------------------------


    public boolean containsDuplicate(int[] nums) {
        //数组元素个数小于1肯定是没有重复元素的
        if (nums.length <= 1) {
            return false;
        }
        //先进行排序
        Arrays.sort(nums);
        //从第一个开始的元素与前一个元素进行对比
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }
}
