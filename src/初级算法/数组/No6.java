package 初级算法.数组;

import java.util.*;

public class No6 {
    //                两个数组的交集 II
    // 给定两个数组，编写一个函数来计算它们的交集。
    //
    //说明：
    //    输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
    //    我们可以不考虑输出结果的顺序。
    //
    //进阶：
    //    如果给定的数组已经排好序呢？你将如何优化你的算法？
    //    如果 nums1 的大小比 nums2 小很多，哪种方法更优？
    //    如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
    //
    //   作者：力扣 (LeetCode)
    //   链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
    //   来源：力扣（LeetCode）
    //   著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //---------------------------------------------------------------------------------
    //反复纠错之后才写对(最优解)
    /*
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return null;
        }
        //先排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        //排序完之后使用两个指针进行遍历
        int q1 = 0, q2 = 0, j = 0;
        int[] res = new int[nums1.length > nums2.length ? nums1.length : nums2.length];
        while (q1 < nums1.length && q2 < nums2.length) {
            //直到有相同值
            if(nums1[q1] < nums2[q2]) {
                q1++;
            }else if(nums1[q1] > nums2[q2]) {
                q2++;
            }
            if (q1 < nums1.length && q2 < nums2.length && nums1[q1] == nums2[q2]) {
                res[j] = nums1[q1];
                j++;
                q1++;
                q2++;
            }
        }
        int[] consult = new int[j];
        for (int i = 0; i < j; i++) {
            consult[i] = res[i];
        }
        return consult;
    }*/
    //----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------
    //                          解法二：使用哈希表
    public int[] intersect(int[] nums1, int[] nums2){
        if (nums1.length < 1 || nums2.length < 1) {
            return null;
        }

        //map中第一个数值是元素的值，第二个数值是第一个数组中出现次数
        Map<Integer,Integer> map = new HashMap<>();

        int max;
        if (nums1.length > nums2.length) {
            max = nums1.length;
        }
        else max = nums2.length;

        //两个数组中元素个数大的为结果数组的
        int[] temp = new int[max];
        int j = 0;

        for (int i = 0; i < nums1.length; i++) {
            //如果map中已经有了这个元素
            if (map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i])+1);
            }else
                map.put(nums1[i],1);
        }

        for (int i = 0; i < nums2.length; i++) {

            if (map.containsKey(nums2[i]) && !map.get(nums2[i]).equals(0)){
                temp[j++] = nums2[i];
                //放进去之后再减一
                map.put(nums2[i],map.get(nums2[i])-1);
            }
        }
        int[] res = new int[j];
        for (int i = 0; i < j; i++) {
            res[i] = temp[i];
        }
        return res;
    }
}
