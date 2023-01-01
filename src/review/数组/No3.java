package review.数组;

public class No3 {
    /**
     *      旋转数组
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     *
     * 提示：
     *     1 <= nums.length <= 105
     *     -231 <= nums[i] <= 231 - 1
     *     0 <= k <= 105
     *
     * 进阶：
     *     尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
     *     你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     */

    //第一种解法：反转数组
    /*public void rotate(int[] nums,int k){
        //由于题目没有说明k的大小，k有可能比数组长度要大，所以要取余数
        k = k % nums.length;
        reverse(nums,nums.length-1,0);
        reverse(nums,k-1,0);
        reverse(nums,nums.length-1,k);
    }

    public void reverse(int[] nums,int end,int begin){
        int temp;
        //双指针进行交换
        while (begin < end) {
            temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            end--;
            begin++;
        }
    }*/

    //第二种解法：分组交换法
    public void rotate(int[] nums,int k){
        int temp1,temp2,j,i;
        int[] temp = new int[k];
        for (int l = 0; l < k && l < nums.length; l++) {
            temp[l] = nums[l];
        }
        for (i = 0; i < k && i < nums.length; i++) {
            temp1 = temp[i];
            for (j = i+k; j < nums.length; j+=k) {
                temp2 = nums[j];
                nums[j] = temp1;
                temp1 = temp2;
            }
            j = j%nums.length;
            nums[j] = temp1;
        }
    }
}
