package 初级算法.数组;

public class No1 {
    /*
    删除排序数组中的重复项:
        给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
    不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

    提示：
        0 <= nums.length <= 3 * 104
        -104 <= nums[i] <= 104
        nums 已按升序排列

        作者：力扣 (LeetCode)
        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    /*----------------------------------------------------------------------------------
                                   失败版本：穷举法
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++){
            //确保数组下标不会越界
            while (i+1 != len && nums[i] == nums[i+1]) {
                //从i+2开始往前移,注意数组下标不要越界
                for(int j = i+1;j < len-1;j++) {
                    nums[j] = nums[j+1];
                }
                //数组个数更新
                len--;
            }
        }
        return len;
    }
    --------------------------------------------------------------------------------------

                             第二个失败品：9ms,并不是最优解
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++){
           //思路：如果有n个相同的，直接跳过n个
            int p;
            p = i+1;
            while(p != len && nums[p] == nums[i]){
                p++;
            }
            //p与i+1之间的间隔是p-i-1，也就是往前移p-i-1格
            for(int j=p;j<len;j++){
                nums[j-p+i+1] = nums[j];
            }
            len=len-p+i+1;
        }
        return len;
    ---------------------------------------------------------------------------------------
    }*/


    //                               最优解：双指针解法
    /*public int removeDuplicates(int[] nums){
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        //从1开始是因为数组如果元素个数大于1，必定会留下一个元素
        int fast=1,slow=1;
        while (fast < len) {
            if (nums[fast] != nums[fast-1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }*/

    //----------------------------------------------------------------------
    //自己重新写一遍
    public int removeDuplicates(int[] nums){
        if (nums.length < 1) {
            return 0;
        }

        int slow = 1,quick,temp = nums[0];

        for (quick = 1; quick < nums.length; quick++) {
            if (nums[quick] != temp) {
                nums[slow++] = nums[quick];
                temp = nums[quick];
            }
        }

        return slow;
    }
}
