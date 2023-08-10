package contest.year2023.m5.d21;

import java.util.Stack;

/**
 * 2696.删除子串后的字符串最小长度
 *
 * @author wjs 2023/8/10
 */
public class No1 {
    //解法一：暴力替换，6ms
    /*public int minLength(String s) {
        int loc = 0;
        while ((loc = s.indexOf("AB")) != -1 || (loc = s.indexOf("CD")) != -1) {
            if (loc < s.length() - 2) {
                s = s.substring(0,loc) + s.substring(loc+2);
            } else {
                s = s.substring(0,loc);
            }
        }
        return s.length();
    }*/

    //解法二：使用栈，6ms
    public int minLength(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if(chars[i] == 'B' && !stack.isEmpty() && stack.peek() == 'A') {
                stack.pop();
            } else if(chars[i] == 'D' && !stack.isEmpty() && stack.peek() == 'C') {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.size();
    }
}
