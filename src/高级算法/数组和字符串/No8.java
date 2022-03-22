package 高级算法.数组和字符串;

public class No8 {
    /**
     *              寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * 进阶：
     *     如何证明 nums 中至少存在一个重复的数字?
     *     你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
     */

    //双重循环可以解决吧？超时，暴力解法真垃圾
    /*public int findDuplicate(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }*/



    /**
     *          解法一：二分查找
     *   使用count数组来统计nums中元素出现的个数
     * 如count[i] 表示 nums中小于等于i的元素的
     * 个数，则用target表示重复的数字，满足：
     *      i小于target时      count[i] <= i
     *      i大于等于target时   count[i] > i
     * 根据此性质，可以使用二分查找
     */
    /*public int findDuplicate(int[] nums){
        int left = 1,right = nums.length - 1,center,res = 0;

        while (left <= right) {
            int count = 0;
            center = left + (right - left) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= center) {
                    count++;
                }
            }
            if (count > center) {
                res = center;
                right = center - 1;
            }else{
                left = center + 1;
            }
        }

        return res;
    }*/



    /**
     *          解法二：按位恢复
     *   存在这样的现象：
     * 统计数组中所有数字的第i位为1的数字个数，记
     * 为x，计算数字1~n中第i位为1的数字个数，记为
     * y，若target的第i位为1，则x > y ，因为数
     * 组中此位为1的数字多了一个或多个，所以只需要
     * 统计数组中哪些位为1的数字多了，最后再按位还
     * 原就可以了
     */
    /*public int findDuplicate(int[] nums){
        int n = nums.length,bit = (int)(Math.log(n -1) / Math.log(2)),res = 0;

        for (int i = 0; i <= bit; i++) {
            int x = 0,y = 0,temp = 1;
            temp = temp << i;
            for (int j = 0; j < n; j++) {
                if ((nums[j] & temp) == temp) {
                    x++;
                }
                if (j > 0 && (j & temp) == temp) {
                    y++;
                }
            }
            if (x > y) {
                res += temp;
            }
        }

        return res;
    }*/


    /**
     *          解法三：快慢指针法
     *   按照数值走到对应下标的做法，数组中有重复数字
     * 所以一定会形成一个环
     *   想法：
     * 在数组中建立从下表i到nums[i]的边，快指针一次
     * 走两步，慢指针一次走一步，当快慢指针第一次相遇
     * 时，让慢指针回到起点，快指针和慢指针一次都只走
     * 一步再次相遇的点就是重复数的点，即环的入口
     */
    public int findDuplicate(int[] nums){
        int slow = 0,quick = 0;

        do{
            slow = nums[slow];
            quick = nums[nums[quick]];
        }while(nums[slow] != nums[quick]);
        slow = 0;
        while (nums[slow] != nums[quick]) {
            slow = nums[slow];
            quick = nums[quick];
        }

        return nums[slow];
    }
}
