package contest.year2023.m4.d1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>周赛第三题<p/>
 *
 * @author wjs 2023/4/16
 */
public class No3 {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        long res = 0;
        int l = gcd(n,k);
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0;i < l;i++) list.add(new ArrayList<>());
        for(int i = 0;i < n;i++){
            int loc = i % l;
            list.get(loc).add(arr[i]);
        }
        for(int i = 0;i < l;i++) {
            List<Integer> nums = list.get(i);
            Collections.sort(nums);
            Integer mid = nums.get(nums.size() / 2);
            for(Integer num : nums){
                res += Math.abs(num-mid);
            }
        }
        return res;
    }
    private int gcd(int x,int y){
        int tmp;
        while(y > 0){
            tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }
}
