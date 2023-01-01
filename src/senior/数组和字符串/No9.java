package senior.数组和字符串;

import java.util.Deque;
import java.util.LinkedList;

public class No9 {
    /**
     *          基本计算器II
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 整数除法仅保留整数部分。
     *
     * 提示：
     *     1 <= s.length <= 3 * 105
     *     s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     *     s 表示一个 有效表达式
     *     表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
     *     题目数据保证答案是一个 32-bit 整数
     */

    /**
     * 想法：使用堆栈和两个指针
     * 结果：速度很慢，30ms
     */
    /*public int calculate(String s){
        //p1标记加减法，p2是当前遍历到的位置，p3标记乘除法
        int p1 = -1,p2 = 0,p3 = -1;
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (p2 <= chars.length) {
            if (p2 == chars.length || chars[p2] == '+' || chars[p2] == '-' || chars[p2] == '*' || chars[p2] == '/') {
                stack.push(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                if (p3 != -1) {
                    int temp2 = stack.pop();
                    int temp1 = stack.pop();
                    if(chars[p3] == '*'){
                        stack.push(temp1 * temp2);
                    }else{
                        stack.push(temp1 / temp2);
                    }
                    p3 = -1;
                }
                if (p1 != -1 && (p2 == chars.length || chars[p2] == '+' || chars[p2] == '-')) {
                    int temp2 = stack.pop();
                    int temp1 = stack.pop();
                    if (chars[p1] == '+') {
                        stack.push(temp1 + temp2);
                    } else if (chars[p1] == '-') {
                        stack.push(temp1 - temp2);
                    }
                    p1 = -1;
                }
                if (p2 < chars.length && (chars[p2] == '+' || chars[p2] == '-')) {
                    p1 = p2;
                }else if(p2 < chars.length){
                    p3 = p2;
                }
            } else if(chars[p2] != ' '){
                sb.append(chars[p2]);
            }
            p2++;
        }

        return stack.pop();
    }*/

    /**
     * 官方题解：13ms
     */
    /*public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }*/

    /**
     *   改进后的官方题解，使用char数组代替了字符串的一系列函数，
     * 省掉了不必要的时间
     *   思想：官方题解是将减法转换成加法，这样只用处理加法就可以
     * 了，如果是加减法就直接存入堆栈中，因为加法没有先后顺序要求，
     * 而如果是乘除法，就从堆栈中取出前一个数和当前数做运算
     */
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<Integer>();
        char[] chars = s.toCharArray();
        //提前保存的运算符，当现在的符号不是数字时，对上一个运算符做运算
        char preSign = '+';
        int num = 0;
        int n = chars.length;
        for (int i = 0; i < n; ++i) {
            //当前位是数字的时候，采用累乘的方法，将结果拼接起来
            if (Character.isDigit(chars[i])) {
                num = num * 10 + chars[i] - '0';
            }
            //当前位不是数字，且不是空格或者是最后一位时，将前一个运算符进行运算
            if (!Character.isDigit(chars[i]) && chars[i] != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                //更改运算符，前一个运算符已经运算完毕，改为当前的运算符
                preSign = chars[i];
                //清空数字，准备重新存储下一个数字
                num = 0;
            }
        }
        int ans = 0;
        //将堆栈中的运算结果全部加起来，就得到了最终的结果
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
