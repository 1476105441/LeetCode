package 高级算法.动态规划;

import java.util.*;

public class No4 {
    /**
     *      单词拆分
     */

    //解法一：动态规划
    //使用dp[i][j]表示当前字符串的前i个字符能否使用第j个单词匹配上
    /*char[] sc;
    public boolean wordBreak(String s, List<String> wordDict) {
        sc = s.toCharArray();
        int n = sc.length,m = wordDict.size();
        boolean[] dp = new boolean[n+1];
        List<char[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(wordDict.get(i).toCharArray());
        }

        //初始化dp
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                char[] temp = list.get(j);
                if (i >= temp.length) {
                    if (dp[i-temp.length]&&examine(i - temp.length, temp)) {
                        dp[i] = true;
                    }
                }
                if(dp[i])
                    break;
            }
        }

        return dp[n];
    }
    public boolean examine(int loc,char[] c){
        int j = 0;
        for (int i = loc; i < sc.length && j < c.length; i++) {
            if(c[j] != sc[i])
                return false;
            j++;
        }
        return true;
    }*/

    //解法二：DFS，1ms
    /*char[] sc;
    boolean[] visited;
    public boolean wordBreak(String s, List<String> wordDict){
        sc = s.toCharArray();
        visited = new boolean[sc.length+1];
        List<char[]> list = new ArrayList<>();
        for (int i = 0; i < wordDict.size(); i++) {
            list.add(wordDict.get(i).toCharArray());
        }

        return dfs(list,0);
    }
    public boolean dfs(List<char[]> list,int loc){
        if(loc == sc.length)
            return true;
        for (char[] temp : list) {
            int nLoc = loc + temp.length-1;
            if (nLoc >= sc.length || visited[nLoc+1]) {
                continue;
            }
            if (check(loc, temp)) {
                if(dfs(list,nLoc+1))
                    return true;
                visited[nLoc+1] = true;
            }
        }
        return false;
    }
    public boolean check(int loc,char[] temp){
        int i = 0;
        for (int j = loc; j < sc.length && i < temp.length; j++) {
            if(sc[j] != temp[i])
                return false;
            i++;
        }
        return true;
    }*/

    //BFS，以下标为入栈的标记，初始时下标0在队列中，从下标0开始广度查
    //找是否有子串符合，符合就入队列
    char[] sc;
    public boolean wordBreak(String s, List<String> wordDict){
        Queue<Integer> queue = new LinkedList<>();

        sc = s.toCharArray();
        boolean[] visited = new boolean[sc.length+1];
        List<char[]> list = new ArrayList<>(wordDict.size());
        for (int i = 0; i < wordDict.size(); i++) {
            list.add(wordDict.get(i).toCharArray());
        }
        queue.add(0);
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n > 0) {
                Integer loc = queue.poll();
                for (char[] temp : list) {
                    int l = loc + temp.length;
                    if (l > sc.length || visited[l]) {
                        continue;
                    }
                    if (check(loc, temp)) {
                        if(l == sc.length)
                            return true;
                        queue.add(l);
                        visited[l] = true;
                    }
                }
                n--;
            }
        }

        return false;
    }
    public boolean check(int loc,char[] temp){
        int i = 0;
        for (int j = loc; j < sc.length && i < temp.length; j++) {
            if(sc[j] != temp[i])
                return false;
            i++;
        }

        return i == temp.length;
    }
}
