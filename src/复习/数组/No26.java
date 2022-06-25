package 复习.数组;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class No26 {
    /**
     * 基本计算器II
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 整数除法仅保留整数部分。
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
     * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * <p>
     * 提示：
     * 1 <= s.length <= 3 * 105
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * s 表示一个 有效表达式
     * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
     * 题目数据保证答案是一个 32-bit 整数
     */

    //分为加减法和乘除法运算，加减法存入堆栈中，乘除法当即做运算
    //直接将所有数字都存入堆栈中，如果前一个运算符是乘除，则从堆
    //栈中取出一个数字进行乘除，再放回堆栈中
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        char pre = '+';
        int num = 0;
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 48 && chars[i] <= 57) {
                num = num * 10 + chars[i] - 48;
            }
            if (chars[i] != ' ' && (chars[i] < 48 || chars[i] > 57) || i == chars.length - 1) {
                switch (pre) {
                    case '*':
                        stack.push(stack.pop() * num);break;
                    case '/':
                        stack.push(stack.pop() / num);break;
                    case '+':
                        stack.push(num);break;
                    default :
                        stack.push(-num);
                }
                pre = chars[i];
                num = 0;
            }
        }

        num = 0;
        while (!stack.isEmpty()) {
            num += stack.pop();
        }

        return num;
    }
}
