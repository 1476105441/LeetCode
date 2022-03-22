package 高级算法.数组和字符串;

public class No6 {
    /**
     *              缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     */

    /**
     *           解法一：将数组设计成为哈希表形式
     *   对于一个长度为n的数组，其中缺失的第一个正数一定是在[1,n+1]
     * 范围内，因为最好的情况就是数组中的n元素为从1开始连续的序列，
     * 缺失的第一个正数就是n+1，否则一定是在[1,n]之间的
     *
     * 解题步骤：
     * 1、将数组中的所有负数更改为n+1，因为要使用负数来标记当前的数是
     * 否在数组中
     * 2、遍历数组，如果当前元素（下标为i）的绝对值在[1,n]之间的，对
     * 第i-1个元素进行取反，注意不要重复取反了
     * 3、第二步进行完成后，再次遍历数组，第一个不为负数的数组下标就是
     * 缺失的第一个正数，注意要进行加一操作，因为数组下标是从0开始的
     */
    /*public int firstMissingPositive(int[] nums){
        int n = nums.length,res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int temp = Math.abs(nums[i]);
            if (temp >= 1 && temp <= n) {
                if (nums[temp - 1] > 0) {
                    nums[temp - 1] *= -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res = i + 1;
                return res;
            }
        }

        return n+1;
    }*/

    /**
     *              解法二：置换
     *   思想：采用交换的方式，将正确的数交换到正确的位置
     * 比如：如果数组中有1，那么它的位置应该是数组中的第一个元素的位置
     *
     * 注意：交换位置之后的当前元素仍然有可能还在[1,n]之间，所以还要
     * 继续交换，这种情况有可能出现死循环，为了防止出现死循环，加上判
     * 定结束循环的条件：nums[i] = x = nums[x-1]
     */
    public int firstMissingPositive(int[] nums){
        int temp,n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n) {
                int x = nums[i] - 1;
                if (nums[x] == nums[i]) {
                    break;
                }else {
                    temp = nums[i];
                    nums[i] = nums[x];
                    nums[x] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i+1;
            }
        }
        return n+1;
    }
}
