package contest.year2022.m6.m6d26;

import java.util.ArrayList;
import java.util.List;

public class Rewrite {
    //t1
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i || j == n - 1 - i) {
                    if(grid[i][j] == 0)
                        return false;
                } else {
                    if(grid[i][j] != 0)
                        return false;
                }
            }
        }
        return true;
    }

    //t2
    public int countHousePlacements(int n) {
        long[][] dp = new long[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000007;
            dp[i][1] = dp[i-1][0];
        }
        long res = (dp[n-1][0] + dp[n-1][1])% 1000000007;
        res = (res * res) % 1000000007;

        return (int) res;
    }

    //t3
    //为什么这个解法不行呢？
    /*public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length,res,sum1 = 0,sum2 = 0,r = 1;
        for (int i = 0; i < n; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        res = Math.max(sum1,sum2);
        int temp1 = nums1[0],temp2 = nums2[0];
        int flag = nums1[0] - nums2[0];
        while (r < n) {
            int flag1 = nums1[r] - nums2[r];
            if (flag == 0) {
                flag = flag1;
                temp1 = 0;
                temp2 = 0;
            }
            if (flag > 0 && flag1 < 0 || flag < 0 && flag1 > 0) {
                int c = Math.abs(temp1 - temp2);
                if (flag > 0) {
                    res = Math.max(res,sum2 + c);
                } else {
                    res = Math.max(res,sum1 + c);
                }
                flag = flag1;
                temp1 = 0;
                temp2 = 0;
            }
            temp1 += nums1[r];
            temp2 += nums2[r];
            r++;
        }
        int c = Math.abs(temp1 - temp2);
        if (flag > 0) {
            res = Math.max(res,sum2 + c);
        } else {
            res = Math.max(res,sum1 + c);
        }

        return res;
    }*/

    //失败品
    /*public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length,res = 0,sum1 = 0,sum2 = 0,l = 0,r = 1;
        for (int i = 0; i < n; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
            System.out.println(nums1[i] > nums2[i]);
        }

        res = Math.max(sum1,sum2);
        int temp1 = nums1[l],temp2 = nums2[l];
        boolean flag = nums1[l] >= nums2[l]; //flag = true 代表第一个数组的元素比第二个数组的元素大，反之则不大
        while (r < n) {
            boolean flag1;
            if (nums1[r] == nums2[r]) {
                flag1 = flag;
            } else {
                flag1 = nums1[r] > nums2[r];
            }
            if (flag != flag1) {
                int c = Math.abs(temp1 - temp2);
                if (flag) {
                    res = Math.max(res,sum2 + c);
                } else {
                    res = Math.max(res,sum1 + c);
                }
                l = r;
                flag = flag1;
                temp1 = 0;
                temp2 = 0;
            }
            temp1 += nums1[r];
            temp2 += nums2[r];
            r++;
        }
        if (l < n) {
            int c = Math.abs(temp1 - temp2);
            if (flag) {
                res = Math.max(res,sum2 + c);
            } else {
                res = Math.max(res,sum1 + c);
            }
        }

        return res;
    }*/

    //转换思路，题目其实就是要求两数组的元素之差构成的数组的最大和
    /*public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int res = 0,n = nums1.length,sum1 = 0,sum2 = 0,m1 = 0,m2 = 0;
        int[] diff1 = new int[n],diff2 = new int[n];

        for (int i = 0; i < n; i++) {
            diff1[i] = nums1[i] - nums2[i];
            diff2[i] = nums2[i] - nums1[i];
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        int t1 = 0,t2 = 0;
        for (int i = 0; i < n; i++) {
            t1 = Math.max(t1+diff1[i],diff1[i]);
            t2 = Math.max(t2+diff2[i],diff2[i]);
            if(t1 > m1)
                m1 = t1;
            if(t2 > m2)
                m2 = t2;
        }
        res = Math.max(sum1,sum2);
        res = Math.max(res,sum1 + m2);
        res = Math.max(res,sum2 + m1);

        return res;
    }*/

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int res = 0,n = nums1.length,sum1 = 0,sum2 = 0,m1 = 0,m2 = 0;
        int[] diff1 = new int[n];

        for (int i = 0; i < n; i++) {
            diff1[i] = nums1[i] - nums2[i];
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        int t1 = 0,t2 = 0;
        for (int i = 0; i < n; i++) {
            t1 = Math.max(t1+diff1[i],diff1[i]);
            t2 = Math.max(t2-diff1[i],-diff1[i]);
            if(t1 > m1)
                m1 = t1;
            if(t2 > m2)
                m2 = t2;
        }
        res = Math.max(sum1,sum2);
        res = Math.max(res,sum1 + m2);
        res = Math.max(res,sum2 + m1);

        return res;
    }


    //t4
    //看了大佬的题解之后，稍微有了一些思路，使用时间戳+提前统计异或和的方式，58ms
    /*List<List<Integer>> edge;
    int[][] times; //time[0]存放的是一个节点的进入时间，time[1]存放的是一个节点的退出时间
    boolean[] set; //记忆化
    int[] vals,nums; //维护每个节点为根的树的异或值
    int time; //维护全局的时间戳，每到一个节点就进行增长
    *//*public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length,res = Integer.MAX_VALUE;
        edge = new ArrayList<>();
        times = new int[n][2];
        set = new boolean[n];
        vals = new int[n];
        this.nums = nums;
        time = 0;

        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        dfs(0);

        //开始遍历所有边，选取任意两个边作为要删除的边，这个操作的时间复杂度是O(n^2)
        for (int i = 0; i < n - 2; i++) {
            int x1 = edges[i][0],y1 = edges[i][1],min1,max1;
            for (int j = i+1; j < n - 1; j++) {
                int x2 = edges[j][0],y2 = edges[j][1],min2,max2;
                boolean flag;
                //选取出每个边中较小的节点
                if (times[x1][0] > times[y1][0]) {
                    max1 = y1;
                    min1 = x1;
                } else {
                    max1 = x1;
                    min1 = y1;
                }
                if (times[x2][0] > times[y2][0]) {
                    max2 = y2;
                    min2 = x2;
                } else {
                    max2 = x2;
                    min2 = y2;
                }

                int val1,val2,val3,max,min;
                if ((flag = times[min1][0] <= times[max2][0] && times[min1][1] >= times[max2][1])
                        || times[min2][0] <= times[max1][0] && times[min2][1] >= times[max1][1]) {

                    //flag = true 说明第一个边在上面
                    if (flag) {
                         val1 = vals[0] ^ vals[min1];
                         val2 = vals[min1] ^ vals[min2];
                         val3 = vals[min2];
                    } else {
                        val1 = vals[0] ^ vals[min2];
                        val2 = vals[min2] ^ vals[min1];
                        val3 = vals[min1];
                    }

                }else{
                    val1 = vals[min1];
                    val2 = vals[min2];
                    val3 = vals[0] ^ val1 ^ val2;
                }
                max = Math.max(val1,val2);
                max = Math.max(max,val3);
                min = Math.min(val1,val2);
                min = Math.min(min,val3);
                res = Math.min(res,max-min);

                //剪枝判断，但并没有起到太好的效果
                if(res == 0)
                    return 0;
            }
        }

        return res;
    }*//*

    public int dfs(int loc) {
        set[loc] = true;
        int val = nums[loc];
        times[loc][0] = time; //记录进入时间
        for (Integer next : edge.get(loc)) {
            if (!set[next]) {
                time++; //记录新节点，时间增加
                val = val ^ dfs(next);
            }
        }
        times[loc][1] = time; //记录离开时间
        vals[loc] = val;
        return val;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length,res = Integer.MAX_VALUE;
        edge = new ArrayList<>();
        times = new int[n][2];
        set = new boolean[n];
        vals = new int[n];
        this.nums = nums;
        time = 0;

        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            edge.get(x).add(y);
            edge.get(y).add(x);
        }
        dfs(0);

        //直接寻找要删除边的“上”节点，注意通过节点来删除的话，要取到每个节点
        for (int i = 1; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                int val1,val2,val3,max,min;
                if (times[i][0] < times[j][0] && times[i][1] >= times[j][0]) {
                    val1 = vals[j];
                    val2 = vals[i] ^ val1;
                    val3 = vals[0] ^ vals[i];
                } else if (times[i][0] > times[j][0] && times[i][0] <= times[j][1]) {
                    val1 = vals[i];
                    val2 = vals[j] ^ val1;
                    val3 = vals[0] ^ vals[j];
                } else {
                    val1 = vals[i];
                    val2 = vals[j];
                    val3 = vals[0] ^ val2 ^ val1;
                }
                max = Math.max(val1,val2);
                max = Math.max(max,val3);
                min = Math.min(val1,val2);
                min = Math.min(min,val3);
                res = Math.min(res,max-min);
                //剪枝判断，但并没有起到太好的效果
                if(res == 0)
                    return 0;
            }
        }

        return res;
    }*/

    List<Integer>[] edge;
    //使用两个数组分别存放进入时的时间戳和出去时的时间戳要
    //比用一个二维数组要快，我推测这是由于缓存行导致的
    int[] vals,nums,in,out;
    int time; //维护全局的时间戳，每到一个节点就进行增长
    public void dfs(int loc,int pre) {
        in[loc] = time; //记录进入时间
        vals[loc] = nums[loc];
        for (int next : edge[loc]) {
            if (next != pre) {
                time++; //记录新节点，时间增加
                dfs(next,loc);
                vals[loc] = vals[loc] ^ vals[next];
            }
        }
        out[loc] = time; //记录离开时间
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length,res = Integer.MAX_VALUE;
        edge = new List[n];
        in = new int[n];
        out = new int[n];
        vals = new int[n];
        this.nums = nums;
        time = 0;

        for (int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int x = edges[i][0],y = edges[i][1];
            edge[x].add(y);
            edge[y].add(x);
        }
        dfs(0,-1);

        //直接寻找要删除边的“上”节点，注意通过节点来删除的话，要取到每个节点
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int val1,val2,val3;
                if (in[i] < in[j] && out[i] >= in[j]) {
                    val1 = vals[j];
                    val2 = vals[i] ^ val1;
                    val3 = vals[0] ^ vals[i];
                } else if (in[i] > in[j]&& in[i] <= out[j]) {
                    val1 = vals[i];
                    val2 = vals[j] ^ val1;
                    val3 = vals[0] ^ vals[j];
                } else {
                    val1 = vals[i];
                    val2 = vals[j];
                    val3 = vals[0] ^ val2 ^ val1;
                }
                res = Math.min(res,Math.max(Math.max(val1,val2),val3)-Math.min(Math.min(val1,val2),val3));
                //剪枝判断，但并没有起到太好的效果
                if(res == 0)
                    return 0;
            }
        }

        return res;
    }
}
