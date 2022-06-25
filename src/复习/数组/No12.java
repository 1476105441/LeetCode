package 复习.数组;

import java.util.*;

public class No12 {
    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 提示：
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */

    //想法：固定一个数，转换为双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }

        int i=0,j,k,temp,value;
        Arrays.sort(nums);

        while (i < nums.length-2 && nums[i] <= 0) {
            j = i+1;
            k = nums.length-1;
            while (j < k) {
                value = nums[i]+nums[j]+nums[k];
                if (value == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j-1] == nums[j]) {
                        j++;
                    }
                    while (j < k && nums[k + 1] == nums[k]) {
                        k--;
                    }
                } else if (value < 0) {
                    j++;
                }else{
                    k--;
                }
            }

            temp = nums[i];
            while (i < nums.length && nums[i] == temp) {
                i++;
            }
        }

        return res;
    }
}
