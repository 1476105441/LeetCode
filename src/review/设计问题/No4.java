package review.设计问题;

import java.util.*;

public class No4 {
    /**
     *      常数时间插入、删除和获取随机元素
     */

    class RandomizedSet {
        Map<Integer,Integer> map;
        List<Integer> list;
        Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if(map.containsKey(val))
                return false;

            list.add(val);
            map.put(val,list.size()-1);

            return true;
        }

        public boolean remove(int val) {
            Integer loc = map.get(val);
            if(loc == null) {
                return false;
            }

            Integer i = list.get(list.size() - 1);
            map.put(i,loc);
            list.set(loc,i);
            list.remove(list.size()-1);
            map.remove(val);

            return true;
        }

        public int getRandom() {
            int size = random.nextInt(list.size());

            return list.get(size);
        }
    }
}
