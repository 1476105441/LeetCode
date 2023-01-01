package senior.数组和字符串;

public class No1 {
    //              除自身以外数组的乘积
    //给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    //提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
    //
    //说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
    //
    //进阶：
    //你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

    //------------------------------------------------------------------------------
    //思路:每一个数组元素除了其本身之外的乘积和该怎么求？
    //  由于是在数组中，利用这一特性，可以将数组中当前元素的除自身以外的乘积分成两部分：该元素之前的元素
    //总乘积和该元素之后的元素的总乘积相乘即为所要求的值。
    //那么为什么要分成两部分乘？
    //  分成两部分乘之后，可以按顺序遍历数组，按顺序的将两部分累乘起来，比如：从左边开始遍历，使用一个变量
    //可以存储左半部分的累乘结果，从右边开始遍历，使用一个变量可以存储右半部分的累乘结果。
    /*public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int left = 1,right = 1;
        boolean flag = (nums.length & 1) == 1;

        if (nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res[0] = 0;
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flag) {
                if (i <= nums.length/2) {
                    res[i] = 1;
                    res[nums.length-1-i] = 1;
                }
            }else{
                if (i < nums.length / 2) {
                    res[i] = 1;
                    res[nums.length-1-i] = 1;
                }
            }
            res[i] *= left;
            left *= nums[i];
            res[nums.length-1-i] *= right;
            right *= nums[nums.length-1-i];
        }

        return res;
    }*/

    //---------------------------------------------------------------------

    public int[] productExceptSelf(int[] nums){
        int[] res = new int[nums.length];
        int left = 1,right = 1;

        if (nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res[0] = 0;
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] *= left;
            left *= nums[i];
            res[nums.length-1-i] *= right;
            right *= nums[nums.length-1-i];
        }

        return res;
    }
}
