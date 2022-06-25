package 复习.回溯;

import java.util.ArrayList;
import java.util.List;

public class No4 {
    /**
     *      括号生成
     */

    /*List<String> res;
    int num;
    public List<String> generateParenthesis(int n){
        res = new ArrayList<>();
        num = n;
        create(new StringBuilder(),0,0);
        return res;
    }
    public void create(StringBuilder sb,int left,int count){
        if (count == num) {
            res.add(sb.toString());
            return;
        }

        if (left + count < num) {
            sb.append('(');
            create(sb,left+1,count);
            sb.deleteCharAt(sb.length()-1);
        }
        if (left > 0) {
            sb.append(')');
            create(sb,left-1,count+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/

    //动态规划解法：由(n-1)的数量推出n的数量
    /*public List<String> generateParenthesis(int n){
        List<List<String>> temp = new ArrayList<>(n+1);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            temp.add(new ArrayList<>());
        }
        temp.get(0).add("");
        temp.get(1).add("()");
        for (int i = 2; i < n + 1; i++) {
            int j = i-1,k;
            while (j >= 0) {
                for (String s1 : temp.get(j)) {
                    k = i-1-j;
                    for (String s2 : temp.get(k)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("(").append(s1).append(")").append(s2);
                        temp.get(i).add(sb.toString());
                    }
                }
                j--;
            }
        }

        for (String s : temp.get(n)) {
            res.add(s);
        }

        return res;
    }*/
}
