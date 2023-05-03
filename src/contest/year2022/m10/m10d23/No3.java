package contest.year2022.m10.m10d23;

import java.util.Arrays;

public class No3 {
    /*public long minCost(int[] nums, int[] cost) {
        int n = nums.length,min = Integer.MAX_VALUE,max = 0;
        for (int i = 0; i < n; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        int dis = max - min;
        long[] dp = new long[dis + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= dis; j++) {
                dp[j] += (long)Math.abs(dis+min-nums[i]) * cost[i];
            }
        }

        long res = Integer.MAX_VALUE;
        for (int i = 0; i <= dis; i++) {
            res = Math.min(res,dp[i]);
        }

        return res;
    }*/


    //怎么求最小开销，决定性因素是什么?
    //开销大的元素应该尽可能的少修改，开销小的元素可以修改的多一些
    /*public long minCost(int[] nums, int[] cost){
        int n = nums.length;
        long res = 0;

        for (int i = 0; i < n; i++) {

        }

        return res;
    }*/


    //不会写，看题解
    //超时了，难道问题出在排序算法上？经过控制变量对比，还真是排序算法的问题
    public long minCost1(int[] nums, int[] cost){
        int n = nums.length;
        long res = 0,total = 0,sumCost,sum = 0;

        //1、先排序，注意：使用快速排序的实现会超时，而堆排序的实现则不会
        //quickShift(nums,cost,0,n-1);
        dumpSort(nums,cost);

        //2、再统计其余所有元素变成nums[0]的总开销total和所有的开销和sumCost
        sumCost = cost[0];
        for (int i = 1; i < n; i++) {
            sumCost = sumCost + cost[i];
            total += (long) (nums[i] - nums[0]) * cost[i];
        }
        res = total;

        for (int i = 1; i < n; i++) {
            //计算需要增加的开销和
            sum += cost[i-1];

            total -= (sumCost-2*sum) * (nums[i] - nums[i-1]);
            res = Math.min(res,total);
        }

        return res;
    }
    //快速排序会超时
    /*public void quickShift(int[] nums,int[] cost,int l,int r){
        if(l >= r)
            return;
        int i = l,j = r;
        while (i < j) {
            while (i < j && nums[i] <= nums[j])
                j--;
            if (i < j) {
                swap(nums,i,j);
                swap(cost,i,j);
                i++;
            }
            while(i < j && nums[i] <= nums[j])
                i++;
            if (i < j) {
                swap(nums,i,j);
                swap(cost,i,j);
                j--;
            }
        }
        quickShift(nums,cost,l,i-1);
        quickShift(nums,cost,i+1,r);
    }*/
    public void swap(int[] temp,int i,int j){
        int t = temp[i];
        temp[i] = temp[j];
        temp[j] = t;
    }
    //堆排序，成功
    public void dumpSort(int[] nums,int[] cost){
        int n = nums.length;
        int[] temp1 = new int[n],temp2 = new int[n];
        for (int i = 0; i < n; i++) {
            temp1[i] = nums[i];
            temp2[i] = cost[i];
            update(temp1,temp2,i);
        }
        for (int i = n-1; i > -1 ; i--) {
            nums[i] = temp1[0];
            cost[i] = temp2[0];
            temp1[0] = temp1[i];
            temp2[0] = temp2[i];
            shift(temp1,temp2,0,i);
        }
    }
    public void update(int[] nums,int[] cost,int loc){
        int i = loc,p = (i-1) >> 1;
        while (i > 0) {
            if (nums[i] > nums[p]) {
                swap(nums,i,p);
                swap(cost,i,p);
                i = p;
                p = (i-1) >> 1;
            }else
                break;
        }
    }
    public void shift(int[] nums,int[] cost,int loc,int n){
        int i = loc,j = (i << 1) + 1;
        while (j < n) {
            if (j < n - 1 && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                swap(nums,i,j);
                swap(cost,i,j);
                i = j;
                j = (i << 1) + 1;
            }else
                break;
        }
    }


    //解法二：中位数贪心
    //证明此解法的正确性：假设取定中位数为使数组相等的数，此时的开销为x
    //设数组元素个数为n，如果n是奇数，若中位数右边一个元素才是使得数组开
    //销最小的元素，那么有开销y = x + (n/2+1) - n/2，中位数及其左边
    //的数需要增加一个费用，中位数右边的数需要减少一个费用，y = x+1，假
    //设不成立
    public long minCost2(int[] nums, int[] cost){
        int n = nums.length,mid = 0;
        long res = 0,sumCost = 0,tempCost = 0,midCost;

        dumpSort(nums,cost);

        for (int i = 0; i < n; i++) {
            sumCost += cost[i];
        }
        midCost = (sumCost+1) >> 1;

        for (int i = 0; i < n; i++) {
            tempCost += cost[i];
            if (tempCost >= midCost) {
                mid = nums[i];
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            res += (long) Math.abs(nums[i] - mid) * cost[i];
        }

        return res;
    }



    //复习一下解法1，14ms
    public long minCost(int[] nums, int[] cost){
        int n = nums.length;
        long res = 0,sumCost = 0,total = 0,temp = 0;

        int[][] list = new int[n][];
        for (int i = 0; i < n; i++) {
            list[i] = new int[]{nums[i],cost[i]};
            sumCost += cost[i];
        }

        Arrays.sort(list,(a,b)->{
            return a[0] - b[0];
        });

        for (int i = 1; i < n; i++) {
            total += (long)(list[i][0] - list[0][0]) * list[i][1];
        }
        res = total;
        temp = list[0][0];

        for (int i = 1; i < n; i++) {
            total = total + (list[i][0] - list[i-1][0]) * (2 * temp - sumCost);
            res = Math.min(res,total);
            temp += list[i][1];
        }

        return res;
    }
}
