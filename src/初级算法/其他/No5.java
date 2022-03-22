package 初级算法.其他;

public class No5 {
    //                  有效的括号
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    //
    //有效字符串需满足：
    //    左括号必须用相同类型的右括号闭合。
    //    左括号必须以正确的顺序闭合。

    //看到题目的思路：堆栈解决
    //---------------------------------------------------------------------------------------
    /*
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        int num = 0;

        while ( num < chars.length) {

            if (chars[num] == ')' || chars[num] == '}' || chars[num] == ']') {
                if (stack.empty()) {
                    return false;
                }
                char c = stack.pop();
                if (chars[num] == ')' && c != '(') {
                    return false;
                } else if (chars[num] == '}' && c != '{') {
                    return false;
                } else if (chars[num] == ']' && c != '[') {
                    return false;
                }
            }else{
                stack.add(chars[num]);
            }

            num++;
        }

        if (!stack.empty()){
            return false;
        }

        return true;
    }
    */
    //---------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //这题还有没有别的解法？
    //试试回溯能不能解决
    //                   成功，两个成员变量用来记录
    int loc = 0;
    int count = 0;
    public boolean isValid(String s){
        char[] chars = s.toCharArray();

        char c = recursion(chars);

        if (c == '1') {
            return true;
        }
        return false;
    }

    //字符1代表匹配成功，字符2代表匹配失败
    public char recursion(char[] chars){
        //递归出口
        if (loc == chars.length ) {
            if (count == 0) {
                return '1';
            }else{
                return '2';
            }
        }

        char c1 = chars[loc];
        if (c1 == ')' || c1 == '}' || c1 == ']') {
            loc++;
            return c1;
        }
        //count用于计数，记录有几个左括号需要匹配
        count++;
        loc++;
        char c2 = recursion(chars);

        if (c2 == '2') {
            return '2';
        }

        if (c2 == ')' && c1 != '(') {
            return '2';
        } else if (c2 == ']' && c1 != '[') {
            return '2';
        } else if (c2 == '}' && c1 != '{') {
            return '2';
        }

        //匹配成功就让count减少
        count--;
        return recursion(chars);
    }
}
