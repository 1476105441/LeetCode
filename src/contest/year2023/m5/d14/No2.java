package contest.year2023.m5.d14;

/**
 * 2683.相邻值的按位异或
 *
 * @author wjs 2023/8/8
 */
public class No2 {
    //异或能够体现的就是相邻值是否相等，我们可以随意假设真正的值
    //成功，12ms，效率有点低
    /*public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int[] nums = new int[n];
        for(int i=0;i < n-1;i++) {
            if(derived[i] == 1) {
                if(nums[i] == 1) {
                    nums[i+1] = 0;
                } else {
                    nums[i+1] = 1;
                }
            } else {
                nums[i+1] = nums[i];
            }
        }
        return derived[n-1] == 1 ? nums[n-1] != nums[0] : nums[n-1] == nums[0];
    }*/

    //使用一个变量代替整个数组，11ms，基本没有效率上的提升
    /*public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int bit = 0;
        for(int i=0;i < n-1;i++) {
            if(derived[i] == 1) {
                if(bit == 1) {
                    bit = 0;
                } else {
                    bit = 1;
                }
            }
        }
        return derived[n-1] == 1 ? bit != 0 : bit == 0;
    }*/

    //解法二：推公式，2ms
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int bit = 0;
        for(int i=0;i < n;i++) {
            bit ^= derived[i];
        }
        return bit == 0;
    }
}
