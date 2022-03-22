package 初级算法.排序和搜索;

public class No1 {
    //                 合并两个有序数组
    //  给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
    //请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
    //
    //注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的
    // 初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
    //
    //进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？

    //想法：双指针遍历两个数组进行比较，选大的
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //loc是当前存放位置，i是遍历nums1，j是遍历nums2
        int loc = m+n-1,i = m-1,j = n-1;

        if (i == -1) {
            while (j >= 0) {
                nums1[j] = nums2[j];
                j--;
            }
            return;
        }

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]){
                nums1[loc--] = nums1[i--];
            }else{
                nums1[loc--] = nums2[j--];
            }
        }

        //处理剩下的
        while (i >= 0) {
            nums1[loc--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[loc--] = nums2[j--];
        }
    }
}
