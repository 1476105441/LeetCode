package 周赛.m7d10;

public class No2 {
    /**
     *      无限集中的最小数字
     */
}
class SmallestInfiniteSet {
    int[] nums;
    public SmallestInfiniteSet() {
        nums = new int[1001];
        for (int i = 0; i < 1001; i++) {
            nums[i] = i+1;
        }
    }

    public int popSmallest() {
        for (int i = 0; i < 1001; i++) {
            if (nums[i] != -1) {
                int res = nums[i];
                nums[i] = -1;
                return res;
            }
        }
        return -1;
    }

    public void addBack(int num) {
        if (nums[num - 1] != -1) {
            return;
        }
        nums[num-1] = num;
    }
}

