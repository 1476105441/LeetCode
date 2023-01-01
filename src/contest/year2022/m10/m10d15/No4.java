package contest.year2022.m10.m10d15;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    //有一个想法，深搜
    //想了很久，以失败告终
    /*public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        e = new ArrayList<>(n);
        set = new int[n];
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            e.get(edges[i][0]).add(edges[i][1]);
        }
        dfs(0,0,0,0);

        return res;
    }
    int res = 0,c;
    int[] nums,set;
    List<List<Integer>> e;
    public void dfs(int nowVal,int sum,int loc,int count){

        set[loc] = 1;
        c++;

        if (c == nums.length) {
            if(nums[loc] + sum == nowVal)
                res = Math.max(res,count);
        }else{
            List<Integer> list = e.get(loc);
            if (nowVal == 0 || nums[loc] + sum == nowVal) {
                //尝试删除
                int t = count,ts = sum;
                for (Integer i : list) {
                    if (set[loc] == 0) {
                        t++;
                        dfs(nums[loc]+ts,0,i,t);
                    }
                }
            }
            if(nums[loc] + sum <= nowVal){
                for (Integer i : list) {
                    if (set[loc] == 0) {
                        dfs(nowVal,sum+nums[loc],i,count);
                    }
                }
            }
        }

        c--;
        set[loc] = 0;
    }*/


    //记忆化搜索，还是会失败，why？
    /*List<List<Integer>> e;
    public int componentValue(int[] nums, int[][] edges){
        int n = nums.length,sum = 0,max = 0;
        e = new ArrayList<>(n);
        set = new boolean[n];
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
            max = Math.max(nums[i],max);
            sum += nums[i];
        }
        for (int i = 0; i < edges.length; i++) {
            e.get(edges[i][0]).add(edges[i][1]);
            e.get(edges[i][1]).add(edges[i][0]);
        }
        System.out.println(sum);
        int limit = sum / max;

        for (int i = limit; i > 0 ; i--) {
            if (sum % i == 0) {
                int res = dfs(sum/i, 0, nums);
                if(res == 0)
                    return i - 1;
            }
        }
        return 0;
    }
    boolean[] set;
    public int dfs(int val,int loc,int[] nums){
        List<Integer> list = e.get(loc);
        set[loc] = true;

        int sum = nums[loc];
        for (Integer i : list) {
            if(set[i])
                continue;
            int t = dfs(val, i, nums);
            if(t == -1)
                return -1;
            sum += t;
        }

        set[loc] = false;
        if(sum == val)
            return 0;
        else if(sum > val)
            return -1;
        else
            return sum;
    }*/

    //原来是细节没有处理好
    /*List<List<Integer>> e;
    public int componentValue(int[] nums, int[][] edges){
        int n = nums.length,sum = 0,max = 0;
        e = new ArrayList<>(n);
        set = new boolean[n];
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
            max = Math.max(nums[i],max);
            sum += nums[i];
        }
        for (int i = 0; i < edges.length; i++) {
            e.get(edges[i][0]).add(edges[i][1]);
            e.get(edges[i][1]).add(edges[i][0]);
        }
        //System.out.println(sum);
        int limit = sum / max;

        for (int i = limit; i > 0 ; i--) {
            if (sum % i == 0) {
                int res = dfs(sum/i, 0, nums);
                if(res == 0)
                    return i - 1;
            }
        }
        return 0;
    }
    boolean[] set;
    public int dfs(int val,int loc,int[] nums){
        List<Integer> list = e.get(loc);
        set[loc] = true;

        int sum = nums[loc];
        for (Integer i : list) {
            if(set[i])
                continue;
            int t = dfs(val, i, nums);
            if(t == -1){
                set[loc] = false;  //这里的细节，必须处理好
                return -1;
            }
            sum += t;
        }

        set[loc] = false;
        if(sum == val)
            return 0;
        else if(sum > val)
            return -1;
        else
            return sum;
    }*/


    //成功，63ms
    /*List<List<Integer>> e;
    public int componentValue(int[] nums, int[][] edges){
        int n = nums.length,sum = 0,max = 0;
        e = new ArrayList<>(n);
        set = new boolean[n];
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
            max = Math.max(nums[i],max);
            sum += nums[i];
        }
        for (int i = 0; i < edges.length; i++) {
            e.get(edges[i][0]).add(edges[i][1]);
            e.get(edges[i][1]).add(edges[i][0]);
        }
        //System.out.println(sum);
        int limit = sum / max;

        for (int i = limit; i > 0 ; i--) {
            if (sum % i == 0) {
                int res = dfs(sum/i, 0, nums,-1);
                if(res == 0)
                    return i - 1;
            }
        }
        return 0;
    }
    boolean[] set;
    public int dfs(int val,int loc,int[] nums,int pre){
        List<Integer> list = e.get(loc);
        //set[loc] = true;

        int sum = nums[loc];
        for (Integer i : list) {
            if(i == pre)
                continue;
            int t = dfs(val, i, nums,loc);
            if(t == -1)
                return -1;
            sum += t;
        }

        //set[loc] = false;
        if(sum == val)
            return 0;
        else if(sum > val)
            return -1;
        else
            return sum;
    }*/


    //重新捋一下思路
    //要划分为数值相同的连通块，并且要保证删除的边最多，也就
    //是说，连通块的数量要尽可能的多
    //解题思路：确定一个连通块的值，然后DFS搜索
    List<List<Integer>> e;
    boolean[] set;
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        e = new ArrayList<>(edges.length);
        set = new boolean[n];

        int sum = 0,max = 0;
        for (int i = 0; i < n; i++) {
            e.add(new ArrayList<>());
            sum += nums[i];
            max = Math.max(max,nums[i]);
        }
        for (int i = 0; i < edges.length; i++) {
            e.get(edges[i][0]).add(edges[i][1]);
            e.get(edges[i][1]).add(edges[i][0]);
        }

        //可能的最大连通块的数量
        int limit = sum / max;
        for (int i = limit; i > 0; i--) {
            if (sum % i == 0) {
                if(dfs(sum / i,0,nums) == 0)
                    return i-1;
            }
        }

        return 0;
    }
    public int dfs(int val,int loc,int[] nums){
        //去掉这行代码，执行速度提升了20ms
        //if(set[loc])
        //    return 0;

        set[loc] = true;
        List<Integer> list = e.get(loc);
        int sum = nums[loc];

        for (Integer i : list) {
            if(set[i])
                continue;
            int temp = dfs(val,i,nums);
            if (temp == -1) {
                set[loc] = false;
                return -1;
            }
            sum += temp;
        }

        set[loc] = false;

        if(sum > val)
            return -1;
        else if(sum == val)
            return 0;
        else
            return sum;
    }
}
