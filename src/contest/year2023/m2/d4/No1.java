package contest.year2023.m2.d4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class No1 {
    public int[] separateDigits(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            Stack<Integer> stack = new Stack<>();
            while (temp > 0) {
                stack.add(temp % 10);
                temp /= 10;
            }
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
