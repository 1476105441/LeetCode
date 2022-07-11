package 周赛.m7d9;

import java.util.Arrays;

public class No3 {
    /**
     *      最小差值平方和
     */

    //使用堆，超时了
    /*public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length,total = k1+k2;
        long res=0;
        long[] temp = new long[n];

        for (int i = 0; i < n; i++) {
            temp[i] = Math.abs(nums1[i]-nums2[i]);
            update(temp,i);
        }
        while (total > 0) {
            if(temp[0] > 0)
                temp[0]--;
            else
                break;
            shift(temp,0,n);
            total--;
        }

        for (int i = 0; i < n; i++) {
            res += temp[i]*temp[i];
        }
        return res;
    }
    public void shift(long[] nums,int s,int n){
        int i = s,j = (i<<1)+1;
        while (j < n) {
            if (j < n - 1 && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                long temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    public void update(long[] nums,int loc){
        int i = loc,p = (i-1)>>1;
        while (i > 0) {
            if (nums[i] > nums[p]) {
                long temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }*/

    //解法一，最快13ms
    /*public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length,total = k1+k2;
        long res=0,sum = 0;
        long[] temp = new long[n+1];

        for (int i = 1; i < n+1; i++) {
            temp[i] = Math.abs(nums1[i-1]-nums2[i-1]);
            sum += temp[i];
            res += temp[i]*temp[i];
        }
        temp[0] = 0;
        if (sum < total) {
            return 0;
        }

        Arrays.sort(temp);
        for (int i = n; ; i--) {
            //当前要减少的元素数量
            int m = n+1-i;
            //v是当前差值的大小，c
            //是将m个元素减少到temp[i-1]
            //所需要的k值
            long v = temp[i],c;

            if (i == 0) {
                c = m * v;
            }else{
                c = m * (v-temp[i-1]);
            }
            //先剪掉，后面退出循环时再加上
            res -= v*v;

            if (c < total) {
                total -= c;
                continue;
            }

            //每个元素的高度只能减少这么多，总
            //共有m个元素，total平均分给每个
            //元素，但是还有多余的元素
            v -= total/m;
            return res + (total%m)*(v-1)*(v-1) + (m-total%m)*v*v;
        }
    }*/

    //解法二，二分法，效率差不多
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2){
        int n = nums1.length;
        long total = k1+k2,sum = 0,res = 0,target = 0;
        long[] temp = new long[n];
        for (int i = 0; i < n; i++) {
            temp[i] = Math.abs(nums1[i]-nums2[i]);
            sum += temp[i];
        }
        if (sum < total) {
            return 0;
        }

        long l = 0,r = 100001;
        while (l <= r) {
            long c = l + ((r-l)>>1);
            if (check(temp, c, total)) {
                target = c;
                r = c-1;
            }else
                l = c+1;
        }

        for (int i = 0; i < n; i++) {
            if (temp[i] > target) {
                total -= (temp[i] - target);
                temp[i] = target;
            }
        }
        int j = 0;
        while (total > 0) {
            if (temp[j] == target) {
                total--;
                temp[j]--;
            }
            j++;
        }

        for (int i = 0; i < n; i++) {
            res += temp[i] * temp[i];
        }
        return res;
    }
    public boolean check(long[] temp,long center,long total){
        long sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += Math.max(temp[i],center)-center;
        }

        return sum <= total;
    }
}
