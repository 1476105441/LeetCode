package contest.year2023.m4.d9;

import java.util.*;

public class No2 {
    //2615、等值距离和

    //解法一：哈希表+前缀计算差值，18ms
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i < n;i++){
            List<Integer> list = map.get(nums[i]);
            if(list == null){
                list = new ArrayList<>();
                map.put(nums[i],list);
            }
            list.add(i);
        }
        Set<Integer> keySet = map.keySet();
        for(Integer key : keySet){
            List<Integer> list = map.get(key);
            int m = list.size();
            long prefix = 0,sum = 0;
            for(int i=0;i < m;i++){
                sum += list.get(i);
            }
            for(int i=0;i < m;i++){
                int val = list.get(i);
                long tmp = ((long)i * val - prefix) + ((sum - prefix - val) - (m-i-1) * (long)val);
                arr[val] = tmp;
                prefix += val;
            }
        }
        return arr;
    }
}
