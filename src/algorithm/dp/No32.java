package algorithm.dp;

import java.util.Stack;

public class No32 {
    //最长有效括号
    //栈+动态规划，成功4ms
    /*public int longestValidParentheses(String s) {
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
    }*/

    //动态规划，成功，1ms
    /*public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length,res = 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                    dp[i] = (i - dp[i-1] - 2 >= 0 ? dp[i- dp[i-1] -2] : 0) + 2 + dp[i-1];
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }*/

    //两次遍历，成功，1ms
    public int longestValidParentheses(String s) {
        char[] chars =s.toCharArray();
        int n = chars.length,res = 0;
        int l = 0,r = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                res = Math.max(res,2 * r);
            } else if (r > l) {
                l = r = 0;
            }
        }
        l = 0;
        r = 0;
        for (int i = n-1; i > -1; i--) {
            if (chars[i] == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                res = Math.max(res,2 * l);
            } else if (l > r) {
                l = r = 0;
            }
        }
        return res;
    }
}
