package contest.year2022.m10.m10d15;

public class No3 {
    /*public int minimizeArrayValue(int[] nums) {
        int n = nums.length,max,dif = 0;

        max = nums[0];

        for (int i = 1; i < n; i++) {
            if(nums[i] < max)
                dif += max - nums[i];
            else {
                int temp = nums[i] - max;
                if (dif != 0) {
                    if (temp >= dif) {
                        temp -= dif;
                    } else {
                        dif -= temp;
                        continue;
                    }
                }
                int x = temp / (i + 1);
                int y = temp % (i + 1);
                max += x;
                if (y != 0) {
                    max++;
                    dif += i + 1 - y;
                }
            }
        }

        return max;
    }*/

    //解法一：思维转换，求平均值问题
    //4-6ms
    /*public int minimizeArrayValue(int[] nums){
        int n = nums.length;
        long res = 0,sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if ((sum % (i+1) == 0)) {
                res = Math.max(res,sum / (i+1));
            } else {
                res = Math.max(res,sum / (i+1) + 1);
            }
        }

        return (int) res;
    }*/

    //解法二：二分查找，寻找最大值的最小值/最小值的最大值问题都可以往二分查找上面想
    //49ms
    /*public int minimizeArrayValue(int[] nums) {
        long l = 0,r = 1000000000;
        while(l < r){
            long c = l + ((r-l) >> 1);
            if(examine(nums,c) > c){
                l = c + 1;
            } else {
                r = c;
            }
        }
        return (int)l;
    }
    public long examine(int[] nums,long mid){
        long[] tmp = new long[nums.length];
        for(int i=0;i < nums.length;i++){
            tmp[i] = nums[i];
        }
        for(int i=nums.length-1;i > 0;i--){
            if(tmp[i] > mid){
                tmp[i-1] += tmp[i] - mid;
                tmp[i] -= mid;
            }
        }
        return tmp[0];
    }*/

    //二分查找第二种写法：
    //15-31ms
    public int minimizeArrayValue(int[] nums) {
        long l = 0,r = 1000000000;
        while(l < r){
            long c = l + ((r-l) >> 1);
            if(!examine(nums,c)){
                l = c + 1;
            } else {
                r = c;
            }
        }
        return (int)l;
    }
    public boolean examine(int[] nums,long mid){
        long val = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < mid) {
                val += mid - nums[i];
            } else {
                if (nums[i] - mid > val) {
                    return false;
                }
                val -= nums[i] - mid;
            }
        }
        return true;
    }
}
