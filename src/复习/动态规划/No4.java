package 复习.动态规划;

public class No4 {
    /**
     *      打家劫舍
     */

    //使用i表示当前要打劫的房屋的编号，
    //对于房屋i，我们有两种选择：选择
    //打劫或者不打劫，打劫的话，所获得
    //的金额与是否打劫第i-2个房屋有关，
    //因为打劫当前房屋，那么前面一个房
    //屋就不能打劫
    public int rob(int[] nums) {
        int a,b,res;
        if (nums.length == 1) {
            return nums[0];
        }
        a = nums[0];
        b = Math.max(a,nums[1]);
        res = Math.max(a,b);

        for (int i = 2; i < nums.length; i++) {
            res = Math.max(a+nums[i],b);
            a = b;
            b = res;
        }

        return res;
    }
}
