package contest.year2023.m2.d4;

import java.util.*;

public class No3 {
    /*public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length,limit = prizePositions[n-1];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(prizePositions[i],map.getOrDefault(prizePositions[i],0)+1);
        }
        int l = 1,r = 1,sum = 0,max = 0,mid = 0;
        while (r <= k + 1) {
            sum += map.getOrDefault(r,0);
            r++;
        }
        max = sum;
        while (r <= limit) {
            sum -= map.getOrDefault(r,0);
            sum += map.getOrDefault(r,0);
            if (max < sum) {
                mid = max;
                max = sum;
            } else if (mid < sum) {
                mid = sum;
            }
            r++;
            l++;
        }
        return max + mid;
    }*/

    //前缀、二分
    //没能实现，太复杂了
    /*public int maximizeWin(int[] prizePositions, int k){
        int n = prizePositions.length,now = prizePositions[0];
        List<Integer> prefix = new ArrayList<>(),map = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            //计算前缀和映射关系
            if (now != prizePositions[i])  {
                prefix.add(sum);
                map.add(now);
                now = prizePositions[i];
            }
            sum++;
        }
        n = map.size();
        int max = 0,preMax = 0;
        for (int i = 0; i < n; i++) {
            //以当前元素为起点
            int end = find(map.get(i)+k,map);
            int j = map.get(i) - 1,x = j - k - 1;
            if (j < 0) {

            }
            if (end == n - 1) {
                break;
            }
        }
        return preMax;
    }
    //二分找到映射中第一个比它小的
    private int find(int val,List<Integer> map){
        int n = map.size();
        int l = 0,r = n - 1,res = 0;
        while (l <= r) {
            int c = l + ((r-l) >> 1);
            if (map.get(c) == val) {
                res = c;
                break;
            } else if (map.get(c) > val) {
                r = c - 1;
            } else {
                res = c;
                l = c + 1;
            }
        }
        return res;
    }*/

    /**
     * 想法：双指针，同时不必将数据单独存储起来
     */
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        //维护一个数值数组，表示当前下标i之前的最大满足条件的窗口值
        int[] prefix = new int[n+1];
        int l = 0,res = 0;
        for (int r = 0; r < n; r++) {
            //维护双指针，使双指针的长度在k及以内
            while (prizePositions[r] - prizePositions[l] > k) {
                l++;
            }
            res = Math.max(res, r - l + 1 + prefix[l]);
            prefix[r + 1] = Math.max(prefix[r], r - l + 1);
        }
        return res;
    }
}
