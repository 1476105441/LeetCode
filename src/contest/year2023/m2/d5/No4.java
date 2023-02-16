package contest.year2023.m2.d5;

import java.util.*;

public class No4 {
    //成功，44ms
    //最近一段时间自己写出来的困难题！
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        Map<Integer,Integer> map1 = new HashMap<>(256),map2 = new HashMap<>(256);
        Set<Integer> set = new HashSet<>();
        //统计两个数组中元素的个数
        for (int i = 0; i < n; i++) {
            map1.put(basket1[i],map1.getOrDefault(basket1[i],0)+1);
            map2.put(basket2[i],map2.getOrDefault(basket2[i],0)+1);
            //统计数组究竟出现了哪些元素
            set.add(basket1[i]);
            set.add(basket2[i]);
        }
        long min = 1000000000;
        List<Integer> list1 = new ArrayList<>(),list2 = new ArrayList<>();
        for (Integer num : set) {
            Integer v1 = map1.get(num),v2 = map2.get(num);
            if (v1 == null) {
                v1 = 0;
            }
            if (v2 == null) {
                v2 = 0;
            }
            int count = v1 + v2;
            if ((count & 1) == 1) {
                return -1;
            }
            if (v1 > v2) {
                int temp = (v1 - v2) >> 1;
                for (int i = 0; i < temp; i++) {
                    list1.add(num);
                }
            } else if (v1 < v2) {
                int temp = (v2 - v1) >> 1;
                for (int i = 0; i < temp; i++) {
                    list2.add(num);
                }
            } else {
                min = Math.min(min,num);
            }
        }
        Collections.sort(list1);
        Collections.sort(list2);
        int l1 = 0, l2 = 0, r1 = list1.size() - 1, r2 = r1, c = r1 + 1;
        long res = 0;
        while (c > 0) {
            Integer lv1 = list1.get(l1),lv2 = list2.get(l2);
            if (lv1 < lv2) {
                res += Math.min(min << 1,lv1);
                min = Math.min(min,lv1);
                l1++;
                r2--;
            } else {
                res += Math.min(min << 1,lv2);
                min = Math.min(min,lv2);
                l2++;
                r1--;
            }
            c--;
        }
        return res;
    }

}
