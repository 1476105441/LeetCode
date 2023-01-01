package contest.year2022.m7.m7d9;

import java.util.*;

public class Rewrite {

    //t1
    /*public boolean evaluateTree(TreeNode root) {
        return dfs(root);
    }
    public boolean dfs(TreeNode node){
        if(node.val < 2)
            return node.val == 1;

        boolean l ,r ;
        l = dfs(node.left);
        r = dfs(node.right);

        if(node.val == 2)
            return l | r;
        else
            return l & r;
    }*/


    //t2
    /*public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int res = 0,m = buses.length,n = passengers.length;
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int l = 0,r = 0,c = 0;
        Set<Integer> set = new HashSet<>();

        while (l < m && r < n) {
            if (c == capacity || passengers[r] > buses[l]) {
                if (c != capacity && !set.contains(buses[l])) {
                    res = buses[l];
                }
                l++;
                c = 0;
            } else{
                if (!set.contains(passengers[r] - 1)) {
                    res = passengers[r] - 1;
                }
                set.add(passengers[r]);
                c++;
                r++;
            }
        }
        //判断特殊情况，有可能乘客先到最后了，然后容量也满了，所以要先移动到下一个大巴车
        if(c == capacity)
            l++;
        //如果当前的大巴还没有全部出发，直接设置为最后一个
        if (l < m && !set.contains(buses[m-1]))
            res = buses[m-1];

        return res;
    }*/

    //想一下该怎么优化？
    //直接换解法，采用分配法，先将所有的乘客分配到对应的大巴里，然后从可分配的最后一个乘客开始，往前找空隙
    /*public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int res = 0,m = buses.length,n = passengers.length;
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int j = 0,c = 0;
        for (int i = 0; i < m; i++) {
            c = capacity;
            for (;c > 0 && j < n && passengers[j] <= buses[i]; j++) {
                c--;
            }
        }
        j--;
        res = c > 0 ? buses[m-1] : passengers[j];

        while(j >= 0 && passengers[j--] == res) res--;

        return res;
    }*/


    //t3
    //首先，有一个特性需要证明出来，不然没法往下写，那就是：
    //让差值大的值减小对结果的影响更大，比如有两个差值：4,11
    //应该选择让4减一还是让11减一？
    // 4-1 减少的数量：7
    // 11-1 减少的数量：21

    //太恶心了，非常需要注意细节的一题
    /*public static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length,sum = k1 + k2;
        long res = 0,count = 0;
        long[] diff = new long[n];

        //1、计算出差值数组
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            res = res + diff[i] * diff[i];
            count += diff[i];
        }

        if(sum > count)
            return 0;
        if(sum == 0)
            return res;
        //2、将差值排序，并且平均减少
        Arrays.sort(diff);
        long pre = diff[n-1],dis = 1; //先排序啊大哥
        int i;
        res -= diff[n-1] * diff[n-1];
        for (i = n-2; i > -1 && sum >= 0; i--) {
            if (pre != diff[i]) {
                long temp = pre - diff[i];
                if (temp * dis <= sum) {
                    sum -= temp * dis;
                }else {
                    int mod = dis == 1 ? sum : (int) (sum % dis); //mod是有多少个元素需要多减一次
                    pre = pre - sum / dis;
                    res += (pre-1) * (pre-1) * mod + pre * pre * (dis - mod);
                    sum = 0;
                    break;
                }
            }
            pre = diff[i];
            res -= diff[i] * diff[i];
            dis++;
        }
        if (sum > 0) {
            int mod = dis == 1 ? sum : (int) (sum % dis);
            pre = pre - sum / dis;
            res += (pre-1) * (pre-1) * mod + pre * pre * (dis - mod);
        }

        return res;
    }*/

