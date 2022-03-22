package 中级算法复习.其他;

import java.util.Stack;

public class No2 {
    //          逆波兰表达式求值

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer temp1,temp2;

        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                temp1 = stack.pop();
                temp2 = stack.pop();
                stack.push(temp2 + temp1);
            } else if ("-".equals(tokens[i])) {
                temp1 = stack.pop();
                temp2 = stack.pop();
                stack.push(temp2 - temp1);
            } else if ("*".equals(tokens[i])) {
                temp1 = stack.pop();
                temp2 = stack.pop();
                stack.push(temp2 * temp1);
            } else if ("/".equals(tokens[i])) {
                temp1 = stack.pop();
                temp2 = stack.pop();
                stack.push(temp2 / temp1);
            }else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }

        return stack.pop();
    }
}
