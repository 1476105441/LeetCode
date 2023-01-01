package review.中级算法复习.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    //              括号生成

    //  回溯算法进行解决，每次有两种选择：添加左括号或者添加右括号
    //左括号数量达到n时不可再添加，右括号数量和左括号一样时不可再添
    //加
    List<String> res;
    public List<String> generateParenthesis(int n){
        res = new ArrayList<>();
        recursion(n,0,0,new StringBuilder());
        return res;
    }

    //采用递归函数
    public void recursion(int n,int left,int right,StringBuilder sb){
        int length = sb.length();
        if (left == n && right == n) {
            res.add(sb.toString());
            //sb.deleteCharAt(length-1);
            return;
        }

        if (left < n) {
            sb.append("(");
            recursion(n,left+1,right,sb);
            sb.deleteCharAt(length);
        }
        if (right < left) {
            sb.append(")");
            recursion(n,left,right+1,sb);
            sb.deleteCharAt(length);
        }
    }
}
