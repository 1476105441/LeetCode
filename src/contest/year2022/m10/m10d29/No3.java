package contest.year2022.m10.m10d29;

import java.util.HashMap;
import java.util.Map;

public class No3 {
    //根据求余数来分类
    public int destroyTargets(int[] nums, int space) {
        int n = nums.length,res = 0,max = 0;

        Map<Integer,int[]> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int loc = nums[i] % space;
            int[] ints = map.get(loc);
            if (ints == null) {
                ints = new int[2];
                ints[0] = nums[i];
                ints[1] = 1;
                map.put(loc,ints);
            }else{
                if(nums[i] < ints[0])
                    ints[0] = nums[i];
                ints[1]++;
            }
            if (max < ints[1]) {
                max = ints[1];
                res = ints[0];
            } else if (max == ints[1]) {
                //System.out.println(res);
                res = Math.min(res,ints[0]);
            }
        }

        return res;
    }
}
