package contest.year2023.m3.d19;

public class No3 {
    //回溯
    int res;
    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        res = n;
        int[] set = new int[10001];
        for(int i = 0;i < n;i++){
            updateSet(nums,set,i,k);
            recursion(nums,set,i+1,k);
            removeSet(nums,set,i,k);
        }
        return res;
    }
    private void recursion(int[] nums,int[] set,int i,int k){
        if(i == nums.length) return;
        if(set[nums[i]] == 0){
            //有两种选择，选择添加或者不添加
            res++;
            updateSet(nums,set,i,k);
            recursion(nums,set,i+1,k);
            removeSet(nums,set,i,k);
        }
        //不添加的回溯
        recursion(nums,set,i+1,k);
    }
    private void updateSet(int[] nums,int[] set,int i,int k){
        set[nums[i]+k]++;
        if(nums[i]-k > 0) set[nums[i]-k]++;
    }
    private void removeSet(int[] nums,int[] set,int i,int k){
        set[nums[i]+k]--;
        if(nums[i]-k > 0) set[nums[i]-k]--;
    }
}
