package review.其他;

public class No8 {
    /**
     *      逆波兰表达式求值
     */

    //直接使用堆栈
    /*public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                int x = stack.pop(),y = stack.pop();
                stack.push(y+x);
            } else if ("-".equals(tokens[i])) {
                int x = stack.pop(),y = stack.pop();
                stack.push(y-x);
            } else if ("*".equals(tokens[i])) {
                int x = stack.pop(),y = stack.pop();
                stack.push(y*x);
            } else if ("/".equals(tokens[i])) {
                int x = stack.pop(),y = stack.pop();
                stack.push(y/x);
            }else
                stack.push(Integer.valueOf(tokens[i]));
        }
        return stack.pop();
    }*/

    //使用数组代替堆栈
    //细节：每一个波兰表达式中都会有半数减1的符号，且整体数目一定时奇数
    public int evalRPN(String[] tokens){
        int n = tokens.length,loc=0;
        int[] stack = new int[n/2+1];
        for (int i = 0; i < n; i++) {
            if ("+".equals(tokens[i])) {
                int x = stack[--loc],y = stack[--loc];
                stack[loc++] = y+x;
            } else if ("-".equals(tokens[i])) {
                int x = stack[--loc],y = stack[--loc];
                stack[loc++] = y-x;
            } else if ("*".equals(tokens[i])) {
                int x = stack[--loc],y = stack[--loc];
                stack[loc++] = y*x;
            } else if ("/".equals(tokens[i])) {
                int x = stack[--loc],y = stack[--loc];
                stack[loc++] = y/x;
            }else
                stack[loc++] = Integer.parseInt(tokens[i]);
        }
        return stack[--loc];
    }
}