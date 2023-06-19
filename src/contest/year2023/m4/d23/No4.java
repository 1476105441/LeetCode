package contest.year2023.m4.d23;

import java.util.ArrayList;
import java.util.List;

/**
 * 2654. 使数组所有元素变成 1 的最少操作次数
 * @author wjs 2023/6/12
 */
public class No4 {
    //无法变成1的情况：所有的元素都有最小公约数
    //成功，1ms
    //成功是成功了，但是写的太复杂，将近100行代码
    /*public int minOperations(int[] nums) {
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
    }*/
    /*private int gcd(int x, int y) {
        while(y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }*/

    //仔细思考，其实本题只有三种情况：
    //1、nums中有值为1的元素
    //2、没有值为1的元素，但是数组中的元素经过gcd之后可以得到1
    //3、数组中的元素gcd之后无法得到1
    /*public int minOperations(int[] nums) {
        int tmp = 0, c = 0, n = nums.length;
        for(int i=0;i < n;i++) {
            tmp = gcd(tmp,nums[i]);
            if(nums[i] == 1) {
                c++;
            }
        }
        if(tmp > 1) {
            return -1;
        }
        if(c > 0) {
            return n-c;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i < n;i++) {
            int t = 0;
            for(int j=i;j < n;j++) {
                t = gcd(t,nums[j]);
                if(t == 1) {
                    min = Math.min(j-i+1,min);
                    break;
                }
            }
        }
        return min+n-2;
    }*/

    //时隔许久，重新写一遍
    /*public int minOperations(int[] nums) {
        int n = nums.length, tmp = 0, c = 0;
        for(int i=0;i < n;i++) {
            if(nums[i] == 1) {
                c++;
            }
            tmp = gcd(tmp,nums[i]);
        }
        if(c > 0) {
            return n-c;
        }
        if(tmp != 1) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i < n;i++) {
            int ct = 0;
            for(int j=i;j < n;j++) {
                ct = gcd(ct,nums[j]);
                if(ct == 1) {
                    res = Math.min(res,j-i);
                    break;
                }
            }
        }
        return res + n - 1;
    }
    private int gcd(int x, int y) {
        while(y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }*/

    //另一种解法，在数组的数据量比较大时效率更佳
    //时间复杂度分析我没看懂，不过可以肯定的是，
    //这种写法减少了枚举的范围
    public int minOperations(int[] nums) {
        int n = nums.length, tmp = 0, c = 0;
        for(int i=0;i < n;i++) {
            if(nums[i] == 1) {
                c++;
            }
            tmp = gcd(tmp,nums[i]);
        }
        if(c > 0) {
            return n-c;
        }
        if(tmp != 1) {
            return -1;
        }
        List<int[]> list = new ArrayList<>();
        int res = Integer.MAX_VALUE;
        for(int i=0;i < n;i++) {
            list.add(new int[]{nums[i],i});
            int j = 0;
            for(int[] t : list) {
                t[0] = gcd(t[0],nums[i]);
                if(list.get(j)[0] == t[0]) {
                    list.get(j)[1] = t[1];
                } else {
                    j++;
                    list.set(j,t);
                }
            }
            if(list.get(0)[0] == 1) {
                res = Math.min(res,i-list.get(0)[1]);
            }
        }
        return res + n - 1;
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
