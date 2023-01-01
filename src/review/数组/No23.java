package review.数组;

public class No23 {
    /**
     *      缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 提示：
     *     1 <= nums.length <= 5 * 105
     *     -231 <= nums[i] <= 231 - 1
     */

    //想法：使用映射关系，每个位置上都代表当前这个数值
    //是否存在，存在即为负数，不存在为正数
    /*public int firstMissingPositive(int[] nums){
        int n = nums.length,value,res = n+1;
        //先将全部元素设置为正数
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n+1;
            }
        }
        //进行映射
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0)
                value = -nums[i];
            else
                value = nums[i];
            if (value > 0 && value < n+1 && nums[value-1] > 0) {
                nums[value-1] = -nums[value-1];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res = i+1;
                break;
            }
        }

        return res;
    }*/

    //解法二：使用置换的方法，把正确的数放在正确的位置
    public int firstMissingPositive(int[] nums){
        int n = nums.length,res = n+1;

        for (int i = 0; i < n; i++) {
            int value = nums[i],temp;
            while (value > 0 && value < n+1 && value != i + 1 && value != nums[value-1]) {
                temp = nums[value-1];
                nums[value-1] = value;
                nums[i] = temp;
                value = nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                res = i+1;
                break;
            }
        }

        return res;
    }
}
