package 复习.中级算法复习.动态规划;

public class No4 {
    //          最长上升子序列

    /*public int lengthOfLIS(int[] nums){
        int[] temp = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            temp[i] = 1;
            for (int j = i-1; j > -1 ; j--) {
                if (nums[j] < nums[i] && temp[j]+1 > temp[i]) {
                    temp[i] = temp[j]+1;
                }
            }
            if (max < temp[i]) {
                max = temp[i];
            }
        }

        return max;
    }*/



    //使用一维数组，缩短动态规划的时间
    /*public int lengthOfLIS(int[] nums){
        int[] temp = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            temp[i] = 1;
            for (int j = i-1; j > -1 ; j--) {
                if (nums[j] < nums[i] && temp[j]+1 > temp[i]) {
                    temp[i] = temp[j]+1;
                }
            }
            if (max < temp[i]) {
                max = temp[i];
            }
        }

        return max;
    }*/

    public int lengthOfLIS(int[] nums){
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        int count = 1,loc;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > temp[count - 1]) {
                temp[count++] = nums[i];
            }else{
                loc = find(temp,count-1,nums[i]);
                if (loc != -1) {
                    temp[loc] = nums[i];
                }
            }
        }

        return count;
    }

    public int find(int[] nums,int i,int target){
        int res = -1,left = 0,right = i,center;
        while (left <= right) {
            center = left + (right - left) / 2;
            if (nums[center] > target) {
                if (center > 0 && nums[center-1] < target) {
                    res = center;
                    break;
                } else if (center == 0) {
                    res = center;
                    break;
                }else {
                    right = center - 1;
                }
            }else {
                left = center + 1;
            }
        }

        return res;
    }
}
