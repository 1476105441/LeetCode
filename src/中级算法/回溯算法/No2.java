package 中级算法.回溯算法;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    //                  括号生成
    //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    //有效括号组合需满足：左括号必须以正确的顺序闭合。

    //----------------------------------------------------------------------
    //                      1ms
    /*List<String> res;
    public List<String> generateParenthesis(int n) {

        res = new ArrayList<>();

        recursion(0,1,n,"(");

        return res;
    }

    //注意不要改变传递的参数的值！！！！
    public void recursion(int count,int l,int n,String s){
        //匹配的括号对数等于需要的括号对数，存入结果集中
        if (count == n) {
            res.add(s);
            return;
        }

        //左括号数量等于括号对数，就不能再添加左括号了
        if (l == n) {
            recursion(count+1,l,n,s + ')');
            return;
        }

        //到了这里说明左括号数量没有到达上限，但是还要看前面的左括号是否已经全部配对完
        //左括号是随便可以加的
        recursion(count,l+1,n,s + '(');

        //右括号不能随便加，只有左括号没有完全匹配完才能加右括号
        if (l > count) {
            recursion(count+1,l,n,s + ')');
        }
    }*/
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    //                      试图改进改进
    //                      成功，0ms+1
    List<String> res;
    public List<String> generateParenthesis(int n) {

        res = new ArrayList<>();

        recursion(0,1,n,1,new StringBuilder("("));

        return res;
    }

    //注意不要改变传递的参数的值！！！！
    public void recursion(int count,int l,int n,int loc,StringBuilder s){
        //匹配的括号对数等于需要的括号对数，存入结果集中
        if (count == n) {
            res.add(s.toString());
            s.deleteCharAt(loc);
            return;
        }

        //左括号数量等于括号对数，就不能再添加左括号了
        if (l == n) {
            s.append(')');
            recursion(count+1,l,n,loc+1,s);
            s.deleteCharAt(loc);
            return;
        }

        //到了这里说明左括号数量没有到达上限，但是还要看前面的左括号是否已经全部配对完
        //左括号是随便可以加的
        s.append('(');
        recursion(count,l+1,n,loc+1,s);
        s.deleteCharAt(loc);

        //右括号不能随便加，只有左括号没有完全匹配完才能加右括号
        if (l > count) {
            s.append(')');
            recursion(count+1,l,n,loc+1,s);
            s.deleteCharAt(loc);
        }
    }
}
