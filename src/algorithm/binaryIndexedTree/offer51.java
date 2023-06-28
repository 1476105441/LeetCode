package algorithm.binaryIndexedTree;

import java.util.Arrays;

/**
 * 剑指Offer51.数组中的逆序对
 *
 * @author wjs 2023/6/27
 */
public class offer51 {
    //解法一：树状数组
    /*int[] c;
    Integer[] tmp;
    public int reversePairs(int[] nums) {
        int n = nums.length;
        c = new int[n+1];
        tmp = new Integer[n];
        for(int i=0;i < n;i++) {
            tmp[i] = nums[i];
        }
        Arrays.sort(tmp,(x, y) -> {
            if(y == x){
                return 0;
            } else if(y > x) {
                return 1;
            } else {
                return -1;
            }
        });
        int res = 0;
        for(int i=0;i < n;i++) {
            int loc = findLoc(nums[i]) + 1;
            //System.out.println(loc);
            res += getVal(loc-1);
            update(loc);
        }
        return res;
    }
    private int findLoc(int num) {
        int l=0,r=tmp.length-1;
        while(l < r) {
            int c = l + ((r-l) >> 1);
            if(tmp[c] > num) {
                l = c+1;
            } else if(tmp[c] < num) {
                r = c-1;
            } else {
                return c;
            }
        }
        return l;
    }
    private int lowbit(int num) {
        return num & -num;
    }
    private void update(int loc) {
        int len = c.length;
        while(loc < len) {
            c[loc]++;
            loc += lowbit(loc);
        }
    }
    private int getVal(int loc) {
        int res = 0;
        while(loc > 0) {
            res += c[loc];
            loc -= lowbit(loc);
        }
        return res;
    }*/

    //解法二：归并排序
    int[] vals,temp;
    int res;
    public int reversePairs(int[] nums) {
        int n = nums.length;
        vals = nums;
        temp = new int[n];
        mergeSort(0,n-1);
        return res;
    }
    private void mergeSort(int l, int r) {
        if(l >= r) {
            return;
        }
        int c = l + ((r-l) >> 1);
        mergeSort(l,c);
        mergeSort(c+1,r);
        int i = l, j = c+1,k = l;
        while(i <= c && j <= r) {
            if(vals[i] > vals[j]) {
                temp[k] = vals[j];
                j++;
            } else {
                res += j - c - 1;
                temp[k] = vals[i];
                i++;
            }
            k++;
        }
        while(i <= c) {
            temp[k] = vals[i];
            i++;
            k++;
            res += r - c;
        }
        while(j <= r) {
            temp[k] = vals[j];
            j++;
            k++;
        }
        for(int loc=l; loc <= r;loc++) {
            vals[loc] = temp[loc];
        }
    }
}
