package 中级算法复习.动态规划;

public class No1 {
    //          跳跃游戏

    public boolean canJump(int[] nums){
        int loc = 0,step = 0,i;
        if(nums.length == 1){
            return true;
        }

        for ( i = 0; i < nums.length; i++) {
            step--;
            if (nums[i] > step) {
                step = nums[i];
            }
            if (step == 0) {
                break;
            }
        }

       return i >= nums.length-1;
    }
}
