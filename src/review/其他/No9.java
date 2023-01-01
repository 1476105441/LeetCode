package review.其他;

public class No9 {
    /**
     *      多数元素
     */

    public int majorityElement(int[] nums) {
        int res = -1,c = 0,n = nums.length;
        for (int i = 0; i < n; i++) {
            if (c == 0) {
                res = nums[i];
                c = 1;
            }else{
                if (nums[i] == res) {
                    c++;
                }else{
                    c--;
                }
            }
        }

        return res;
    }
}
