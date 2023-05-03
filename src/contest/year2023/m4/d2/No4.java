package contest.year2023.m4.d2;

import java.util.*;

public class No4 {
    // 2612、最少反转次数

    //失败，超出时间限制
    //1、首先banned位置的元素比不可能变成1
    //2、如果n == k，则数组只能反转一次
    //3、bfs
    //4、如何确定每次翻转的位置？
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] res = new int[n];
        Arrays.fill(res,-1);
        res[p] = 0;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i < banned.length;i++){
            set.add(banned[i]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(p);
        int dep = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int loc = queue.poll();
                int r = loc,l = r-k+1,t = l;
                while(l <= loc){
                    while(l < 0){
                        l++;
                        r++;
                    }
                    t = r-loc+l;
                    if(t == -7)
                        System.out.println(r+" "+l);
                    if(r < n && !set.contains(t) && res[t] == -1){
                        res[t] = dep;
                        queue.add(t);
                    }
                    l++;
                    r++;
                }
                size--;
            }
            dep++;
        }
        return res;
    }
}
