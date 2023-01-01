package senior.数组和字符串;

import java.util.HashMap;
import java.util.Map;

public class No3 {
    //          四数相加II
    /**
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     *     0 <= i, j, k, l < n
     *     nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     */

    /**
     * 想法：先排序，再以每两个数组，时间复杂度为n方
     */
    /*public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length,res = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = (nums1[i] + nums2[j]) * -1;
                Integer value = map.get(temp);
                if (value != null) {
                    map.put(temp,value+1);
                }else{
                    map.put(temp,1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums3[i] + nums4[j];
                Integer value = map.get(temp);
                if (value != null) {
                    res += value;
                }
            }
        }

        return res;
    }*/

    //使用增强for循环
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length,res = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for (int num1 : nums1){
            for (int num2 : nums2){
                int temp = num1 + num2;
                Integer value = map.get(temp);
                if (value == null) {
                    map.put(temp,1);
                }else{
                    map.put(temp,value+1);
                }
            }
        }

        for (int num1 : nums3){
            for (int num2 : nums4){
                if (map.containsKey(-num1-num2)) {
                    res += map.get(-num1-num2);
                }
            }
        }

        return res;
    }
}
