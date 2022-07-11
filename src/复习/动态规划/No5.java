package 复习.动态规划;

public class No5 {
    /**
     *      跳跃游戏
     */

    //记录一个当前能走的步数，初始值为
    //数组第一个元素的值，然后遍历数组，
    //到达每一个元素时，有两种选择：
    // “踩”或者“不踩”当前元素，意思就
    //是是否要从当前元素往后跳，这样做
    //会使之前的步数被替换掉
    /*public boolean canJump(int[] nums) {
        int count = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                return false;
            }
            count--;
            count = Math.max(count,nums[i]);

        }

        return true;
    }*/

    //官方题解中所谓的贪心解法，其实也差不多
    public boolean canJump(int[] nums){
        int n = nums.length,r = 0;
        for (int i = 0; i < n; i++) {
            if (i <= r) {
                r = Math.max(r,i+nums[i]);
            }
            if (r >= n-1) {
                return true;
            }
        }
        return false;
    }
}
