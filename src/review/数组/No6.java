package review.数组;

import java.util.HashMap;
import java.util.Map;

public class No6 {
    /**
     *      两个数组的交集 II
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致
     * （如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     *
     * 提示：
     *     1 <= nums1.length, nums2.length <= 1000
     *     0 <= nums1[i], nums2[i] <= 1000
     *
     * 进阶：
     *     如果给定的数组已经排好序呢？你将如何优化你的算法？
     *     如果 nums1 的大小比 nums2 小，哪种方法更优？
     *     如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     */

    //解法一：先排序再双指针比较
    /*public int[] intersect(int[] nums1, int[] nums2){
        quickSort(nums1,0,nums1.length-1);
        quickSort(nums2,0,nums2.length-1);
        int[] temp = new int[Math.min(nums1.length,nums2.length)],res;

        int i = 0,j = 0,k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                temp[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        res = new int[k];
        for (int l = 0; l < k; l++) {
            res[l] = temp[l];
        }

        return res;
    }

    public void quickSort(int[] nums,int low,int high){
        if (low >= high) {
            return;
        }
        int i = low,j = high,temp;

        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        quickSort(nums,low,i-1);
        quickSort(nums,i+1,high);
    }*/

    //解法二：使用哈希表
    public int[] intersect(int[] nums1,int[] nums2){
        Map<Integer,Integer> map = new HashMap<>();
        int[] res,temp = new int[Math.min(nums1.length,nums2.length)];
        Integer value,k=0;

        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }

        for (int i = 0; i < nums2.length; i++) {
            value = map.get(nums2[i]);
            if (value != null && value > 0) {
                temp[k++] = nums2[i];
                map.put(nums2[i],value-1);
            }
        }

        res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = temp[i];
        }

        return res;
    }
}
