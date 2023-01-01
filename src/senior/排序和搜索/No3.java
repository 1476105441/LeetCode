package senior.排序和搜索;

public class No3 {
    /**
     *          寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * 提示：
     *     nums1.length == m
     *     nums2.length == n
     *     0 <= m <= 1000
     *     0 <= n <= 1000
     *     1 <= m + n <= 2000
     *     -106 <= nums1[i], nums2[i] <= 106
     */

    //题目要求算法的时间复杂度应为O(log(m+n))，所以只能使用二分查找
    //根据中位数的特性，寻找中位数其实就是寻找第k小的数，其中k为(m+n)/2
    //或者(m+n)/2+1
    /*public double findMedianSortedArrays(int[] nums1,int[] nums2){
        int k,m = nums1.length,n = nums2.length,start1=0,start2=0,halfK;
        k = (m+n+1)/2;
        double res1,res2;
        int temp1,temp2;

        //判断奇偶，如果是奇数就只用找一个数
        boolean flag = false;
        if (((m + n) & 1) == 1) {
            flag = true;
        }

        while (true) {
            if (start1 == m) {
                if (flag) {
                    return nums2[start2 + k - 1];
                }else{
                    res1 = nums2[start2 + k - 1];
                    res2 = nums2[start2 + k];
                    return (res1 + res2)/2;
                }
            }
            if (start2 == n) {
                if (flag) {
                    return nums1[start1 + k - 1];
                }else{
                    res1 = nums1[start1 + k - 1];
                    res2 = nums1[start1 + k];
                    return (res1 + res2)/2;
                }
            }
            if (k == 1) {
                if (flag) {
                    return Math.min(nums1[start1],nums2[start2]);
                }else{
                    if (nums1[start1] < nums2[start2]) {
                        res1 = nums1[start1++];
                    }else{
                        res1 = nums2[start2++];
                    }
                    if (start1 == m) {
                        res2 = nums2[start2];
                    } else if (start2 == n) {
                        res2 = nums1[start1];
                    }else
                        res2 = Math.min(nums1[start1],nums2[start2]);
                    return (res1+res2)/2;
                }
            }
            halfK = k/2 - 1;
            if (start1 + halfK > m - 1) {
                temp1 = m - 1;
            }else{
                temp1 = start1 +halfK;
            }
            if (start2 + halfK > n - 1) {
                temp2 = n - 1;
            }else{
                temp2 = start2 +halfK;
            }

            if (nums1[temp1] < nums2[temp2]) {
                k = k - (temp1-start1+1);
                start1 = temp1+1;
            }else{
                k = k - (temp2-start2+1);
                start2 = temp2+1;
            }
        }
    }*/


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length,sum = m+n,k = (sum+1) >> 1;
        //System.out.println(k);
        if((sum & 1) == 0){
            double num1 = find(nums1,nums2,k),num2 = find(nums1,nums2,k+1);
            return (num1 + num2)/2;
        }
        return find(nums1,nums2,k);
    }
    public double find(int[] nums1,int[] nums2,int k){
        int s1 = 0,s2 = 0,temp,l1,l2;
        while(k > 1){
            temp = k >> 1;
            l1 = s1 + temp - 1;
            l2 = s2 + temp - 1;
            //特殊情况
            if(l1 >= nums1.length){
                l1 = nums1.length-1;
                if(nums1.length == 0){
                    return nums2[s1+k-1];
                }
                if(nums1[l1] < nums2[l2]){
                    int count = k - (l1-s1+1);
                    return nums2[s2+count-1];
                }
                s2 = l2+1;
            }
            else if(l2 >= nums2.length){
                l2 = nums2.length-1;
                if(nums2.length == 0)
                    return nums1[s1+k-1];
                if(nums1[l1] > nums2[l2]){
                    int count = k - (l2-s2+1);
                    return nums1[s1+count-1];
                }
                s1 = l1+1;
            }
            else{
                if(nums1[l1] < nums2[l2]){
                    s1 = l1+1;
                }else{
                    s2 = l2+1;
                }
            }
            k -= temp;
        }

        //k==1时仍然有可能出现特殊情况，如：其中一个数组的所有元素刚好被排除完了
        if(s1 == nums1.length){
            return nums2[s2];
        }else if(s2 == nums2.length){
            return nums1[s1];
        }

        return nums1[s1] > nums2[s2] ? nums2[s2] : nums1[s1];
    }
}
