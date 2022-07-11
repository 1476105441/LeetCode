package 复习.搜索和排序;

public class No12 {
    /**
     *      寻找两个正序数组的中位数
     */

    //在两个数组中寻找中位数，就是寻找两个数组中
    /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length,total = m+n;
        boolean flag = false;
        //判断总数的奇偶
        if ((total & 1) == 0) {
            flag = true;
        }
        int s1 = 0,s2 = 0,k = (total+1)>>1,tmpK,l1,l2;
        double res = -1;

        while (k > 0) {
            tmpK = (k >> 1)-1;

            //还要判断两个数组中是否有已经全部去掉的
            if (s1 == m) {
                l2 = s2+k-1;
                if (flag) {
                    res = ((double)(nums2[l2]+nums2[l2+1]))/2;
                }else
                    res = nums2[l2];
                break;
            }
            if (s2 == n) {
                l1 = s1+k-1;
                if (flag) {
                    res = ((double)(nums1[l1]+nums1[l1+1]))/2;
                }else
                    res = nums1[l1];
                break;
            }
            //k等于1，到了终止位置
            if (k == 1) {
                if (flag) {
                    int temp1,temp2;
                    if (nums1[s1] < nums2[s2]) {
                        temp1 = nums1[s1++];
                        if (s1 < m) {
                            temp2 = Math.min(nums1[s1],nums2[s2]);
                        }else
                            temp2 = nums2[s2];
                    }else{
                        temp1 = nums2[s2++];
                        if (s2 < n) {
                            temp2 = Math.min(nums1[s1],nums2[s2]);
                        }else
                            temp2 = nums1[s1];
                    }
                    res = ((double)(temp1+temp2))/2;
                }else
                    res = Math.min(nums1[s1],nums2[s2]);
                break;
            }else{
                if (s1 >= m - tmpK) {
                    l1 = m-1;
                }else
                    l1 = s1 + tmpK;
                if (s2 >= n - tmpK) {
                    l2 = n-1;
                }else
                    l2 = s2 + tmpK;

                //更改起始位置，淘汰掉一部分元素
                if (nums1[l1] > nums2[l2]) {
                    k -= (l2-s2+1);
                    s2 = l2+1;
                }else{
                    k -= (l1-s1+1);
                    s1 = l1+1;
                }
            }
        }

        return res;
    }*/

    //重写一遍
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        int m = nums1.length,n = nums2.length,total = m+n,k = (total+1)>>1,half;
        boolean flag = (total&1) == 1;

        int s1 = 0,s2 = 0,t1,t2,temp1,temp2;
        while (true) {
            half = (k>>1)-1;
            if (s1 == m) {
                if (flag) {
                    return nums2[s2+k-1];
                }else{
                    return (double)(nums2[s2+k-1]+nums2[s2+k])/2;
                }
            }
            if (s2 == n) {
                if (flag) {
                    return nums1[s1+k-1];
                }else{
                    return (double)(nums1[s1+k-1]+nums1[s1+k])/2;
                }
            }
            if (k == 1) {
                if (flag) {
                    return Math.min(nums1[s1],nums2[s2]);
                }else{
                    if (nums1[s1] > nums2[s2]) {
                        temp1 = nums2[s2++];
                        if (s2 >= n) {
                            temp2 = nums1[s1];
                        }else
                            temp2 = Math.min(nums1[s1],nums2[s2]);
                    }else{
                        temp1 = nums1[s1++];
                        if (s1 >= m) {
                            temp2 = nums2[s2];
                        }else
                            temp2 = Math.min(nums1[s1],nums2[s2]);
                    }

                    return (double) (temp1+temp2)/2;
                }
            }else{
                if (s1 >= m - half) {
                    t1 = m-1;
                }else
                    t1 = s1+half;

                if (s2 >= n - half) {
                    t2 = n-1;
                }else
                    t2 = s2+half;

                if (nums1[t1] > nums2[t2]) {
                    k -= t2-s2+1;
                    s2 = t2+1;
                }else{
                    k -= t1-s1+1;
                    s1 = t1+1;
                }
            }
        }
    }
}
