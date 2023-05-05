package contest.year2023.m4.d2;

import java.util.*;

public class No4 {
    // 2612、最少反转次数

    //失败，超出时间限制
    //1、首先banned位置的元素比不可能变成1
    //2、如果n == k，则数组只能反转一次
    //3、bfs
    //4、如何确定每次翻转的位置？
    /*public int[] minReverseOperations(int n, int p, int[] banned, int k) {
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
    }*/

    //等差数列+bfs+平衡树
    //1、推算出位置i的对称点位的位置，设左边界l，右边界r，对称点为r+l-i
    //2、推算出当区间变动时，对称点的变动，如果区间向左移动，l需要减一，r也是如此，所以对称点需要减2；同理向右移动对称点需要加2
    //3、讨论边界问题
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        TreeSet<Integer> s = new TreeSet<>();
        return null;
    }
}
