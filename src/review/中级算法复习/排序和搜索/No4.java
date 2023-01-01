package review.中级算法复习.排序和搜索;

public class No4 {
    //      寻找峰值

    public int findPeakElement(int[] nums){
        if(nums.length == 1){
            return 0;
        }
        int low = 0,high = nums.length-1,center,res = -1;

        while (low <= high) {
            center = low + (high - low) /2;

            if (center < nums.length - 1) {
                //等于0的情况
                if (center == 0) {
                    if (nums[center] > nums[center + 1]) {
                        res = center;
                        break;
                    }else{
                        high++;
                        continue;
                    }
                }
                //前面已经判断过等于0的情况了，所以这里可以放心的使用center-1
                if (nums[center] > nums[center-1] && nums[center] > nums[center+1]) {
                    res = center;
                    break;
                }
                if (nums[center] < nums[center + 1]) {
                    low = center + 1;
                }else{
                    high = center - 1;
                }
            }else if(nums[center] > nums[center - 1]){
                res = center;
                break;
            }
        }

        return res;
    }
}
