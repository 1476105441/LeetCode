package algorithm.bitOperation;

public class No287 {
    //解法一：二分查找
    //使用二分查找，基于这么一个思想：在重复数字i之前的
    //数字数量等于数字，在重复数字以及之后的数字数量大于
    //数字
    /*public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1,r = n;
        while(l < r){
            int c = l + ((r-l) >> 1);
            if(check(nums,c)){
                l = c+1;
            } else {
                r = c;
            }
        }
        return l;
    }
    private boolean check(int[] nums,int val){
        int count = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] <= val){
                count++;
            }
        }
        return count <= val;
    }*/

    //解法二：使用set去重
    /*public int findDuplicate(int[] nums) {
        int n = nums.length;
        int[] set = new int[n+1];
        for(int i = 0;i < n;i++){
            set[nums[i]]++;
            if(set[nums[i]] > 1){
                return nums[i];
            }
        }
        return -1;
    }*/

    //解法三：二进制
    //统计二进制中每个位为1的个数
    public int findDuplicate(int[] nums) {
        int n = nums.length,max = 0;
        int[] x = new int[32],y = new int[32];
        for(int i = 0;i < n;i++){
            int loc = 0,val = nums[i];
            max = Math.max(max,val);
            while(val > 0){
                if((val & 1) == 1){
                    y[loc]++;
                }
                loc++;
                val = val >>> 1;
            }
        }
        for(int i = 1;i <= max;i++){
            int loc = 0,val = i;
            while(val > 0){
                if((val & 1) == 1){
                    x[loc]++;
                }
                loc++;
                val = val >>> 1;
            }
        }
        int res = 0;
        for(int i = 0;i < 32;i++){
            if(y[i] > x[i]){
                res += 1 << i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        No287 n = new No287();
        n.findDuplicate(new int[]{3,1,3,4,2});
    }
}
