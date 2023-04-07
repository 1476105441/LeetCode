package contest.year2023.m3.d26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No3 {
    //失败
    //queries按下标排序，nums按大小排序
    /*public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length,m = queries.length;
        List<Long> res = new ArrayList<>(m);
        List<Integer> tmp = new ArrayList<>(m);
        for(int i=0;i < m;i++) {
            tmp.add(i);
            res.add(0L);
        }
        Arrays.sort(nums);
        Collections.sort(tmp,(x, y) -> queries[x]-queries[y]);
        long basic = 0;
        int c = 0,pre;
        //第一次特殊处理
        for(int i=0;i < n;i++){
            int v = 0;
            if(nums[i] <= queries[tmp.get(0)]) {
                c++;
                v = queries[tmp.get(0)] - nums[i];
            } else {
                v = nums[i] - queries[tmp.get(0)];
            }
            basic += v;
        }
        res.set(tmp.get(0),basic);
        pre = queries[tmp.get(0)];
        for(int i=1;i < m;i++){
            while(c < n && nums[c] < queries[tmp.get(i)]){
                long dis = nums[c] - pre;
                pre = nums[c];
                basic = basic + (c - n + c) * dis;
                c++;
            }
            long dis = queries[tmp.get(i)] - pre;
            pre = queries[tmp.get(i)];
            basic = basic + (c - n + c) * dis;
            c++;
            res.set(tmp.get(i),basic);
        }
        return res;
    }*/

    //排序原数组，计算前缀和，二分查找
    //成功，45ms
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length,m = queries.length;
        List<Long> res = new ArrayList<>(m);
        long[] sum = new long[n+1];
        for(int i=1;i <= n;i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        for(int i=0;i < m;i++){
            int loc = find(nums,queries[i]);
            long v1 = (long)queries[i] * loc - sum[loc];
            long v2 = (sum[n]-sum[loc]) - (long)queries[i] * (n-loc);
            res.add(v1+v2);
        }
        return res;
    }
    //二分找到第一个大于当前值的下标
    private int find(int[] nums,int val){
        int l = 0,r = nums.length-1,res = r+1;
        while(l <= r){
            int c = l+((r-l)>>1);
            if(nums[c] > val){
                r = c-1;
                res = c;
            } else {
                l = c+1;
            }
        }
        return res;
    }
}
