package practice;

public class No496 {
    /**
     *      下一个更大元素I
     */

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length,m = nums2.length;
        int[] res = new int[n],bucket = new int[10001];

        for (int i = 0; i < m; i++) {
            bucket[nums2[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            res[i] = -1;
            int x = bucket[nums1[i]];
            while (x < m) {
                if (nums2[x] > nums1[i]) {
                    res[i] = nums2[x];
                    break;
                }
                x++;
            }
        }

        return res;
    }
}
