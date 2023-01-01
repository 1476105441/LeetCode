package review.设计问题;

import java.util.Random;

public class No1 {
    /**
     *      打乱数组
     */
    class Solution {
        int[] val, temp;

        public Solution(int[] nums) {
            val = nums;
        }

        public int[] reset() {
            temp = val;
            return val;
        }

        public int[] shuffle() {
            Random random = new Random();
            temp = new int[val.length];
            for (int i = 0; i < val.length; i++) {
                temp[i] = val[i];
            }
            int tmp;
            for (int i = 0; i < temp.length; i++) {
                int j = random.nextInt(i+1);
                tmp = temp[i];
                temp[i] = temp[j];
                temp[j] = tmp;
            }
            return temp;
        }
    }
}
