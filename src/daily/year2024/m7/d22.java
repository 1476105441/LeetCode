package daily.year2024.m7;

import java.util.ArrayList;
import java.util.List;

public class d22 {
    int n;
    List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        this.n = n;
        dfsGenerate(0,0,new StringBuilder());
        return res;
    }

    //生成一个左括号leftC就加1，生成一个右括号leftC就减1
    private void dfsGenerate(int leftCount,int pairCount,StringBuilder tmp) {
        if(pairCount == n) {
            res.add(tmp.toString());
            return;
        }
        if(leftCount != 0) {
            tmp.append(')');
            dfsGenerate(leftCount-1,pairCount+1,tmp);
            tmp.deleteCharAt(tmp.length()-1);
        }
        if(leftCount + pairCount < n) {
            tmp.append('(');
            dfsGenerate(leftCount+1,pairCount,tmp);
            tmp.deleteCharAt(tmp.length()-1);
        }
    }
}