package contest.year2022.m9.m9d17;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    /**
     *      按位或最大的最小子数组长度
     */

    //失败
    /*public int[] smallestSubarrays(int[] nums) {
        int max = 0,n = nums.length,l = 0,r = 0,temp = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            max |= nums[i];
        }
        while (l < n && r < n) {
            temp |= nums[r];
            r++;
            while (temp == max) {
                res[l] = r-l;
                temp ^= nums[l];
                l++;
            }
        }

        System.out.println(res[n-1]);
        for (int i = l; i < n; i++) {
            res[i] = 0;
        }

        return res;
    }*/

    //解法一：预处理，统计每个元素每个位上为1的最近的元素下标
    /*public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][30]; //f[i][j] 存放的是大于等于i的元素中，第j位为1的元素的最小下标
        int[] res = new int[n];

        //最后一个元素要特殊处理
        for(int i = 0;i < 30;i++){
            if(((nums[n-1] >> i) & 1) == 1)
                f[n-1][i] = n-1;
        }
        //更新其余元素
        for(int i = n-2;i >= 0;i--){
            for(int j = 0;j < 30;j++){
                if(((nums[i] >> j) & 1) == 1)
                    f[i][j] = i;
                else
                    f[i][j] = f[i+1][j];
            }
        }

        for(int i = 0;i < n;i++){
            int max = i;
            for(int j = 0;j < 30;j++){
                max = Math.max(max,f[i][j]);
            }
            res[i] = max - i + 1;
        }
        return res;
    }*/

    //解法二：维护下标数组
    /*public int[] smallestSubarrays(int[] nums){
        List<int[]> list = new ArrayList<>();
        int n = nums.length;
        int[] res = new int[n];

        for (int i = n-1; i >= 0; i--) {
            list.add(new int[]{0,i});
            int k = 0;
            for (int[] temp : list) {
                temp[0] |= nums[i];
                if (temp[0] == list.get(k)[0]) {
                    list.get(k)[1] = temp[1];
                }else
                    list.set(++k,temp);
            }

            //清除list中多余的元素
            list.subList(k+1,list.size()).clear();
            res[i] = list.get(0)[1] - i + 1;
        }

        return res;
    }*/


    //*****************为了加深印象，重新写一遍********************

    //解法一，预处理
    /*public int[] smallestSubarrays(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][30];
        for (int i = 0; i < 30; i++) {
            if (((nums[n - 1] >> i) & 1) == 1) {
                dp[n-1][i] = n-1;
            }
        }

        for (int i = n-2; i >= 0 ; i--) {
            for (int j = 0; j < 30; j++) {
                if((nums[i] >> j & 1) == 1)
                    dp[i][j] = i;
                else
                    dp[i][j] = dp[i+1][j];
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int mx = i;
            for (int j = 0; j < 30; j++) {
                mx = Math.max(mx,dp[i][j]);
            }
            res[i] = mx - i + 1;
        }
        return res;
    }*/


    //解法二：维护数组
    public int[] smallestSubarrays(int[] nums){
        int n = nums.length;
        List<int[]> temp = new ArrayList<>();
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            //第一个元素是按位或的值，第二个元素是按位或得到的最小下标
            temp.add(new int[]{0,i});
            int k = 0;
            for (int[] a : temp) {
                a[0] |= nums[i];
                if (temp.get(k)[0] == a[0]) {
                    //或运算之后值相同，但当前数组a的最小下标肯定是小于等于前面的数组的
                    //因为是按照顺序添加进来的，所以覆盖掉前面的最小下标
                    temp.get(k)[1] = a[1];
                }else{
                    //去重操作，将当前数组放到应该放的位置
                    k++;
                    temp.set(k,a);
                }
            }
            //将后面重复的数组截取掉
            temp.subList(k+1,temp.size()).clear();

            res[i] = temp.get(0)[1] - i + 1;
        }

        return res;
    }
}

//重新写一遍
class Solution{
    //超时，想的太简单了
    /*public int[] smallestSubarrays(int[] nums) {
        int n = nums.length,max;
        int[] res = new int[n];
        max = nums[n-1];
        res[n-1] = 1;

        for(int i = n-2;i > -1;i--){
            max = max | nums[i];
            int temp = nums[i],j;

            for( j = i+1;temp < max && j < n;j++){
                temp |= nums[j];
            }

            res[i] = j-i;
        }

        return res;
    }*/

    //加了特判，仍然超时，投机取巧是不可行的
    /*public int[] smallestSubarrays(int[] nums) {
        int n = nums.length,max;
        int[] res = new int[n];
        max = nums[n-1];
        res[n-1] = 1;

        for(int i = n-2;i > -1;i--){
            max = max | nums[i];
            int temp = nums[i],j;

            if (temp < max) {
                if (nums[i] == nums[i + 1]) {
                    res[i] = 1 + res[i + 1];
                    continue;
                }
                for( j = i+1;temp < max && j < n;j++){
                    temp |= nums[j];
                }
                res[i] = j-i;
            }else
                res[i] = 1;
        }

        return res;
    }*/

    //既然是位运算，思考方向可以从二进制位入手，寻找以当前
    //下标为开始的或运算值最大的子数组的长度。
    //从二进制的角度来看，或运算值最大，也就是说，我们需要
    //填补上当前元素与最大值之间相差的位为1的位置，那么，
    //从这方面上手，题目就可以解决了
    /*public int[] smallestSubarrays(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        int[][] dp = new int[n][30];
        for (int i = 0; i < 30; i++) {
            if(((nums[n-1] >> i) & 1) == 1)
                dp[n-1][i] = n-1;
        }

        for (int i = n-2; i > -1; i--) {
            for (int j = 0; j < 30; j++) {
                if(((nums[i] >> j) & 1) == 1)
                    dp[i][j] = i;
                else
                    dp[i][j] = dp[i+1][j];
            }
        }

        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = 0; j < 30; j++) {
                max = Math.max(max,dp[i][j]);
            }
            res[i] = max - i + 1;
        }

        return res;
    }*/


    //解法二：
    public int[] smallestSubarrays(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        List<int[]> list = new ArrayList<>();

        for (int i = n-1; i >= 0; i--) {
            list.add(new int[]{0,i});
            int k = 0;
            for (int[] temp : list) {
                temp[0] |= nums[i];
                if(list.get(k)[0] == temp[0])
                    list.get(k)[1] = temp[1];
                else
                    list.set(++k,temp);
            }
            //清除数组中重复的元素，避免影响到下一次循环
            list.subList(k+1,list.size()).clear();

            res[i] = list.get(0)[1] - i + 1;
        }

        return res;
    }
}