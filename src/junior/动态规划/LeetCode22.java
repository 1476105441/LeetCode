package junior.动态规划;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {
    public static void main(String[] args) {

    }

    public static void f(int n){
        if (n == 4) {
            return;
        }
        System.out.println(n);
        n++;
        f(n);
    }

    public static List<String> generateParenthesis(int n) {
        //这个数组为存储状态的数组,第0行不用
        List<String>[] list = new List[n+1];

        //第0行存储空字符串
        list[0] = new ArrayList<>();
        list[0].add("");

        //先确定括号只有一对时的情况
        list[1] = new ArrayList<>();
        list[1].add("()");

        //n为1时直接return
        if (n == 1) {
            return list[1];
        }

        for (int i = 2; i <= n; i++) {
            list[i] = new ArrayList<>();
            int p, q;
            for (p = 0; p <= i-1; p++) {
                //q+p=n-1,q和p代表了n-1的情况
                q = i-1-p;
                int p1=0,q1=0;
                while (p1 < list[p].size()) {
                    while (q1 < list[q].size()) {
                        list[i].add("(" + list[p].get(p1) + ")" + list[q].get(q1));
                        q1++;
                    }
                    p1++;
                    q1=0;
                }
            }
        }
        return list[n];
    }
}
