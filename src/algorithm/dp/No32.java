package algorithm.dp;

import java.util.Stack;

public class No32 {
    //最长有效括号
    //栈，成功4ms
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length,res = 0;
        int[] dp = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < n;i++){
            if(chars[i] == ')'){
                if(!stack.isEmpty()){
                    int loc = stack.pop();
                    dp[i] = dp[loc] + 2;
                    if(loc != i-1 && i > 0){
                        dp[i] += dp[i-1];
                    }
                }
            } else {
                stack.push(i);
                if(i > 0 && chars[i-1] == ')'){
                    dp[i] = dp[i-1];
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
