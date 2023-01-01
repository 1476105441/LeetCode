package contest.year2022.m8.m8d20;

public class No4 {
    /**
     *      删除操作后的最大子段和
     */

    //要计算序列i-j的和
    //果然超时
    /*public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        long sum = 0;
        long[] dp = new long[n],res = new long[n];
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{0,n-1});

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            dp[i] = sum;
        }

        for (int i = 0; i < n; i++) {
            int[] temp = null;
            for (int[] ints : set) {
                if (removeQueries[i] >= ints[0] && removeQueries[i] <= ints[1]) {
                    temp = ints;
                    set.remove(ints);
                    break;
                }
            }
            if (removeQueries[i] == temp[0]) {
                if(removeQueries[i] != temp[1])
                    set.add(new int[]{temp[0]+1,temp[1]});
            } else if (removeQueries[i] == temp[1]) {
                set.add(new int[]{temp[0],temp[1]-1});
            } else{
                set.add(new int[]{temp[0],removeQueries[i]-1});
                set.add(new int[]{removeQueries[i]+1,temp[1]});
            }

            long max = 0;
            for (int[] ints : set) {
                if (ints[0] > 0) {
                    max = Math.max(max,dp[ints[1]] - dp[ints[0]-1]);
                }else
                    max = Math.max(max,dp[ints[1]]);
            }
            res[i] = max;
        }

        return res;
    }*/

    //逆向思维+并查集
    /*int[] parent;
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        parent = new int[n+1];
        long[] res = new long[n],sum = new long[n+1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = n-1; i > 0; i--) {
            int x = removeQueries[i],y = findParent(x+1);
            parent[x] = y;
            sum[y] += sum[x] + nums[x];
            res[i-1] = Math.max(res[i],sum[y]);
        }

        return res;
    }

    public int findParent(int i){
        if (parent[i] != i) {
            return findParent(parent[i]);
        }
        return i;
    }*/

    //重新写一遍
    int[] parent;
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries){
        int n = nums.length;
        parent = new int[n+1];
        long[] sum = new long[n+1],res = new long[n];
        for (int i = 0; i < n+1; i++) {
            parent[i] = i;
        }

        for (int i = n-2; i > 0; i--) {
            int x = removeQueries[i],y = find(x+1);
            parent[x] = y;
            sum[y] += sum[x]+nums[x];
            res[i] = Math.max(sum[y],res[i-1]);
        }

        return res;
    }
    public int find(int i){
        if(parent[i] != i)
            return find(parent[i]);
        return i;
    }
}
