package dataStructure.MonotonePriorityStack;

import java.util.Deque;
import java.util.LinkedList;

public class No84 {
    //思想：以每个柱子为高度，寻找左右两侧的边界——第一个小于它的下标就是边界
    //暴力的解法就是以当前元素为中心，往两边扩散
    //可以使用单调栈优化
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n],right = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0;i < n;i++){
            //不必担心相等的情况会出问题，因为如果有一连串相等的元素，虽然前
            //面元素计算出来的值是不完整的，但是最后一个计算出来的值是正确的
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int loc = stack.pop();
                right[loc] = i;
            }
            if(stack.isEmpty()){
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int loc = stack.pop();
            right[loc] = n;
        }
        int res = 0;
        for(int i = 0;i < n;i++){
            int l = right[i]-left[i]-1;
            res = Math.max(res,l * heights[i]);
        }
        return res;
    }

    //使用数组代替栈结构，7ms
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n],left = new int[n],right = new int[n];
        int l = 0;
        for(int i = 0;i < n;i++){
            while(l != 0 && heights[stack[l-1]] >= heights[i]){
                l--;
                right[stack[l]] = i;
            }
            if(l == 0){
                left[i] = -1;
            } else {
                left[i] = stack[l-1];
            }
            stack[l++] = i;
        }
        while(l != 0){
            l--;
            right[stack[l]] = n;
        }
        int res = 0;
        for(int i = 0;i < n;i++){
            int val = heights[i] * (right[i]-left[i]-1);
            res = Math.max(res,val);
        }
        return res;
    }
}
