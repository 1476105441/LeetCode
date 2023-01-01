package junior.数组;

public class No3 {
    //                        旋转数组
    //  要求：给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

    //  进阶：
    //    尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    //    你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？

    //  提示：
    //      1 <= nums.length <= 2 * 104
    //      -231 <= nums[i] <= 231 - 1
    //      0 <= k <= 105

    //      作者：力扣 (LeetCode)
    //      链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
    //      来源：力扣（LeetCode）
    //      著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



    /*
    //--------------------------------------------------------------------------------
    //                            失败产品
    public void rotate(int[] nums, int k) {
        if (nums.length == 1 || k == 0 || k == nums.length) {
            return;
        }
        //快慢两个指针来遍历，temp是用于交换的一个存储空间,用j来代替k，方便操作
        int quick, slow, len = nums.length, temp, j = k;
        slow = 0;
        quick = slow;
        if (j > len) {
            j %= len;
        } else if (j > len/2) {
            //分奇数和偶数
            if (len % 2 == 0) {
                j = len/2;
            }else{
                j = len/2+1;
            }
        }
        for (; slow < j; ) {
            quick += j;
            if (quick > len - 1) {
                //大于len - 1 时
                quick = len - j + slow;
                //如果满足下式，则说明已经交换过一次，不需要再交换了
                if ((quick - slow) % j == 0) {
                    slow++;
                    quick = slow;
                    continue;
                }
                //如果是最后一趟，且达到了此状态，直接退出即可，不然会重复交换
                if (slow == j - 1) {
                    break;
                }
                //交换值
                temp = nums[quick];
                nums[quick] = nums[slow];
                nums[slow] = temp;
                //只有到这时才说明一次循环已经完成，slow指针往前移动
                slow++;
                quick = slow;
            }
            //交换值
            temp = nums[quick];
            nums[quick] = nums[slow];
            nums[slow] = temp;
        }
    }
    //没解决掉k>nums.length时的情况
    //--------------------------------------------------------------------------------
    */

    /*
    //--------------------------------------------------------------------------------
    //                              成功产品（非最优解）
    public static void rotate(int[] nums, int k){
        if (nums.length == 1 || k == 0 || k == nums.length) {
            return;
        }
        //快慢两个指针来遍历，temp是用于交换的一个存储空间,用j来代替k，方便操作
        int quick, slow, len = nums.length, temp,temp1,count = 0;
        slow = 0;
        quick = slow;
        temp = nums[slow];
        for (; count <len; ) {
            quick = (quick + k) % len;
            //说明已经遍历一个循环了
            if (quick == slow) {
                //交换值
                nums[quick] = temp;
                //只有到这时才说明一次循环已经完成，slow指针往前移动
                slow++;
                temp = nums[slow];
                quick = slow;
                count++;
                continue;
            }
            //交换值
            temp1 = nums[quick];
            nums[quick] = temp;
            temp = temp1;
            count++;
        }
    }
    //--------------------------------------------------------------------------------
    */

    //--------------------------------------------------------------------------------
    //                            翻转数组（最优解）
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    //翻转数组的方法
    public static void reverse(int[] nums,int start,int end){
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end -= 1;
        }
    }
    //--------------------------------------------------------------------------------

}