    //重新写一遍
    //遇到的坑：不转换成long类型做乘法的话会溢出
    /*public static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length,total = k1 + k2,dis = 1,pre;
        long res = 0,sum = 0;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            res += (long) diff[i] * diff[i];
//            sum += diff[i];
        }
        if(total == 0)
            return res;
//        if(total >= sum)
//            return 0;

        Arrays.sort(diff);
        pre = diff[n-1];
        boolean flag = true;
        for (int i = n-2; i >= -1 && flag; i--) {
            int now = i == -1 ? 0 : diff[i];
            if (pre != now) {
                int num = pre - now; //前后差值
                //不够减的情况
                if (num * dis > total) {
                    int mod = total % dis; //算出有多少个元素是要多减1的
                    pre -= total / dis;
                    res += (long) (pre - 1) * (pre-1) * mod + (long) pre * pre * (dis-mod);
                    flag = false;
                }
                total -= num * dis;
            }
            pre = now;
            res -= (long) diff[i + 1] * diff[i+1]; //把前一个元素的值去掉
            dis++;
        }

        return res;
    }*/

    //统一使用long类型，好像效率反而变慢了，说好像是因为并没有严谨的多次测量
    /*public static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length,total = k1 + k2,dis = 1;
        long res = 0,pre;
        long[] diff = new long[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            res += diff[i] * diff[i];
        }
        if(total == 0)
            return res;

        Arrays.sort(diff);
        pre = diff[n-1];
        boolean flag = true;
        for (int i = n-2; i >= -1 && flag; i--) {
            long now = i == -1 ? 0 : diff[i];
            if (pre != now) {
                long num = pre - now; //前后差值
                //不够减的情况
                if (num * dis > total) {
                    int mod = total % dis; //算出有多少个元素是要多减1的
                    pre -= total / dis;
                    res += (pre - 1) * (pre-1) * mod + pre * pre * (dis-mod);
                    flag = false;
                }
                total -= num * dis;
            }
            pre = now;
            res -= diff[i + 1] * diff[i+1]; //把前一个元素的值去掉
            dis++;
        }

        return res;
    }*/

    //第二种解法：二分查找，计算减到某个值所需要的数量，17ms，有一些细节还是需要注意的，不然也会被卡很久
    /*public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2){
        int n = nums1.length,total = k1 + k2;
        long res = 0,sum = 0;
        long[] diff = new long[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }
        if(total >= sum)
            return 0;
        if (total == 0) {
            for (int i = 0; i < n; i++) {
                res += diff[i] * diff[i];
            }
            return res;
        }

        Arrays.sort(diff);

        int l = 0, r = (int) diff[n-1],target = r;
        while (l <= r) {
            int c = l + ((r-l)>>1);
            int flag = check(diff, c, total);
            if (flag > 0) {
                l = c+1;
            } else{
                r = c-1;
                target = c;
            }
        }
        int i;
        for ( i = diff.length - 1; i > -1; i--) {
            if(diff[i] <= target)
                break;
            total -= diff[i] - target;
            diff[i] = target;
        }
        i = n-1;
        while (total > 0 && i > -1) {
            if (diff[i] == target) {
                total--;
                diff[i]--;
            }
            i--;
        }
        for (int j = 0; j < n; j++) {
            res += diff[j] * diff[j];
        }

        return res;
    }
    //返回值 > 0说明当前要减少到val需要的数量大于total
    public int check(long[] diff,int val,int total){
        int sum = 0;
        for (int i = diff.length-1; i > -1 ; i--) {
            if(diff[i] <= val)
                break;
            sum += diff[i] - val;
        }
        return sum - total;
    }*/

