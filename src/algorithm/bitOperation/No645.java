package algorithm.bitOperation;

public class No645 {
    //1、通过xor去除掉不符合条件的元素
    //2、通过前面一步求出的结果找到两个元素的不同特征
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int xor = 0,num1 = 0,num2 = 0;
        for(int i = 1;i <= n;i++){
            xor ^= i;
        }
        for(int i = 0;i < n;i++){
            xor ^= nums[i];
        }
        int lowbit = xor & (-xor);
        for(int i = 1;i <= n;i++){
            //这里不能用等于1来判断!
            if((i & lowbit) == 0){
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for(int i = 0;i < n;i++){
            if((nums[i] & lowbit) == 0){
                num1 ^= nums[i];
            } else {
                num2 ^= nums[i];
            }
        }
        int[] res = new int[2];
        for(int i = 0;i < n;i++){
            if(nums[i] == num1){
                res[0] = num1;
                res[1] = num2;
                return res;
            }
        }
        res[0] = num2;
        res[1] = num1;
        return res;
    }
}
