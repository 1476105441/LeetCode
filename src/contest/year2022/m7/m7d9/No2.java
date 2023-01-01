package contest.year2022.m7.m7d9;

import java.util.*;

public class No2 {
    /**
     *      坐上公交的最晚时间
     */

    //自己的想法，有点复杂，而且也有问题
    /*public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int m = buses.length,n = passengers.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
        }
        Arrays.sort(buses);
        Arrays.sort(passengers);
        for (int i = 0; i < n; i++) {
            int l = 0,r = m-1,loc = -1;
            while (l < r) {
                int c = l+((r-l)>>1);
                if (buses[c] > passengers[i]) {
                    loc = c;
                    r = c-1;
                } else if (buses[c] < passengers[i]) {
                    l = c+1;
                }else{
                    loc = c;
                    break;
                }
            }
            if (buses[l] >= passengers[i])
                loc = l;

            if(loc != -1)
                list.get(loc).add(passengers[i]);
            else
                break;
        }

        int pre = 0,max = -1,last = -1;
        for (int i = 0; i < m; i++) {
            int length = list.get(i).size();
            if (capacity > pre) {
                int loc = capacity - pre -1,temp;
                if(loc >= length)
                    loc = length-1;
                temp = list.get(i).get(loc)-1;
                if (loc == 0&&temp!=last) {
                    max = temp;
                } else if (loc > 0 && temp != list.get(i).get(loc - 1)) {
                    max = temp;
                }
            }

            last = list.get(i).get(length-1);
            pre = length+pre-capacity;
            if (pre < 0) {
                pre = 0;
            }
        }
        return max;
    }*/

    //双指针
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity){
        int m = buses.length,n = passengers.length,res = 1,cnt = 0;
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int j = 0;
        //使用set是为了判断是否有乘客在buses[i]开出时到达
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(passengers[i]);
        }
        for (int i = 0; i < m; i++) {
            while (cnt < capacity && j < n && passengers[j] <= buses[i]) {
                //只有第一个到达的乘客和不连续到达的乘客之间可以插
                //入，也就是插入到当前乘客的前一个时间点
                if(j == 0 || passengers[j-1] != passengers[j]-1)
                    res = passengers[j]-1;
                j++;
                cnt++;
            }
            if(cnt < capacity && !set.contains(buses[i]))
                res = buses[i];
            cnt = 0;
        }
        return res;
    }
}
