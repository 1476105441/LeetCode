package 高级算法.动态规划;

import java.util.ArrayList;
import java.util.List;

public class No5 {
    /**
     *      单词拆分II
     */

    //我的想法是应该像树一样把这个过程存储下来
    char[] sc;
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
    }
}
class Node{
    public List<Node> parents;
    public char[] string;
}
