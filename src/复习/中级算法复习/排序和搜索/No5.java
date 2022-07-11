package 复习.中级算法复习.排序和搜索;

public class No5 {
    //          在排序数组中查找元素的第一个和最后一个位置

    int[] res;
    public int[] searchRange(int[] nums,int target){
        res = new int[]{-1,-1};
        if (nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                res[0] = 0;
                res[1] = 1;
            }
            return res;
        }

        find(nums,target,0,nums.length-1,0);

        return res;
    }

    /**
     *     flag的值为：0,1,2
     *     0:表示当前还没查找到任何一个边界
     *     1:查找到了左边界
     *     2:查找到了右边界
     */
    public void find(int[] nums,int target,int left,int right,int flag){
        if (left > right) {
            return;
        }
        int center = left + (right - left)/2;

        if (nums[center] > target) {
            find(nums,target,left,center-1,flag);
        } else if (nums[center] < target) {
            find(nums,target,center+1,right,flag);
        }else {
            if (center == 0) {
                res[0] = center;
                if (nums[center + 1] != target) {
                    res[1] = center;
                }else if (flag == 0) {
                    find(nums,target,center+1,right,1);
                }
                return;
            }else if (center == nums.length - 1) {
                res[1] = center;
                if (nums[center - 1] != target){
                    res[0] = center;
                }else if (flag == 0) {
                    find(nums,target,left,center-1,2);
                }
                return;
            }
            if (nums[center - 1] != target && nums[center + 1] != target) {
                res[0] = center;
                res[1] = center;
                return;
            }
            if (nums[center - 1] != target) {
                res[0] = center;
                if (flag == 0) {
                    find(nums,target,center+1,right,1);
                }
            } else if (nums[center + 1] != target) {
                res[1] = center;
                if (flag == 0) {
                    find(nums,target,left,center-1,2);
                }
            }else{
                if (flag == 0) {
                    find(nums,target,left,center-1,2);
                    find(nums,target,center+1,right,1);
                } else if (flag == 1) {
                    find(nums,target,center+1,right,flag);
                } else if (flag == 2) {
                    find(nums,target,left,center-1,flag);
                }
            }
        }
    }
}
