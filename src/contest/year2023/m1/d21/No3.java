package contest.year2023.m1.d21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class No3 {
    /*public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        List<int[]> list = new ArrayList<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            list.add(new int[]{nums1[i],nums2[i]});
        }
        list.sort((x,y) -> {
            if (x[1] != y[1]) {
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });
        //PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> x[0] - y[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = n-1,c = 0;
        long sum = 0,min = Integer.MAX_VALUE;
        for (;index > -1; index--) {
            int[] ints = list.get(index);
            if (c < k) {
                queue.add(ints[0]);
                sum += ints[0];
                min = Math.min(min, ints[1]);
                if (c == k-1) {
                    res = sum * min;
                }
                c++;
            } else {
                int peek = queue.peek();
                if (peek >= ints[0]) {
                    continue;
                }
                long temp = (sum - peek + ints[0]) * ints[1];
                if (temp > res) {
                    res = temp;
                    sum = sum - peek + ints[0];
                    min = ints[1];
                    queue.poll();
                    //queue.remove(peek);
                    queue.add(ints[0]);
                }
            }
        }
        return res;
    }*/


    //结束了之后才通过的，不知道和上面那个有什么区别
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        List<int[]> list = new ArrayList<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            list.add(new int[]{nums1[i],nums2[i]});
        }
        list.sort((x,y) -> {
            if (x[1] != y[1]) {
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });
        //PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> x[0] - y[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = n-1,c = 0;
        long sum = 0,min = Integer.MAX_VALUE;
        for (;index > -1; index--) {
            int[] ints = list.get(index);
            if (c < k) {
                queue.add(ints[0]);
                sum += ints[0];
                min = Math.min(min, ints[1]);
                if (c == k-1) {
                    res = sum * min;
                }
                c++;
            } else {
                int peek = queue.poll();
                sum -= peek;
                sum += ints[0];
                long temp = sum * ints[1];
                if (temp > res) {
                    res = temp;
                }
                queue.add(ints[0]);
            }
        }
        return res;
    }
}
