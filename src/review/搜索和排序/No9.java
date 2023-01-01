package review.搜索和排序;

public class No9 {
    /**
     * 搜索旋转排序数组
     */

    public int search(int[] nums, int target) {
        int head = nums[0], flag, r, l, center;
        if (head == target) {
            return 0;
        } else if (head < target) {
            flag = 0;
        } else
            flag = 1;

        l = 0;
        r = nums.length - 1;
        while (l < r) {
            center = l + ((r - l) >> 1);
            if (nums[center] == target) {
                return center;
            } else if (flag == 1) {
                if (nums[center] < target || nums[center] >= head) {
                    l = center + 1;
                } else
                    r = center - 1;
            } else {
                if (nums[center] > target || nums[center] < head) {
                    r = center - 1;
                } else
                    l = center + 1;
            }
        }

        if (nums[l] == target) {
            return l;
        }
        return -1;
    }
}
