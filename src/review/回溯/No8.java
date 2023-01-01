package review.回溯;

import java.util.ArrayList;
import java.util.List;

public class No8 {
    /**
     *      分割回文串
     */

    //先不结合任何一个，然后再逐渐往后结合
    /*List<List<String>> res;
    char[] sChar;
    public List<List<String>> partition(String s){
        res = new ArrayList<>();
        sChar = s.toCharArray();
        recursion();
        return res;
    }
    public void recursion(List<List<String>> preList,int loc){
        if (loc == sChar.length) {

            return;
        }
    }
    public boolean examine(String s){
        char[] chars = s.toCharArray();
        int i = 0,j = chars.length-1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }*/


    //动态规划+回溯
    /*boolean[][] dp;
    int n;
    String s;
    List<List<String>> res;
    public List<List<String>> partition(String s){
        char[] chars = s.toCharArray();
        n = chars.length;
        res = new ArrayList<>();
        this.s = s;
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                dp[i][j] = true;
            }
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i+l-1;
                dp[i][j] = chars[i] == chars[j] && dp[i+1][j-1];
            }
        }
        dfs(0,new ArrayList<>());
        return res;
    }
    public void dfs(int start,List<String> temp){
        if (start == n) {
            //注意细节，此处不能直接将temp赋值进去，因
            //为直接赋值的话res中存的都是一个List对象
            //严格来讲存的都是同一个对象的引用。
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < n; i++) {
            if (dp[start][i]) {
                temp.add(s.substring(start,i+1));
                dfs(i+1,temp);
                temp.remove(temp.size()-1);
            }
        }
    }*/

    //再次重写一遍
    //先动态规划判断是否是回文串，然后再回溯子串来判断
    List<List<String>> res;
    boolean[][] dp;
    char[] chars;
    String s;
    public List<List<String>> partition(String s){
        res = new ArrayList<>();
        chars = s.toCharArray();
        this.s = s;
        dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                dp[i][j] = true;
            }
        }

        //动态规划计算出从i到j的字符串是否为回文串
        for (int l = 1; l < chars.length; l++) {
            for (int i = 0; i < chars.length - l; i++) {
                int j = i + l;
                dp[i][j] = chars[i] == chars[j] && dp[i+1][j-1];
            }
        }

        dfs(0,new ArrayList<>());

        return res;
    }
    public void dfs(int loc,List<String> list){
        if (loc == chars.length) {
            List<String> temp = new ArrayList<>(list);
            res.add(temp);
            return;
        }

        for (int i = loc; i < chars.length; i++) {
            if (dp[loc][i]) {
                list.add(s.substring(loc,i+1));
                dfs(i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
}
