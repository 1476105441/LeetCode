package algorithm.dp;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class No42 {
    //接雨水
    //解法一：动态规划
    /*public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n],right = new int[n];
        left[0] = height[0];
        right[n-1] = height[n-1];
        for (int i = 1; i < n; i++) {
            int j = n-1-i;
            left[i] = Math.max(height[i],left[i-1]);
            right[j] = Math.max(height[j],right[j+1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(left[i],right[i]) - height[i];
        }
        return res;
    }*/

    //解法二：单调栈
    /*public int trap(int[] height) {
        int n = height.length,res = 0;
        //Stack<Integer> stack = new Stack<>(); //使用这个效率非常低，13ms
        //这个2ms
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]){
                int base = height[stack.pop()];
                if(stack.isEmpty()){
                    break;
                }
                int j = stack.peek(),l = i-j-1;
                int h = Math.min(height[j],height[i]) - base;
                res += h * l;
            }
            stack.push(i);
        }
        return res;
    }*/

    //解法三：双指针，0ms
    //思想：只移动较小的一端，先忽略中间元素，元素能接的雨水数取决于两端的大小
    public int trap(int[] height) {
        int n = height.length,res = 0;
        //双指针，移动策略导致了没有移动的一端一定是全局最大的一端
        int l = 0,r = n-1,lm = height[l],rm = height[r];
        while(l < r){
            if(height[l] >= height[r]){
                res += rm - height[r];
                //rm一定比lm小，有两种情况：
                //1、上回合r移动过，那么rm == height[r]
                //2、上回合r没有移动，l移动过，那么l找到了一个比r的值更大的值
                r--;
                rm = Math.max(rm,height[r]);
            } else {
                res += lm - height[l];
                l++;
                lm = Math.max(lm,height[l]);
            }
        }
        return res;
    }
}
