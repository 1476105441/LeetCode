package contest.year2022.m7.m7d17;

import java.util.*;

public class Rewrite {
    //t2
    public int maximumSum(int[] nums) {
        int n = nums.length,res = -1;
        //数组中第一个元素存放最大值，第二个元素存放次大值
        int[][] set = new int[100][2];
        for (int i = 0; i < n; i++) {
            int loc = countBit(nums[i]);
            if (nums[i] > set[loc][0]) {
                set[loc][1] = set[loc][0];
                set[loc][0] = nums[i];
            } else if(nums[i] > set[loc][1])
                set[loc][1] = nums[i];
        }
        for (int i = 0; i < 100; i++) {
            if(set[i][0] != 0 && set[i][1] != 0)
                res = Math.max(res,set[i][0]+set[i][1]);
        }
        return res;
    }
    public int countBit(int val){
        int res = 0;
        while (val > 0) {
            res += val % 10;
            val /= 10;
        }
        return res;
    }

    //t3，160ms，可以想一下怎么优化
    /*public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length,m = queries.length,l;
        int[] res = new int[m];
        char[][] chars = new char[n][];
        Map<char[],Integer> locs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            chars[i] = nums[i].toCharArray();
            locs.put(chars[i],i);
        }
        l = chars[0].length;

        //将queries排序，然后将nums排序
        int loc = 0;
        int[][] temp = new int[m][3];
        for (int i = 0; i < m; i++) {
            temp[i][0] = queries[i][0];
            temp[i][1] = queries[i][1];
            temp[i][2] = i;
        }
        Arrays.sort(temp,(x,y) -> x[1]-y[1]);
        for (int i = 0; i < l && loc < m; i++) {
            int j = i;
            //按照当前位进行排序
            Arrays.sort(chars,(x,y) -> {
                int l1 = l-j-1;
                if (x[l1] != y[l1]) {
                    return x[l1] - y[l1];
                } else {
                    boolean flag = true;
                    for (int k = l1; k < l; k++) {
                        if (x[k] != y[k]) {
                            flag = false;
                            break;
                        }
                    }
                    return flag ? locs.get(x) - locs.get(y) : x[l1] - y[l1];
                }
            });
            while (loc < m && temp[loc][1] == j + 1) {
                res[temp[loc][2]] = locs.get(chars[temp[loc][0]-1]);
                loc++;
            }
        }

        return res;
    }*/

    //156ms，并没有质的飞跃
    /*public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length,m = queries.length,l;
        int[] res = new int[m];
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            Node node = new Node();
            nodes[i] = node;
            node.chars = nums[i].toCharArray();
            node.loc = i;
        }
        l = nodes[0].chars.length;

        //将queries排序，然后将nums排序
        int loc = 0;
        int[][] temp = new int[m][3];
        for (int i = 0; i < m; i++) {
            temp[i][0] = queries[i][0];
            temp[i][1] = queries[i][1];
            temp[i][2] = i;
        }
        Arrays.sort(temp,(x,y) -> x[1]-y[1]);
        for (int i = 0; i < l && loc < m; i++) {
            int j = i;
            //按照当前位进行排序
            Arrays.sort(nodes,(x,y) -> {
                int l1 = l-j-1;
                if (x.chars[l1] != y.chars[l1]) {
                    return x.chars[l1] - y.chars[l1];
                } else {
                    boolean flag = true;
                    for (int k = l1; k < l; k++) {
                        if (x.chars[k] != y.chars[k]) {
                            flag = false;
                            break;
                        }
                    }
                    return flag ? x.loc - y.loc : x.chars[l1] - y.chars[l1];
                }
            });
            while (loc < m && temp[loc][1] == j + 1) {
                res[temp[loc][2]] = nodes[temp[loc][0]-1].loc;
                loc++;
            }
        }

        return res;
    }
    class Node{
        char[] chars;
        int loc;
    }*/
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length,m = queries.length,max = 0;
        int[] res = new int[m],locs = new int[n];
        char[][] chars = new char[n][];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            locs[i] = i;
            chars[i] = nums[i].toCharArray();
        }
        //建立查询位数和查询下标的关系
        for (int i = 0; i < m; i++) {
            List<Integer> list = map.get(queries[i][1]);
            //找出最大位数，后续使用
            max = Math.max(max,queries[i][1]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(queries[i][1],list);
            }
            list.add(i);
        }
        int l = chars[0].length;//每个字符串的长度
        for (int i = 1; i <= max; i++) {
            List<Integer>[] bucket = new List[10];
            for (int j = 0; j < n; j++) {
                int loc = chars[locs[j]][l-i] - '0';
                if(bucket[loc] == null)
                    bucket[loc] = new ArrayList<>();
                bucket[loc].add(locs[j]);
            }
            //按照大小顺序更新下标数组
            int c = 0;
            for (int j = 0; j < 10; j++) {
                if(bucket[j] != null)
                    for (Integer loc : bucket[j]) {
                        locs[c] = loc;
                        c++;
                    }
            }
            List<Integer> list = map.get(i);
            if(list != null)
                for (Integer j : list) {
                    res[j] = locs[queries[j][0] - 1];
                }
        }

        return res;
    }


    //t4
    //注意，这里的整除关系是指y%x == 0，要满足题目要求，需要numsDivide
    //中的数字全都是nums最小值的倍数才行
    public int minOperations(int[] nums, int[] numsDivide) {
        int res = 0,n = nums.length,m = numsDivide.length,min = numsDivide[0];
        for(int i = 1;i < m;i++){
            min = gcd(min,numsDivide[i]);
        }
        Arrays.sort(nums);
        for(int i = 0;i < n;i++){
            if(nums[i] > min)
                return -1;
            if(min % nums[i] == 0)
                return res;
            res++;
        }
        return -1;
    }
    int gcd(int x,int y){
        while(y != 0){
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }
}
