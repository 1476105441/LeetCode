package review.数组;

public class No18 {
    /**
     *      除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 提示：
     *     2 <= nums.length <= 105
     *     -30 <= nums[i] <= 30
     *     保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
     *
     * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */

    /*public int[] productExceptSelf(int[] nums){
        int n = nums.length,temp1 = 1,temp2 = 1;
        int[] prefix = new int[n],suffix = new int[n],answer = new int[n];

        for (int i = 0; i < n; i++) {
            prefix[i] = temp1;
            suffix[n-i-1] = temp2;
            temp1 *= nums[i];
            temp2 *= nums[n-i-1];
        }

        for (int i = 0; i < n; i++) {
            answer[i] = prefix[i] * suffix[i];
        }

        return answer;
    }*/

    //不使用数组，直接用两个变量存储前缀和后缀
    public int[] productExceptSelf(int[] nums){
        int n = nums.length,prefix = 1,suffix = 1;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            answer[i] *= prefix;
            answer[n-i-1] *= suffix;
            prefix *= nums[i];
            suffix *= nums[n-i-1];
        }

        return answer;
    }
}
