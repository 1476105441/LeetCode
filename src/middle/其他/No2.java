package middle.其他;

public class No2 {
    //                  逆波兰表达式求值
    //根据 逆波兰表示法，求表达式的值。
    //有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
    //
    //说明：
    //    整数除法只保留整数部分。
    //    给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

    //-------------------------------------------------------------------
    //                           使用堆栈
    //速度太慢，分析速度慢的原因？
    //  字符串拼接浪费大量时间
    /*public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        Integer num1,num2;
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                num1 = Integer.valueOf(stack.pop());
                num2 = Integer.valueOf(stack.pop());
                num1 += num2;
                stack.push(num1 + "");
            } else if ("-".equals(tokens[i])) {
                num1 = Integer.valueOf(stack.pop());
                num2 = Integer.valueOf(stack.pop());
                num2 -= num1;
                stack.push(num2 + "");
            } else if ("*".equals(tokens[i])) {
                num1 = Integer.valueOf(stack.pop());
                num2 = Integer.valueOf(stack.pop());
                num2 *= num1;
                stack.push(num2 + "");
            } else if ("/".equals(tokens[i])) {
                num1 = Integer.valueOf(stack.pop());
                num2 = Integer.valueOf(stack.pop());
                num2 /= num1;
                stack.push(num2 + "");
            }else{
                stack.push(tokens[i]);
            }
        }

        Integer res = Integer.valueOf(stack.pop());

        return res;
    }*/
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //字符串拼接会浪费大量时间，所以将存储的数组改为int类型，去除拼接步骤即可优化算法的时间
    public int evalRPN(String[] tokens){
        int[] temp = new int[(tokens.length + 1) / 2];
        int count = 0;
        int num1,num2;
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                num1 = temp[--count];
                num2 = temp[--count];
                num2 += num1;
                temp[count] = num2;
            } else if ("-".equals(tokens[i])) {
                num1 = temp[--count];
                num2 = temp[--count];
                num2 -= num1;
                temp[count] = num2;
            } else if ("*".equals(tokens[i])) {
                num1 = temp[--count];
                num2 = temp[--count];
                num2 *= num1;
                temp[count] = num2;
            } else if ("/".equals(tokens[i])) {
                num1 = temp[--count];
                num2 = temp[--count];
                num2 /= num1;
                temp[count] = num2;
            }else{
                temp[count] = Integer.parseInt(tokens[i]);
            }
            count++;
        }

        return temp[count-1];
    }
}
