package contest.year2022.m7.m7d23;

public class Rewrite {
    //t1
    public String bestHand(int[] ranks, char[] suits) {
        int n = ranks.length;
        boolean flag = true;
        char suit = suits[0];
        int[] bucket = new int[14];
        bucket[ranks[0]]++;
        for (int i = 1; i < n; i++) {
            bucket[ranks[i]]++;
            if(suit != suits[i])
                flag = false;
        }
        if(flag)
            return "Flush";

        int max = 0;
        for (int i = 0; i < 14; i++) {
            max = Math.max(max,bucket[i]);
        }
        return max == 3 ? "Three of a Kind" : max == 2 ? "Pair" : "High Card";
    }

    //t2
    /*long[] dp = new long[100001];
    {
        for (int i = 1; i < 100001; i++) {
            dp[i] = i + dp[i-1];
        }
    }
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length,l = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i == n-1 || nums[i] != 0) {
                res += dp[l];
                l = 0;
            }
            if(nums[i] == 0)
                l++;
        }
        return res;
    }*/

    //t3
    /*class NumberContainers {

        HashMap<Integer,Integer> valMap;
        HashMap<Integer, TreeSet<Integer>> locMap;
        public NumberContainers() {
            valMap = new HashMap<>();
            locMap = new HashMap<>();
        }

        public void change(int index, int number) {
            valMap.put(index,number); //更新下标Map
            TreeSet<Integer> treeSet = locMap.get(number);
            if (treeSet == null) {
                treeSet = new TreeSet<>();
                locMap.put(number, treeSet);
            }
            treeSet.add(index);
        }

        public int find(int number) {
            TreeSet<Integer> treeSet = locMap.get(number);
            if(treeSet == null)
                return -1;
            while (!treeSet.isEmpty()) {
                Integer loc = treeSet.first();
                if(valMap.get(loc) != number)
                    treeSet.remove(loc);
                else
                    return loc;
            }
            return -1;
        }
    }*/

    //t4
    //我明白了，这个脑筋急转弯不简单
    public int shortestSequence(int[] rolls, int k) {
        int n = rolls.length,res = 1;
        int[] set = new int[k+1];
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (set[rolls[i]] < res) {
                //set[rolls[i]]++;
                //使用赋值语句要比自增快1ms
                set[rolls[i]] = res;
                c++;
                //c等于k表示当前这轮全部收集完成了
                if (c == k) {
                    c = 0;
                    res++;
                }
            }
        }
        return res;
    }
}
