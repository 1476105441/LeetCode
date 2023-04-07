package contest.year2023.m3.d26;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No2 {
    //想法：让每一个元素尽可能的小，首先要先找出小于1000的质数
    List<Integer> list;
    boolean[] set;
    {
        list = new ArrayList<>();
        set = new boolean[1001];
        for(int i=2;i <= 1000;i++){
            if(!set[i]) {
                list.add(i);
                set[i] = true;
                for(int j=i;j * i <= 1000;j++){
                    int v = j*i;
                    set[v] = true;
                }
            }
        }
    }
    public boolean primeSubOperation(int[] nums) {
        int n = nums.length,pre = 0;
        nums[0] -= find(nums[0]);
        pre = nums[0];
        for(int i=1;i < n;i++){
            if(nums[i] <= pre) return false;
            int v = nums[i]-pre;
            v = find(v);
            nums[i] -= v;
            pre = nums[i];
        }
        return true;
    }
    private int find(int v){
        int l = 0,r = list.size()-1,res = -1;
        while(l <= r){
            int c = l+((r-l)>>1);
            if(list.get(c) >= v){
                r = c-1;
            } else {
                l = c+1;
                res = c;
            }
        }
        return res == -1 ? 0 : list.get(res);
    }
}
