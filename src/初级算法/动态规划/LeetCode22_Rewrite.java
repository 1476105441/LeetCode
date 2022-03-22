package 初级算法.动态规划;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22_Rewrite {
    public static void main(String[] args) {
        List<String> list = generateParenthesis(4);
        for(String s:list){
            System.out.println(s);
        }
    }
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        String s;

        //先添加一个左括号
        s = "(";
        function(n, 1, 0, list, s);
        return list;
    }

    //回溯算法的函数,n就是需要的括号对数，left是左括号数，right是右括号数，list就是要return的集合,s是当前拼接的字符串
    //思路：程序开始时，有一个左括号，此时，可以选择添加左括号或者右括号，如果左括号数量等于右括号，就只能选择添加左括号，
    // 左括号大于右括号就可以任意添加
    public static void function(int n, int left, int right, List<String> list, String s) {
        //先给出回溯出口
        if (left == n) {
            //如果此时括号还没有匹配完，补上缺的右括号
            while (right < left) {
                s += ")";
                right++;
            }
            list.add(s);
            return;
        }

        //左右括号数相等
        if (left == right) {
            s += "(";
            left++;
            function(n, left, right, list, s);
        } else {
            String s1, s2;
            int left1,right2;
            //进行添加左括号，用局部变量为了不影响后一个添加右括号
            s1 = s + "(";
            left1 = left+1;
            function(n,left1,right,list,s1);
            //添加右括号
            s2 = s + ")";
            right2 = right +1;
            function(n,left,right2,list,s2);
        }
    }
}