    //二分再写一遍
    /*public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2){
        int n = nums1.length,total = k1+k2;
        long[] diff = new long[n];
        long res = 0,sum = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }
        if(total >= sum)
            return 0;
        if (total == 0) {
            for (int i = 0; i < n; i++) {
                res += diff[i] * diff[i];
            }
            return res;
        }
        Arrays.sort(diff);
        int l = 0,r = (int) diff[n-1],target = r;
        while (l <= r) {
            int c = l + ((r-l)>>1);
            if (check(diff, c, total)) {
                //进入到这里，说明当前的c符合要求，继续减小试试
                target = c;
                r = c-1;
            } else{
                l = c+1;
            }
        }
        l = n-1;
        for (; l > -1; l--) {
            if(diff[l] <= target)
                break;
            total -= diff[l]-target;
            diff[l] = target;
        }
        l = n-1;
        while (total > 0 && l > -1) {
            if (diff[l] > 0) {
                total--;
                diff[l]--;
            }
            l--;
        }
        for (int i = 0; i < n; i++) {
            res += diff[i] * diff[i];
        }
        return res;
    }

    public boolean check(long[] diff, int val, int total) {
        long temp = 0;
        for (int i = diff.length-1; i > -1 ; i--) {
            if(diff[i] <= val)
                break;
            temp += diff[i] - val;
        }
        return temp <= total;
    }*/


    //t4
    //滑动窗口+单调栈，失败，倒数第二个用例过不了，我也找不出来原因是什么
    /*public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int l = 0,r = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        while (r < n) {
            //首先将单调栈中比当前元素大的出栈
            while(!queue.isEmpty() && nums[queue.getLast()] > nums[r])
                queue.pollLast();
            //判断当前元素是否有可能存在的符合条件的子数组
            int temp = threshold / nums[r] + 1;
            if (n - l < temp) {
                //加上当前右边界的元素之后不可能达到满足的子数组
                //将单调栈中剩余的元素出栈，去掉最小值后查看是否满足条件
                while (!queue.isEmpty()) {
                    l = queue.pollFirst()+1;
                    temp = threshold / (r-l);
                    if(temp < nums[queue.getFirst()])
                        return r-l;
                }
                l = r+1;
            } else {
                queue.offerLast(r);
            }
            r++;
            if (r > l) {
                temp = threshold / (r-l);
                if(temp < nums[queue.getFirst()])
                    return r-l;
            }
        }

        return -1;
    }*/

    //解法一：单调栈
    //和《柱状图中最大的矩形》这题基本一模一样
    //计算每个元素，以自身为最小元素的左右边界
    //成功，26ms
    /*public int validSubarraySize(int[] nums, int threshold){
        int n = nums.length;
        int[] left = new int[n],right = new int[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] > nums[i]) {
                int loc = queue.pollLast();
                right[loc] = i;
            }
            //判断单调栈中是否还有比当前元素更小的元素
            left[i] = queue.isEmpty() ? -1 : queue.getLast();
            queue.offerLast(i);
        }
        while (!queue.isEmpty()) {
            right[queue.pollLast()] = n;
        }
        for (int i = 0; i < n; i++) {
            int len = right[i]-left[i]-1,temp = threshold / len;
            if(temp < nums[i])
                return len;
        }
        return -1;
    }*/

    //解法二：并查集
    //怎么使用并查集解决这个问题？
    //先将数组按照从大到小进行排序，然后也按照这个顺将元素和它原来顺
    //序的下一个元素加到并查集里
    int[] parent,size;
    public int validSubarraySize(int[] nums, int threshold){
        int n = nums.length;
        parent = new int[n+1];
        size = new int[n+1];
        Integer[] temp = new Integer[n];
        //temp中存储的是下标
        for (int i = 0; i < n; i++) {
            temp[i] = i;
            parent[i] = i;
            size[i] = 1;
        }
        parent[n] = n;
        size[n] = 1;
        //将temp数组按照从大到小顺序排序
        Arrays.sort(temp,(x,y) -> nums[y] - nums[x]);
        for (int i = 0; i < n; i++) {
            int loc = temp[i];
            //找到父节点，并将两部分合并
            int x = find(loc),y = find(loc+1);
            parent[y] = parent[x];
            size[x] += size[y];
            //链条中肯定是有一个还没有加入的
            if(threshold / (size[x]-1) < nums[loc])
                return size[x]-1;
        }
        return -1;
    }
    public int find(int i){
        if(parent[i] != i)
            parent[i] = find(parent[i]); //进行路径压缩
        return parent[i];
    }
}
