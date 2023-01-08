package contest.year2023.m1.d1;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    //1、先找到范围内的质数
    /*static List<Integer> list;
    static int size = 1000001;
    static{
        list = new ArrayList<>(1024*64);
        boolean[] flag = new boolean[size];
        for (int i = 2; i < size; i++) {
            if (!flag[i]) {
                list.add(i);
                for (Integer t : list) {
                    long loc = i * t;
                    if(loc < size)
                        flag[(int) loc] = true;
                }
            }
        }
    }*/

    //仍然超时
    /*public int[] closestPrimes(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if(isPrime(i))
                list.add(i);
        }
        int[] res = new int[]{-1,-1};
        int n = list.size(),dif = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (list.get(i) >= left && i + 1 < n && list.get(i + 1) <= right) {
                int newDif = list.get(i+1) - list.get(i);
                if (newDif < dif) {
                    dif = newDif;
                    res[0] = list.get(i);
                    res[1] = list.get(i+1);
                }
            }
        }
        return res;
    }

    public boolean isPrime(int val) {
        if(val == 2 || val == 3)
            return true;
        for (int i = 1; i < val; i++) {
            int x = 6 * i -1,y = 6 * i + 1;
            if(x > val)
                return false;
            if(x == val || y == val)
                return true;
        }
        return false;
    }*/

    public int[] closestPrimes(int left, int right) {
        int[] res = new int[]{-1,-1};
        int n = list.size(),dif = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (list.get(i) >= left && i + 1 < n && list.get(i + 1) <= right) {
                int newDif = list.get(i+1) - list.get(i);
                if (newDif < dif) {
                    dif = newDif;
                    res[0] = list.get(i);
                    res[1] = list.get(i+1);
                }
            }
        }
        return res;
    }
    static List<Integer> list;
    static int size = 1000001;
    static{
        list = new ArrayList<>(1024*64);
        boolean[] flag = new boolean[size];
        for (int i = 2; (long)i * i < size; i++) {
            if (!flag[i]) {
                list.add(i);
                int k = i;
                long loc;
                while ((loc = (long) i * k) < size) {
                    flag[(int) loc] = true;
                    k++;
                }
            }
        }
    }
}
