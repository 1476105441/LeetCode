package 复习.设计问题;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    /**
     *      最小栈
     */

    class MinStack {
        int min,front,rear;
        int[] nums;
        public MinStack() {
            nums = new int[30001];
            min = Integer.MAX_VALUE;
            front = 0;
            rear = 0;
        }

        public void push(int val) {
            nums[front] = val;
            front++;
            if (min > val) {
                min = val;
            }
        }

        public void pop() {
            if (nums[front-1] == min) {
                min = Integer.MAX_VALUE;
                for (int i = 0; i < front-1; i++) {
                    if(min > nums[i])
                        min = nums[i];
                }
            }
            front--;
        }

        public int top() {
            return nums[front-1];
        }

        public int getMin() {
            return min;
        }
    }
}
