package review.搜索和排序;

public class No7 {
    /**
     *      在排序数组中查找元素的第一个和最后一个位置
     */

    int start,end,target;
    public int[] searchRange(int[] nums, int target) {
        this.target = target;
        start = -1;
        end = -1;
        search(nums,0,nums.length-1,0);
        return new int[]{start,end};
    }

    public void search(int[] nums,int l,int r,int flag){
        if (l > r) {
            return;
        }
        int center = l +((r-l)>>1);
        if (nums[center] == target) {
            if (flag == 1 && center < start) {
                start = center;
                search(nums,l,center-1,flag);
            } else if (flag == 2 && center > end) {
                end = center;
                search(nums,center+1,r,flag);
            }else{
                start = center;
                end = center;
                search(nums,l,center-1,1);
                search(nums,center+1,r,2);
            }
        } else if (nums[center] > target) {
            search(nums,l,center-1,flag);
        }else
            search(nums,center+1,r,flag);
    }
}
