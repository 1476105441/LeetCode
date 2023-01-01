package middle.设计问题;

import java.util.*;

public class No2 {
    //              常数时间插入、删除和获取随机元素
    //实现RandomizedSet 类：
    //    RandomizedSet() 初始化 RandomizedSet 对象
    //    bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
    //    bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
    //    int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
    //你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。

    //常数时间内完成这些方法，与查重有关的，可以用哈希表？
}
class RandomizedSet {

    //自己写的速度很慢的解法
    /*Set<Integer> set;
    List<Integer> nums;
    int count;

    public RandomizedSet() {
        set = new HashSet<>();
        nums = new LinkedList<>();
        count = 0;
    }

    public boolean insert(int val) {
        if (set.contains(val)) {
            return false;
        }else{
            set.add(val);
            nums.add(val);
            count++;
            return true;
        }
    }

    public boolean remove(int val) {
        if (set.remove(val)) {
            count--;
            Integer i = val;
            nums.remove(i);
            return true;
        }else{
            return false;
        }
    }

    public int getRandom() {
        Random random = new Random();
        int num = random.nextInt(count);
        return nums.get(num);
    }*/

    //为了能够快速定位，使用map来存储键值对，key是存入的数据，而value是对应数组中的下标
    //相当于一个索引
    Map<Integer,Integer> map;
    int count;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        count = 0;
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }else{
            map.put(val,count);
            list.add(val);
            count++;
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }else{
            int n = list.size();
            //由于删除的元素可能就是list集合中的最后一个元素
            //int loc = map.remove(val);
            int loc = map.get(val);
            int value = list.get(n-1);
            list.set(loc,value);
            list.remove(n-1);
            count--;
            map.put(value,loc);
            map.remove(val);
            /*if (count != 0 && value != val) {
                map.put(value,loc);
            }*/
            return true;
        }
    }

    public int getRandom() {
        int num = random.nextInt(count);
        return list.get(num);
    }
}
