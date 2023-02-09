package contest.year2023.m2.d5;

import java.util.PriorityQueue;

public class No1 {
    /*public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x,y) -> y-x);
        int n = gifts.length,sum = 0;
        for (int i = 0; i < n; i++) {
            queue.add(gifts[i]);
            sum += gifts[i];
        }
        int c = 0,res = 0;
        while (c < k) {
            Integer tmp = queue.poll();
            int sqrt = (int) Math.sqrt(tmp);
            queue.add(sqrt);
            res += tmp - sqrt;
            c++;
        }
        return sum - res;
    }*/

    public long pickGifts(int[] gifts, int k){
        int n = gifts.length;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int max = 0,loc = 0;
            for (int j = 0; j < n; j++) {
                if (gifts[j] > max) {
                    max = gifts[j];
                    loc = j;
                }
            }
            int sqrt = (int) Math.sqrt(max);
            gifts[loc] = sqrt;
        }
        for (int i = 0; i < n; i++) {
            sum += gifts[i];
        }
        return sum;
    }
}
