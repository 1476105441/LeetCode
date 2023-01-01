package middle.排序和搜索;

public class No6 {
    //              在排序数组中查找元素的第一个和最后一个位置
    //给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    //如果数组中不存在目标值 target，返回 [-1, -1]。
    //进阶：
    //    你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

    //二分查找
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        halfSearch(res,nums,0,nums.length-1,0,target);

        return res;
    }

    //使用flag来判断当前找的是上界还是下界，如果都没找到，就是0，在第一次匹配到的
    // 时候就将上界和下界都更新，flag为1说明当前在找上界，flag为2说明在找下界
    public void halfSearch(int[] res,int[] nums,int low,int high,int flag,int target){
        if (low > high) {
            return;
        }

        int center = low + (high - low) / 2;

        if (nums[center] > target) {
            halfSearch(res,nums,low,center-1,flag,target);
        } else if (nums[center] < target) {
            halfSearch(res,nums,center + 1,high,flag,target);
        }else{
            if (flag == 0) {
                res[0] = center;
                res[1] = center;
                halfSearch(res,nums,low,center-1,1,target);
                halfSearch(res,nums,center + 1,high,2,target);
            } else if (flag == 1) {
                res[0] = center;
                halfSearch(res,nums,low,center-1,1,target);
            } else if (flag == 2) {
                res[1] = center;
                halfSearch(res,nums,center + 1,high,2,target);
            }
        }
    }
}
