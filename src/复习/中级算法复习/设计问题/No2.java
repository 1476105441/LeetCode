package 复习.中级算法复习.设计问题;

import java.util.*;

public class No2 {
    class RandomizedSet{
        //          常数时间插入、删除和获取随机元素

        Map<Integer,Integer> map;
        List<Integer> list;
        int count;
        Random random;
        public RandomizedSet() {
            map = new HashMap<>();
            list = new LinkedList<>();
            random = new Random();
            count = 0;
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }else{
                list.add(val);
                map.put(val,count);
                count++;
                return true;
            }
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int n = list.size();
            int loc = map.get(val),key = list.get(n-1);
            map.put(key,loc);
            list.set(loc,key);
            list.remove(n-1);
            map.remove(val);
            count--;
            return true;
        }

        public int getRandom() {
            int loc = random.nextInt(count);

            return list.get(loc);
        }
    }
}
