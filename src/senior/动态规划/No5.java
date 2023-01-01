package senior.动态规划;

import java.util.*;

public class No5 {
    /**
     *      单词拆分II
     */

    //我的想法是应该像树一样把这个过程存储下来
    /*char[] sc;
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        sc = s.toCharArray();
        res = new ArrayList<>();
        int n = sc.length,m = wordDict.size();
        List<char[]> words = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            words.add(wordDict.get(i).toCharArray());
        }

        boolean[] dp = new boolean[n+1];
        List<Node>[] nodes = new List[n+1];
        dp[0] = true;
        nodes[0] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                char[] temp = words.get(j);
                if (temp.length <= i) {
                    if (dp[i - temp.length] && check(i - temp.length, temp)) {
                        //将每个字符串关联起来
                        Node node = new Node();
                        node.string = temp;
                        node.parents = new ArrayList<>();
                        for (Node parent : nodes[i - temp.length]) {
                            node.parents.add(parent);
                        }
                        nodes[i].add(node);
                        dp[i] = true;
                    }
                }
            }
        }

        //回溯构造答案
        if(!nodes[n].isEmpty())
            for (Node node : nodes[n]) {
                find(node,new ArrayList<>());
            }
        return res;
    }

    public void find(Node node,List<char[]> list){
        if (node.parents == null || node.parents.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int i = list.size()-1;
            sb.append(node.string);
            for (;i > -1;i--) {
                sb.append(" ");
                sb.append(list.get(i));
            }
            res.add(sb.toString());
            return;
        }
        list.add(node.string);
        for (Node parent : node.parents) {
            find(parent,list);
        }
        list.remove(list.size()-1);
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

    //回溯，没搞出来，脑子晕了
    /*char[] c;
    int n;
    boolean[] set;
    List<char[]> word;
    List<List<char[]>> ansTemp;
    public List<String> wordBreak(String s, List<String> wordDict) {
        c = s.toCharArray();
        n = c.length;
        set = new boolean[n];
        word = new ArrayList<>(wordDict.size());
        ansTemp = new ArrayList<>(n);

        for (String t : wordDict) {
            word.add(t.toCharArray());
        }
        for (int i = 0; i < n; i++) {
            ansTemp.add(new ArrayList<>());
        }
        dfs(0,new ArrayList<>());
        List<String> res = new ArrayList<>();
        build(res,n-1,new ArrayList<>());
        return res;
    }

    public void dfs(int loc,List<char[]> list){
        if(loc == n){
            return;
        }

        for (char[] chars : word) {
            int nl = loc + chars.length - 1;
            if (nl >= n || set[nl]) {
                continue;
            }
            int i = loc,j = 0;
            boolean flag = true;
            while (j < chars.length) {
                if (c[i] != chars[j]) {
                    flag = false;
                    break;
                }
                j++;
            }
            if (flag) {

            }
        }
        set[loc] = true;
    }
    public void build(List<String> res,int loc,List<char[]> t){
        if (loc == -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = t.size()-1; i > -1; i--) {
                sb.append(t.get(i)).append(" ");
            }
            res.add(sb.toString());
            return;
        }
        for (char[] chars : ansTemp.get(loc)) {
            int newLoc = loc - chars.length;
            t.add(chars);
            build(res,newLoc,t);
            t.remove(t.size()-1);
        }
    }*/

    //重来，我就不信了，终于成功了，1ms
    /*char[] c;
    int n;
    int[] set;
    List<Node>[] nodes;
    List<char[]> words;
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        c = s.toCharArray();
        n = c.length;
        set = new int[n+1];
        nodes = new List[n];
        words = new ArrayList<>(wordDict.size());

        wordDict.forEach(x ->{
            words.add(x.toCharArray());
        });

        dfs(0);
        res = new ArrayList<>();

        build(n-1,new ArrayList<>());

        return res;
    }
    public boolean dfs(int loc){
        if(loc == n)
            return true;

        boolean stamp = false;
        for (char[] word : words) {
            int nl = loc + word.length -1;
            if(nl >= n || set[nl+1] == -1)
                continue;
            int i = loc;
            boolean flag = true;
            for (int j = 0; j < word.length; j++) {
                if (c[i] != word[j]) {
                    flag = false;
                    break;
                }
                i++;
            }
            if (flag) {
                if(nodes[i-1] == null)
                    nodes[i-1] = new ArrayList<>();
                Node node = new Node();
                node.string = word;
                nodes[i-1].add(node);
                if (set[nl + 1] == 0) {
                    if (dfs(nl+1)) {
                        set[nl+1] = 1;
                        stamp = true;
                    }else
                        set[nl+1] = -1;
                }else
                    stamp = true;
            }
        }
        return stamp;
    }

    public void build(int loc, List<char[]> chars) {
        if (loc < 0) {
            StringBuilder sb = new StringBuilder(n);
            for (int i = chars.size()-1; i > -1; i--) {
                sb.append(chars.get(i));
                if(i != 0)
                    sb.append(" ");
            }
            res.add(sb.toString());
            return;
        }
        List<Node> list = nodes[loc];
        if(list == null)
            return;
        for (Node node : list) {
            chars.add(node.string);
            build(loc-node.string.length,chars);
            chars.remove(chars.size()-1);
        }
    }*/

    //动态规划解法
    public List<String> wordBreak(String s, List<String> wordDict) {
        char[] c = s.toCharArray();
        int n = c.length;
        List<char[]> words = new ArrayList<>(wordDict.size());
        List<char[]>[] nodes = new List[n];
        List<String> res = new ArrayList<>();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for (String t : wordDict) {
            words.add(t.toCharArray());
        }

        for (int i = 0; i < n; i++) {
            for (char[] word : words) {
                if(i + 1 < word.length || !dp[i+1-word.length])
                    continue;
                if (match(i, word, c)) {
                    dp[i+1] = true;
                    if(nodes[i] == null)
                        nodes[i] = new ArrayList<>();
                    nodes[i].add(word);
                }
            }
        }
        build(n-1,new ArrayList<>(),res,nodes);

        return res;
    }
    public boolean match(int loc,char[] temp,char[] c){
        for (int i = temp.length - 1; i > -1; i--) {
            if(c[loc] != temp[i])
                return false;
            loc--;
        }
        return true;
    }

    public void build(int loc, List<char[]> list, List<String> res,List<char[]>[] nodes) {
        if (loc < 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = list.size() - 1; i > -1; i--) {
                sb.append(list.get(i));
                if(i > 0)
                    sb.append(" ");
            }
            res.add(sb.toString());
            return;
        }
        if(nodes[loc] == null)
            return;
        for (char[] chars : nodes[loc]) {
            list.add(chars);
            build(loc-chars.length,list,res,nodes);
            list.remove(list.size()-1);
        }
    }
}
class Node{
    //public List<Node> parents;
    public char[] string;
}
