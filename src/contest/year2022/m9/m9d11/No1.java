package contest.year2022.m9.m9d11;

import java.util.HashMap;
import java.util.Map;

public class No1 {
    /**
     *      出现最频繁的偶数元素
     */

    public int mostFrequentEven(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int n = nums.length,res = -1,min = Integer.MAX_VALUE,max = 0;
        for (int i = 0; i < n; i++) {
            if((nums[i] &1) == 0)
                map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        for(Map.Entry<Integer,Integer> node : map.entrySet()){
            if(node.getValue() > max || (node.getValue() == max && node.getKey() < min)){
                max = node.getValue();
                min = node.getKey();
                res = node.getKey();
            }
        }
        return res;
    }
}
