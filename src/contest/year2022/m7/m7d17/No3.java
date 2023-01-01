package contest.year2022.m7.m7d17;

import java.util.*;

public class No3 {
    /*public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length,m = queries.length;
        long[] val = new long[n];
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            val[i] = parse(nums[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            Integer[] temp = new Integer[n];
            long mod = (long) Math.pow(10,queries[i][1]);
            for (int j = 0; j < n; j++) {
                temp[j] = j;
            }
            Arrays.sort(temp,(x,y)->{
                if(val[x] % mod == val[y] % mod)
                    return x - y;
                else
                    return Long.compare(val[x] % mod, val[y] % mod);
            });
            res[i] = temp[queries[i][0]-1];
        }
        return res;
    }
    public long parse(String num){
        char[] chars = num.toCharArray();
        int n = chars.length;
        long res = 0,bit = 1;
        for (int i = n-1; i > -1 ; i--) {
            int temp = chars[i] - '0';
            res += temp * bit;
            bit *= 10;
        }
        return res;
    }*/

    /*public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length,m = queries.length,l;
        char[][] val = new char[n][];
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            val[i] = nums[i].toCharArray();
        }
        l = val[0].length;
        for (int i = 0; i < m; i++) {
            Integer[] temp = new Integer[n];
            for (int j = 0; j < n; j++) {
                temp[j] = j;
            }
            int loc = Math.max(l - queries[i][1],0);
            Arrays.sort(temp,(x,y)->{
                int t = loc;
                char[] xc = val[x],yc = val[y];
                while(t < l && xc[t] == yc[t])
                    t++;
                if(t == l)
                    return x - y;
                else
                    return xc[t] - yc[t];
            });
            res[i] = temp[queries[i][0]-1];
        }
        return res;
    }*/

    //使用基数排序
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries){
        int n = nums.length,m = queries.length;
        char[][] chars = new char[n][];

        int[] rank = new int[n]; //rank数组中存放的是当前排在该位置的元素下标
        for (int i = 0; i < n; i++) {
            chars[i] = nums[i].toCharArray();
            rank[i] = i;
        }

        int max = 0,l = chars[0].length;
        //存放按照key位排列的queries的下标
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            max = Math.max(max,queries[i][1]);
            List<Integer> list = map.get(queries[i][1]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(queries[i][1],list);
            }
            list.add(i);
        }

        max = Math.min(max,l); //不能超出长度上限
        int[] res = new int[m];
        for (int i = 1; i <= max; i++) {
            //1、遍历数组，找出当前顺序下按照新的位排序的顺序，放入桶中
            List<Integer>[] bucket = new List[10]; //每次基数排序中使用桶排序
            for (int j = 0; j < n; j++) {
                int loc = chars[rank[j]][l-i] - '0';
                if(bucket[loc] == null)
                    bucket[loc] = new ArrayList<>();
                bucket[loc].add(rank[j]);
            }
            //2、遍历桶，将桶中的元素按照大小放入到原数组中
            int c = 0;
            for (int j = 0; j < 10; j++) {
                if(bucket[j] != null)
                    for (Integer loc : bucket[j]) {
                        rank[c] = loc;
                        c++;
                    }
            }
            List<Integer> list = map.get(i);
            if(list != null)
                for (Integer loc : list)
                    res[loc] = rank[queries[loc][0]-1];
        }
        return res;
    }
}
