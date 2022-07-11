package 周赛.m7d10;

public class No4 {
    /**
     *      统计理想数组的数目
     */

    //回溯，超时
    /*int[] nums;
    int m,res;
    public int idealArrays(int n, int maxValue) {
        nums = new int[n];
        m = maxValue;
        res = 0;
        find(0,1);

        return res;
    }
    public void find(int loc,int val){
        if (loc == nums.length) {
            res = (res+1) % 1000000007;
            return;
        }
        for (int i = val; i <= m; i++) {
            if (loc == 0 || i % nums[loc - 1] == 0) {
                nums[loc] = i;
                find(loc+1,nums[loc]);
            }
        }
    }*/

    int[] nums;
    int m,res;
    public int idealArrays(int n, int maxValue) {
        nums = new int[n];
        m = maxValue;
        res = 0;
        find(0,1);

        return res;
    }
    public void find(int loc,int val){
        if (loc == nums.length) {
            res = (res+1) % 1000000007;
            return;
        }
        for (int i = 1; i*val <= m; i++) {
            nums[loc] = i*val;
            find(loc+1,nums[loc]);
        }
    }
}
