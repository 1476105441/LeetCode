package contest.year2022.m9.m9d3;

import java.util.LinkedList;

public class No4 {
    /**
     *      预算内的最多机器人数目
     */

    //这二比题目，原来是要求连续的机器人数目
    /*int r = 0,l = 0;
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int res = 0,n = chargeTimes.length;
        long sum = 0;
        int[] queue = new int[n];

        for (int i = 0; i < n; i++) {
            while (l != r && chargeTimes[queue[r-1]] < chargeTimes[i]) {
                r--;
            }
            queue[r++] = chargeTimes[i];
            while (l != r && chargeTimes[queue[l]] + sum(queue, runningCosts) * (r - l) > budget) {
                l++;
            }
            if (r-l > res) {
                res = r-l;
            }
        }

        return res;
    }
    public long sum(int[] queue,int[] running){
        long res = 0;
        for (int i = l; i < r; i++) {
            res += running[queue[i]];
        }
        return res;
    }*/

    //搞错了，再来
    /*public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget){
        int n = chargeTimes.length,r = 0,l = 0,res = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        long sum = 0;

        for(;r < n;r++){
            while (!queue.isEmpty() && chargeTimes[queue.getLast()] < chargeTimes[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            sum += runningCosts[r];
            while (!queue.isEmpty() && chargeTimes[queue.getFirst()] + (r-l+1) * sum > budget) {
                if(queue.getFirst() == l){
                    queue.pollFirst();
                }
                sum -= runningCosts[l++];
            }
            res = Math.max(res,r-l+1);
        }

        return res;
    }*/


    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget){
        int n = chargeTimes.length,r = 0,l = 0,res = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        long sum = 0;

        while(r < n){
            while (!queue.isEmpty() && chargeTimes[queue.getLast()] < chargeTimes[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            sum += runningCosts[r];
            while (!queue.isEmpty() && chargeTimes[queue.getFirst()] + (r-l+1) * sum > budget) {
                if(queue.getFirst() == l){
                    queue.pollFirst();
                }
                sum -= runningCosts[l++];
            }
            res = Math.max(res,r-l+1);
            r++;
        }

        return res;
    }
}
