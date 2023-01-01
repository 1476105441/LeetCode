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

    public int minimizeArrayValue(int[] nums){
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
    }
}
