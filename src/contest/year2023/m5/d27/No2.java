package contest.year2023.m5.d27;

import java.util.ArrayList;
import java.util.List;

/**
 * 2707.字符串中的额外字符
 *
 * @author wjs 2023/8/16
 */
public class No2 {
    //使用前缀树，暴力解法，想的还是简单了，失败。
    /*public int minExtraChar(String s, String[] dictionary) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        TrieTree root = new TrieTree();
        int m = dictionary.length;
        int res = 0;
        for(int i=0;i < m;i++) {
            buildTree(root,dictionary[i]);
        }
        for(int i=0;i < n;i++) {
            int l = match(root,i,chars);
            if(l == -1) {
                res++;
            } else {
                i = l;
            }
        }
        return res;
    }

    //如果从当前字符开始能够匹配上，则返回最后一个能够匹配的位置；如果无法进行匹配，则返回-1
    private int match(TrieTree root, int loc, char[] chars) {
        int res = -1;
        TrieTree node = root;
        for(int i=loc;i < chars.length;i++) {
            int l = chars[i] - 'a';
            if(node.next[l] == null) {
                break;
            }
            node = node.next[l];
            if(node.isEnd) {
                res = i;
            }
        }
        return res;
    }

    private void buildTree(TrieTree root, String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        TrieTree node = root;
        for(int i=0;i < n;i++) {
            int loc = chars[i] - 'a';
            if(node.next[loc] == null) {
                node.next[loc] = new TrieTree();
            }
            node = node.next[loc];
        }
        node.isEnd = true;
    }

    class TrieTree {
        TrieTree[] next = new TrieTree[26];
        boolean isEnd = false;
    }*/

    //超时
    /*TrieTree root;
    public int minExtraChar(String s, String[] dictionary) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        root = new TrieTree();
        int m = dictionary.length;
        for(int i=0;i < m;i++) {
            buildTree(dictionary[i]);
        }
        //遍历每个元素时有两种选择：1、选择从当前元素开始匹配；2、忽略当前元素
        return recursion(chars,0,0);
    }

    private int recursion(char[] chars, int loc, int cnt) {
        int n = chars.length;
        if(loc == n) {
            return cnt;
        }
        //c1统计选择当前元素，c2统计忽略当前元素
        int c1, c2 = Integer.MAX_VALUE;
        c1 = recursion(chars,loc+1,cnt+1);
        List<Integer> list = match(loc,chars);
        if(list.isEmpty()) {
            return c1;
        }
        for(Integer next : list) {
            c2 = Math.min(c2,recursion(chars,next+1,cnt));
        }
        return Math.min(c1,c2);
    }

    //如果从当前字符开始能够匹配上，则返回最后一个能够匹配的位置；如果无法进行匹配，则返回-1
    private List<Integer> match(int loc, char[] chars) {
        List<Integer> res = new ArrayList<>();
        TrieTree node = root;
        for(int i=loc;i < chars.length;i++) {
            int l = chars[i] - 'a';
            if(node.next[l] == null) {
                break;
            }
            node = node.next[l];
            if(node.isEnd) {
                res.add(i);
            }
        }
        return res;
    }

    private void buildTree(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        TrieTree node = root;
        for(int i=0;i < n;i++) {
            int loc = chars[i] - 'a';
            if(node.next[loc] == null) {
                node.next[loc] = new TrieTree();
            }
            node = node.next[loc];
        }
        node.isEnd = true;
    }

    class TrieTree {
        TrieTree[] next = new TrieTree[26];
        boolean isEnd = false;
    }*/

    TrieTree root;
    boolean[] vis;
    int[] count;
    public int minExtraChar(String s, String[] dictionary) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        vis = new boolean[n];
        count = new int[n];
        root = new TrieTree();
        int m = dictionary.length;
        for(int i=0;i < m;i++) {
            buildTree(dictionary[i]);
        }
        //遍历每个元素时有两种选择：1、选择从当前元素开始匹配；2、忽略当前元素
        return recursion(chars,0);
    }

    private int recursion(char[] chars, int loc) {
        int n = chars.length;
        if(loc == n) {
            return 0;
        }
        if(vis[loc]) {
            return count[loc];
        }
        vis[loc] = true;
        //c1统计选择当前元素，c2统计忽略当前元素
        int c1, c2 = Integer.MAX_VALUE;
        c1 = recursion(chars,loc+1) + 1;
        List<Integer> list = match(loc,chars);
        if(list.isEmpty()) {
            count[loc] = c1;
            return c1;
        }
        for(Integer next : list) {
            c2 = Math.min(c2,recursion(chars,next+1));
        }
        count[loc] = Math.min(c1,c2);
        return count[loc];
    }

    //如果从当前字符开始能够匹配上，则返回最后一个能够匹配的位置；如果无法进行匹配，则返回-1
    private List<Integer> match(int loc, char[] chars) {
        List<Integer> res = new ArrayList<>();
        TrieTree node = root;
        for(int i=loc;i < chars.length;i++) {
            int l = chars[i] - 'a';
            if(node.next[l] == null) {
                break;
            }
            node = node.next[l];
            if(node.isEnd) {
                res.add(i);
            }
        }
        return res;
    }

    private void buildTree(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        TrieTree node = root;
        for(int i=0;i < n;i++) {
            int loc = chars[i] - 'a';
            if(node.next[loc] == null) {
                node.next[loc] = new TrieTree();
            }
            node = node.next[loc];
        }
        node.isEnd = true;
    }

    class TrieTree {
        TrieTree[] next = new TrieTree[26];
        boolean isEnd = false;
    }
}
