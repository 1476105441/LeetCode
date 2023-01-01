package contest.year2022.m12.d10;

import java.util.HashMap;
import java.util.Map;

public class No4 {
    //什么情况下，会变成无法满足题目要求？
    //需要动脑筋的题目，真的很难想到
    /*public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length,mid = -1,mc = 0,total = 0;
        long res = 0;
        int[] cnt = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                total++;
                res += i;
                cnt[nums1[i]]++;
                //更新众数
                if (cnt[nums1[i]] > mc) {
                    mc = cnt[nums1[i]];
                    mid = nums1[i];
                }
            }
        }

        for (int i = 0; i < n && mc * 2 > total; i++) {
            if (nums1[i] != nums2[i] && nums1[i] != mid && nums2[i] != mid) {
                res += i;
                total++;
            }
        }
        return mc * 2 > total ? -1 : res;
    }*/


    //这个题目的重点在于找规律，找出什么情况下，会出现无法达成目标的情况
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length,total = 0,mc = 0,m = 0;
        Map<Integer,Integer> map = new HashMap<>();
        long res = 0;

        //计算出总数
        for(int i = 0;i < n;i++){
            if(nums1[i] == nums2[i]){
                total++;
                res += i; //计算出正常情况下应该计算的结果
                int val = map.getOrDefault(nums1[i],0) + 1;
                if(val > mc){
                    mc = val;
                    m = nums1[i];
                }
                map.put(nums1[i],val);
            }
        }

        //特殊情况特殊处理
        for(int i = 0;i < n && mc * 2 > total;i++){
            if(nums1[i] != nums2[i] && nums1[i] != m && nums2[i] != m){
                //mc--;
                //此处不能使众数的个数减少，而是增加总的需要交换的数对个数
                //如果采用上面那种方式的话，试想这样的情况：总数为8，众数个数为5
                total++;
                res += i;
            }
        }
        if(mc * 2 > total) return -1;

        return res;
    }
}
