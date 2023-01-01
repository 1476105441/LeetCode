package junior.数组;

import java.util.*;

public class No9 {
    //                                两数之和
    //  给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    //你可以按任意顺序返回答案。
    //
    //提示：
    //
    //    2 <= nums.length <= 104
    //    -109 <= nums[i] <= 109
    //    -109 <= target <= 109
    //    只会存在一个有效答案
    //
    //进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2jrse/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //----------------------------------------------------------------------------
    //非常失败品，让我觉得自己就是个傻逼，搞了那么多都没有用
//    public int[] twoSum(int[] nums, int target) {
//        //over指针存放第一个大于或等于target的值,low指针始终小于target
//        int  low = 0, j = -1;
//        int[] res = new int[2];
//        for (; low < nums.length && nums[low] < target; low++) {
//            //分三种情况
//            if (nums[low] < 0) {
//                j = function(nums, target, -1, low);
//            } else if (nums[low] == 0) {
//                j = function(nums, target, 0, low);
//            } else {
//                j = function(nums, target, 1, low);
//            }
//            if (j != -1) {
//                break;
//            }
//        }
//        res[0] = j;
//        res[1] = low;
//        return res;
//    }
//
//    public static int function(int[] nums, int target, int flag, int low) {
//        //nums[low]小于0的情况
//        if (flag == -1) {
//            for (int i = low + 1; i < nums.length; i++) {
//                if (nums[low] + nums[i] == target) {
//                    return i;
//                }
//            }
//        } else if (flag == 0) {
//            for (int i = low + 1; i < nums.length ; i++) {
//                if ( nums[i] == target) {
//                    return i;
//                }
//            }
//        } else {
//            for (int i = low + 1; i < nums.length ; i++) {
//                if (nums[low] + nums[i] == target) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
    //----------------------------------------------------------------------------

    //HashMap集合解决
    public int[] twoSum(int[] nums, int target){
        //*不能用hashset集合，因为hashset集合没有get方法
        //  这种方法的思想是：差值法，用target减去nums[i]如果在Map里面有的话，就可以返回
        //使用哈希表不用着急把数组的值装载进去
        //*value部分存储的是数组下标
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //取出符合条件的数组下标
            if (map.get(target - nums[i]) != null) {
                return new int[]{map.get(target - nums[i]),i};
            }
            //不符合条件就把当前的i放入数组中
            map.put(nums[i],i);
        }
        return new int[]{0,0};
    }
}
