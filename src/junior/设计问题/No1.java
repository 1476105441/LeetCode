package junior.设计问题;

import java.util.Random;

public class No1 {
    //                  打乱数组
    //给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
    //实现 Solution class:
    //    Solution(int[] nums) 使用整数数组 nums 初始化对象
    //    int[] reset() 重设数组到它的初始状态并返回
    //    int[] shuffle() 返回数组随机打乱后的结果
    //提示：
    //    1 <= nums.length <= 200
    //    -106 <= nums[i] <= 106
    //    nums 中的所有元素都是 唯一的
    //    最多可以调用 5 * 104 次 reset 和 shuffle
}
class Solution{
    private int[] nums;
    private Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }

        //num是随机生成的循环次数
        int temp;
        for (int i = 0; i < res.length; i++) {
            int j = random.nextInt(i+1);
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        return res;
    }
}
