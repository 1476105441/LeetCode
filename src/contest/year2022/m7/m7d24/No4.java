package contest.year2022.m7.m7d24;

import java.util.HashSet;
import java.util.Set;

public class No4 {
    /**
     *      优质数对的数目
     */

    //失败版本
    /*int k;
    int[] dp;
    {
        dp = new int[256];
        dp[0] = 0;
        for (int i = 1; i < 256; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
    }
    public long countExcellentPairs(int[] nums, int k) {
        this.k = k;
        long res = 0;
        Set<Integer> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (Integer i : set) {
            for (Integer j : set) {
                StringBuilder sb = new StringBuilder();
                sb.append(i).append(',').append(j);
                if(!set2.contains(sb.toString())){
                    set2.add(sb.toString());
                    if (bitCount(i,j)){
                        if (!i.equals(j)) {
                            res += 2;
                            StringBuilder s = new StringBuilder();
                            s.append(j).append(',').append(i);
                            set2.add(s.toString());
                        }else
                            res++;
                    }
                }
            }
        }

        return res;
    }

    public boolean bitCount(int x,int y){
        int t1 = x&y,t2 = x|y;
        int c = 0;
        int tmp1,tmp2;
        while (t1 > 0 || t2 > 0) {
            tmp1 = t1 & 0xff;
            tmp2 = t2 & 0xff;
            c += dp[tmp1]+dp[tmp2];
            t1 = t1 >> 8;
            t2 = t2 >> 8;
        }
        return c >= k;
    }*/

    /*public boolean bitCount(int x,int y){
        int c = 0,t1 = x|y,t2 = x & y;
        int c1 = 0,c2 = 0;

        if(map.containsKey(t1)){
            c1 = map.get(t1);
            c2 = map.get(t2);
            return (c1+c2) >= k;
        }

        while (c < 32) {
            if((t1 & 1) == 1)
                c1++;
            if((t2&1) == 1)
                c2++;
            t1 = t1 >> 1;
            t2 = t2 >> 1;
            c++;
        }
        map.put(t1,c1);
        map.put(t2,c2);

        return (c1+c2) >= k;
    }*/

    //仍然超时
    /*int k;
    int[] dp;
    {
        dp = new int[256];
        dp[0] = 0;
        for (int i = 1; i < 256; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
    }
    public long countExcellentPairs(int[] nums, int k) {
        this.k = k;
        long res = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        Integer[] array = new Integer[set.size()];
        set.toArray(array);

        for (int i = 0; i < array.length; i++) {
            if(bitCount(array[i],array[i]))
                res++;
            for (int j = i+1; j < array.length; j++) {
                if(bitCount(array[i],array[j]))
                    res +=2 ;
            }
        }

        return res;
    }

    public boolean bitCount(int x,int y){
        int t1 = x&y,t2 = x|y;
        int c = 0;
        int tmp1,tmp2;
        while (t1 > 0 || t2 > 0) {
            tmp1 = t1 & 0xff;
            tmp2 = t2 & 0xff;
            c += dp[tmp1]+dp[tmp2];
            t1 = t1 >> 8;
            t2 = t2 >> 8;
        }
        return c >= k;
    }*/

    //仍然超时，转换了一下思维，x&y位为1的数量
    //加上x|y位为1的数量就等于x位为1的数量加上
    //y位为1的数量
    /*int[] dp;
    {
        dp = new int[256];
        dp[0] = 0;
        for (int i = 1; i < 256; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
    }
    public long countExcellentPairs(int[] nums, int k) {
        long res = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        Integer[] array = new Integer[set.size()];
        set.toArray(array);
        int[] count = new int[array.length];
        int n = array.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(count[j] == 0 && array[j] != 0)
                    count[j] = bitCount(array[j]);
                if (count[i] + count[j] >= k) {
                    if (i == j)
                        res++;
                    else
                        res += 2;
                }
            }
        }

        return res;
    }

    public int bitCount(int x){
        int c = 0,tmp1;

        while (x > 0) {
            tmp1 = x & 0xff;
            c += dp[tmp1];
            x = x >> 8;
        }

        return c;
    }*/


    //再做修改
    /*public long countExcellentPairs(int[] nums, int k) {
        long res = 0;
        Set<Integer> set = new HashSet<>();
        //map的value是位为1的个数等于key的元素的个数
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                int bc = bitCount(nums[i]);
                map.put(bc,map.getOrDefault(bc,0)+1);
            }
        }

        //由于key是位为1的个数，根据题目所给的要求，这个个数是不超过30
        //的，所以遍历的范围大大减小

        //每次寻找两个集合，由于一次只统计一种情况，所以双重遍历是可以的
        for (Map.Entry<Integer, Integer> x : map.entrySet()){
            for (Map.Entry<Integer, Integer> y : map.entrySet()) {
                //x的key和y的key就是位为1的个数
                if (x.getKey() + y.getKey() >= k) {
                    //把x和y看作一个集合，从x中任选一个元素和从y中
                    //任选一个元素，两者的组合个数就是所要加上的个数
                    res += (long) x.getValue() *y.getValue();
                }
            }
        }

        return res;
    }*/

    int[] dp;
    {
        dp = new int[256];
        dp[0] = 0;
        for (int i = 1; i < 256; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
    }
    public int bitCount(int x){
        int c = 0,tmp1;

        while (x > 0) {
            tmp1 = x & 0xff;
            c += dp[tmp1];
            x = x >> 8;
        }

        return c;
    }

    //再优化一下时间，没有成功
    /*public long countExcellentPairs(int[] nums, int k) {
        long res = 0;
        Set<Integer> set = new HashSet<>();
        //map的value是位为1的个数等于key的元素的个数
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                int bc = bitCount(nums[i]);
                map.put(bc,map.getOrDefault(bc,0)+1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Map.Entry<Integer,Integer>[] objects = new Map.Entry[entries.size()];
        entries.toArray(objects);

        for (int i = 0; i < objects.length; i++) {
            if((objects[i].getKey() << 1) >= k)
                res += (long)objects[i].getValue()*objects[i].getValue();
            for (int j = i+1; j < objects.length; j++) {
                if (objects[i].getKey() + objects[j].getKey() >= k) {
                    if((objects[i].getKey() +objects[j].getKey()) >= k)
                        res += (long)objects[i].getValue()*objects[j].getValue()*2;
                }
            }
        }

        return res;
    }*/

    //使用前缀和优化
    //前缀和是这么一个思想：我们统计当前序列中
    //位数和大于等于k的元素个数s，对于这么一个
    //个数，即使另一个数的位数为零也是可以满足
    //题目条件的，所以我们从位数为0的位置开始
    //遍历，遍历完0这个元素之后，位数为1的元素
    //所需要的最小位数就变成了x-1，即需要加上
    //x-1位才能满足题目条件
    public long countExcellentPairs(int[] nums,int k){
        int[] temp = new int[30];
        long res = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if(set.add(nums[i]))
                temp[bitCount(nums[i])]++;
        }

        int s = 0;
        for (int i = k; i < 30; i++) {
            s += temp[i];
        }

        for (int i = 0; i < 30; i++) {
            res += (long) temp[i] * s;
            int loc = k-1-i;
            //由于所需要的位数可能是大于30
            //的，因为题目所给的要求是单个
            //数字的位数不大于30
            if(loc >= 0 && loc < 30)
                s += temp[loc];
        }

        return res;
    }
}
