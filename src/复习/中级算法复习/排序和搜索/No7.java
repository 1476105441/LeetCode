package 复习.中级算法复习.排序和搜索;

public class No7 {
    //          搜索旋转排序数组

    public int search(int[] nums,int target){
        int leftNum = nums[0],left = 0,right = nums.length-1,center,res = -1;

        boolean flag;
        if (leftNum == target) {
            return 0;
        } else if (leftNum > target) {
            flag = true;
        }else{
            flag = false;
        }
        while(left <= right){
            center = left + (right - left) / 2;

            if (nums[center] == target) {
                res = center;
                break;
            } else if (nums[center] > target) {
                if (nums[center] < leftNum) {
                    right = center-1;
                }else if(nums[center] > leftNum){
                    if (flag) {
                        left = center + 1;
                    }else{
                        right = center - 1;
                    }
                }else{          //这里是当遍历的值等于最左边也就是说当前的寻找到了最边界处
                    left = center + 1;
                }
            }else{//当前元素值小于目标值
                if (nums[center] > leftNum) {//当前元素值大于最左边的值，正常往右边找
                    left = center + 1;
                }else if(nums[center] < leftNum){
                    //当前的元素值小于最左边的值，要判断最左边的值和目标值的大小才好确定寻找方向
                    if (flag) {//最左值大于目标值，正常往右找
                        left = center + 1;
                    }else{//最左值小于目标值，往左边找
                        right = center - 1;
                    }
                }else{//当前元素值等于最左值，到达了边界
                    left = center + 1;
                }
            }
        }

        return res;
    }
}
