package review.其他;

import java.util.Stack;

public class No5 {
    /**
     *      有效的括号
     */

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if(stack.isEmpty() || stack.peek() != '(')
                    return false;
                stack.pop();
            } else if (chars[i] == ']') {
                if(stack.isEmpty() || stack.peek() != '[')
                    return false;
                stack.pop();
            } else if (chars[i] == '}') {
                if(stack.isEmpty() || stack.peek() != '{')
                    return false;
                stack.pop();
            } else
                stack.push(chars[i]);
        }

        return stack.isEmpty();
    }
}
