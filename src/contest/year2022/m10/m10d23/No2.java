package contest.year2022.m10.m10d23;

import java.util.*;

public class No2 {
    //没整出来，心态崩了
    /*public int subarrayGCD(int[] nums, int k) {
        int n = nums.length,res = 0,l = 0,r = 0,c = 0;
        int[] dp = new int[n];

        while (l < n) {
            if (nums[l] % k == 0) {
                Map<Integer,Integer> map = new HashMap<>();
                int loc1 = nums[l]/k;
                map.put(loc1,1);
                if(loc1 != 1)
                    c++;

                r = l + 1;
                while (r < n && nums[r] % k == 0) {
                    int loc = nums[r]/k,half = loc / 2,dou = loc * 2;
                    boolean flag = false;
                    if(loc != 1)
                        c++;
                    if ((loc & 1) == 0 && half != 1 && map.get(half) != null || (loc != 1 && (flag = map.get(dou)!=null))) {
                        res += (r-l)*(r-l+1)/2;
                        half = flag ? dou : half;
                        while (l < r && map.get(half) != null) {
                            if ((nums[l] % k) != 0) {
                                l++;
                                continue;
                            }
                            Integer val = map.get(nums[l]/k);
                            if(val == 1)
                                map.remove(l);
                            else
                                map.put(l,val-1);
                            l++;
                        }
                        res -= (r-l)*(r-l+1)/2;
                    }
                    Integer val = map.get(loc);
                    map.put(loc,val == null ? 1 : val+1);
                    r++;
                }
                res += (r-l)*(r-l+1)/2;
                l = r;
            }else
                l++;
        }

        return res-c;
    }*/


    //重新思考一下
    //思考一下：怎么判断当前子数组的最大公因数？
    //失败，理解错了题意
    /*public int subarrayGCD(int[] nums, int k){
        int n = nums.length,res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % k == 0) {
                boolean flag = false;
                if (nums[i] == k) {
                    flag = true;
                    res++;
                }
                Set<Integer> set = new HashSet<>();
                set.add(nums[i]);
                for (int j = i+1; j < n && nums[j] % k == 0; j++) {
                    if (nums[j] == k) {
                        flag = true;
                        res++;
                        set.add(nums[i]);
                    }
                    else if (!set.contains(nums[j]) && examine(set, nums[j],k)) {
                        if(flag)
                            res++;
                        set.add(nums[j]);
                    }
                    else
                        break;
                }
            }
        }

        return res;
    }
    public boolean examine(Set<Integer> set,int val,int k){
        for (Integer i : set) {
            if(i != k && (i % val == 0 || val % i == 0))
                return false;
        }
        return true;
    }*/


    //总结：首先，题意得要正确理解；其次，得要熟练掌握一些数学相关知识
    //就被卡在如何求一个数组的最大公约数上
    //不需要每两个单词进行求最大公约数
    /*public int subarrayGCD(int[] nums, int k){
        int n = nums.length,res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % k == 0) {
                if(nums[i] == k)
                    res++;
                int x = nums[i];
                for (int j = i+1; j < n && nums[j] % k == 0; j++) {
                    x = gcd(x,nums[j]);
                    if(x == k)
                        res++;
                }
            }
        }

        return res;
    }

    public int gcd(int x,int y){
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }*/


    //解法二：使用通用模板
    //失败，使用的模板不对
    /*public int subarrayGCD(int[] nums, int k){
        int n = nums.length,res = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] % k != 0) {
                Integer t = map.get(k);
                if(t != null)
                    res += t;
                map.clear();
            }else{
                Set<Integer> set = map.keySet();
                for (Integer t : set) {
                    int temp = gcd(t,nums[i]);
                    if (temp != t) {
                        map.put(temp,map.getOrDefault(temp,0)+map.get(t)+1);
                        map.remove(t);
                    }else{
                        map.put(t,map.get(t)+1);
                    }
                }
                Integer val = map.get(nums[i]);
                if (val == null) {
                    val = 0;
                }
                map.put(nums[i],val+1);
            }
        }

        return res;
    }*/


    //使用这种模板，是基于一种什么样的思想呢？
    //对于每一个可以整除k的数字，都有可能通过数组中后续的元素以达到
    //最大公因子为k，利用模板，维护最大公因子和最右边端点下标数对
    /*public int subarrayGCD(int[] nums, int k) {
        int n = nums.length, res = 0, index = -1;
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] % k != 0) {
                index = i;
                list = new ArrayList<>();
            } else {
                //第一个元素是gcd值，第二个元素是达到当前gcd的右端点
                list.add(new int[]{nums[i], i});
                int j = 0;
                for (int[] temp : list) {
                    temp[0] = gcd(temp[0], nums[i]);
                    if (list.get(j)[0] == temp[0]) {
                        //核心在这里，找到当前能满足的最大公因数最接近k的最右边端点下标
                        list.get(j)[1] = temp[1];
                    } else {
                        list.set(++j, temp);
                    }
                }

                list.subList(j + 1, list.size()).clear();
                if (list.get(0)[0] == k)
                    res += list.get(0)[1] - index;
            }
        }

        return res;
    }*/

    public int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    //解法三：动态规划
    public int subarrayGCD(int[] nums, int k){
        int n = nums.length,res = 0;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            if(nums[i] == k)
                res++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                dp[i][j] = gcd(dp[i][j-1],nums[j]);
                if(dp[i][j] / k == 0 || dp[i][j] % k > 0)
                    break;
                if(dp[i][j] == k)
                    res++;
            }
        }

        return res;
    }
}

class Solution {
    /*public int subarrayGCD(int[] nums, int k) {
        int res = 0;
        int len = nums.length;

        List<int[]> records = new ArrayList<>(); //[gcd, 相同gcd区间的右端点]
        int index0 = -1; //gcd 不为 k 的下标索引
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (num % k != 0) {
                index0 = i;
                records = new ArrayList<>();
            } else {
                records.add(new int[]{num, i});
                int j = 0; //原地去重
                for (int[] record : records) {

                    if (record[0] > num) record[0] = gcd(record[0], num);
                    else record[0] = gcd(num, record[0]);
                    if (records.get(j)[0] != record[0]) {
                        records.set(++j, record);
                    } else {
                        //相同gcd 更新右端点
                        records.set(j, record);
                    }
                }

                records.subList(j + 1, records.size()).clear();
                int[] first = records.get(0);
                if (first[0] == k) res += first[1] - index0;
            }
        }
        return res;
    }*/

    public int subarrayGCD(int[] nums, int k){
        int n = nums.length,res = 0,index = -1;
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] % k != 0) {
                index = i;
                list = new ArrayList<>();
            }else{
                list.add(new int[]{nums[i],i});
                int j = 0;
                for (int[] temp : list){
                    temp[0] = gcd(temp[0],nums[i]);
                    if(temp[0] == list.get(j)[0])
                        list.get(j)[1] = temp[1];
                    else
                        list.set(++j,temp);
                }
                list.subList(j+1,list.size()).clear();

                if(list.get(0)[0] == k)
                    res += list.get(0)[1] - index;
            }
        }

        return res;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}