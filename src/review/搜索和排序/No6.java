package review.搜索和排序;

public class No6 {
    /**
     *      寻找峰值
     */

    //二分查找，每次只找
    /*public int findPeakElement(int[] nums) {
        int left = 0,right = nums.length-1,center;
        //处理只有一个元素的情况
        if (right == left) {
            return 0;
        }
        while (left < right) {
            center = left + (right-left)/2;
            if(center == 0){
                if (nums[center] < nums[center + 1]) {
                    left++;
                }else
                    right--;
            } else{
                if (nums[center] > nums[center - 1] && nums[center] > nums[center + 1]) {
                    return center;
                } else if (nums[center] < nums[center - 1]) {
                    right = center-1;
                }else{
                    left = center+1;
                }
            }
        }

        return left;
    }*/

    //代码太复杂，可以进一步简化
    public int findPeakElement(int[] nums){
        int l = 0,r = nums.length-1;
        while(l < r){
            int c = l + ((r-l)>>1);
            if (nums[c] < nums[c + 1]) {
                l = c+1;
            }else
                r = c;
        }
        return l;
    }
}
