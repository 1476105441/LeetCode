package 复习.数组;

public class No8 {
    /**
     *      移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 提示:
     *     1 <= nums.length <= 104
     *     -231 <= nums[i] <= 231 - 1
     *
     * 进阶：你能尽量减少完成的操作次数吗？
     */

    //双指针
    /*public void moveZeroes(int[] nums){
        int zero = 0,temp;
        for (int i = 0; i < nums.length && zero < nums.length; i++) {
            while (zero < nums.length && nums[zero] != 0 ) {
                zero++;
            }
            if (zero == nums.length) {
                break;
            }
            if (i > zero && nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = temp;
                zero++;
            }
        }
    }*/

    //最快版本，不用交换
    /*public void moveZeroes(int[] nums){
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[zero++] = nums[i];
            }
        }

        for (int i = zero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }*/

    //精妙的双指针，统计前面0的个数
    public void moveZeroes(int[] nums){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else if (count != 0) {
                nums[i-count] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
