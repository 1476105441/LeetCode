package 中级算法.动态规划;

public class No1 {
    //              跳跃游戏
    //给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
    //数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //判断你是否能够到达最后一个下标。

    //-----------------------------------------------------------------
    //              此解法是贪心法
    //一个填表的思想，从第一个元素开始，记录可以到达的最远下标
    /*public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int res = 0;

        res += nums[0];

        for (int i = 1; i < nums.length &&  i <= res; i++) {
            int temp = i + nums[i];
            if (temp > res) {
                res = temp;
            }
            if (res >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }*/
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //                  动态规划解决
    //如何确定状态转移方程（动态规划方程）？
    //使用dp[]数组存放当前元素还能往后跳的格子数，此时有两种情况，踩在当前格子上或者不踩（踩就代表要从
    // 当前格子往后跳，不踩就是不从当前格子跳，接着之前跳的继续往后）
    public boolean canJump(int[] nums){
        int temp = nums[0];
        int i;

        for (i = 0; i < nums.length; i++) {
            if (temp == 0 && nums[i] == 0) {
                break;
            }

            if (temp < nums[i]) {
                temp = nums[i];
            }

            temp--;
        }

        return i == nums.length-1;
    }
}
