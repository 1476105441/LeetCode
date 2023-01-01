package review.数组;

public class No25 {
    /**
     *      寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * 提示：
     *     1 <= n <= 105
     *     nums.length == n + 1
     *     1 <= nums[i] <= n
     *     nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     *
     * 进阶：
     *     如何证明 nums 中至少存在一个重复的数字?
     *     你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
     */

    //解法一：二分查找
    //根据目标的特性，在目标数字之前的数字小于等
    //于它们的个数一定小于等于它们本身，而target
    //及其后的数字一定大于等于它们本身，因为target
    //是重复的数字
    /*public int findDuplicate(int[] nums){
        int low = 1,high = nums.length-1,center,count,res=0;

        while (low <= high) {
            center = low + (high - low) / 2;
            count=0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= center) {
                    count++;
                }
            }

            if (count > center) {
                res = center;
                high = center-1;
            }else
                low = center+1;
        }

        return res;
    }*/

    //解法二：标识二进制还原法
    //计算1-n的二进制位每个位置的个数x和数组中每个
    //元素的二进制位每个位置的个数y，y若是大于x，则说
    //明目标值中当前二进制为1
    /*public int findDuplicate(int[] nums){
        int x = 0,y = 0,res = 0,count = (int)(Math.log(nums.length-1)/Math.log(2));

        while (count > 0) {
            x = 0;
            y = 0;
            int temp = 1 << count;
            for (int i = 0; i < nums.length; i++) {
                if ((temp & nums[i]) != 0) {
                    x++;
                }
                if (i > 0 && (temp & i) != 0) {
                    y++;
                }
            }
            if (y > x) {
                res += temp;
            }
            count--;
        }

        return res;
    }*/

    //解法三：双指针，快慢指针法
    //首先建立一个从nums[i]到下标i的边映射关系，当前
    //nums[i]的值决定了我们该走哪里，然后使用快慢指针
    //快指针每次走两步，慢指针每次走一步，相遇时让慢指针
    //回退到起点，然后快慢指针都每次只走一步，再次相遇时
    //的下标就是重复元素
    public int findDuplicate(int[] nums){
        int quick = 0,slow = 0;

        do{
            quick = nums[nums[quick]];
            slow = nums[slow];
        }while(quick != slow);

        slow = 0;
        while (slow != quick) {
            slow = nums[slow];
            quick = nums[quick];
        }

        return slow;
    }
}
