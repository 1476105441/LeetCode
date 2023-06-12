package contest.year2023.m4.d23;

/**
 * 2654. 使数组所有元素变成 1 的最少操作次数
 * @author wjs 2023/6/12
 */
public class No4 {
    //无法变成1的情况：所有的元素都有最小公约数
    //成功，1ms
    public int minOperations(int[] nums) {
        int n = nums.length;
        int c = 0;
        for(int i=0;i < n;i++) {
            if(nums[i] == 1) {
                c++;
            }
        }
        if(c > 0) {
            return n-c;
        }
        //用于计算当前值和左右两边值的gcd
        int[] left = new int[n],right = new int[n];
        left[0] = -1;
        right[n-1] = -1;
        int tmp = nums[0];
        for(int i=1;i < n;i ++) {
            tmp = gcd(tmp,nums[i]);
            int j = n-i-1;
            left[i] = gcd(nums[i],nums[i-1]);
            right[j] = gcd(nums[j],nums[j+1]);
            if(left[i] == 1 || right[j] == 1) {
                return n;
            }
        }
        if(tmp != 1) {
            return -1;
        }
        int l = 0, r = 1, res = 2147483647;
        while(r < n) {
            int lv = countLeft(l,r,nums);
            if(lv != -1) {
                res = Math.min(res,lv);
            }
            int rv = countRight(l,r,nums);
            if(rv != -1) {
                res = Math.min(res,rv);
            }
            r++;
            l++;
        }
        return res+n;
    }
    private int countLeft(int l, int r, int[] nums) {
        int c = 0;
        int tmp = nums[r];
        while(l >= 0) {
            tmp = gcd(nums[l],tmp);
            if(tmp == 1) {
                return c;
            }
            if(tmp == nums[l]) {
                return -1;
            }
            l--;
            c++;
        }
        return -1;
    }
    private int countRight(int l, int r, int[] nums) {
        int c = 0;
        int tmp = nums[l];
        while(r < nums.length) {
            tmp = gcd(nums[r],tmp);
            if(tmp == 1) {
                return c;
            }
            if(tmp == nums[r]) {
                return -1;
            }
            r++;
            c++;
        }
        return -1;
    }
    private int gcd(int x, int y) {
        while(y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }
}
