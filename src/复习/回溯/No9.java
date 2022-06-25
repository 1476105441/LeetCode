package 复习.回溯;

import java.util.*;

public class No9 {
    /**
     *      单词搜索II
     */

    //使用set去重，时间有点慢，300多ms
    /*Set<String> set;
    int[][] used;
    char[][] board;
    public List<String> findWords(char[][] board,String[] words){
        List<String> res;
        set = new HashSet<>();
        used = new int[board.length][board[0].length];
        this.board = board;

        //构造前缀树
        preTree head = new preTree(),tree;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            tree = head;
            for (int j = 0; j < chars.length; j++) {
                int loc = chars[j] - 'a';
                if (tree.trees[loc] == null) {
                    tree.trees[loc] = new preTree();
                }
                tree = tree.trees[loc];
            }
            tree.end = true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int loc = board[i][j]-'a';
                if (head.trees[loc] != null) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i,j,head.trees[loc], sb);
                }
            }
        }

        res = new ArrayList<>(set);

        return res;
    }

    public void dfs(int i,int j,preTree now,StringBuilder sb){
        if (used[i][j] == 1) {
            return;
        }
        sb.append(board[i][j]);
        if (now.end) {
            set.add(sb.toString());
        }

        used[i][j] = 1;
        if (i > 0 && now.trees[board[i-1][j]-'a'] != null) {
            dfs(i-1,j,now.trees[board[i-1][j]-'a'],sb);
        }
        if (j > 0 && now.trees[board[i][j-1]-'a'] != null) {
            dfs(i,j-1,now.trees[board[i][j-1]-'a'],sb);
        }
        if (i < board.length-1 && now.trees[board[i+1][j]-'a'] != null) {
            dfs(i+1,j,now.trees[board[i+1][j]-'a'],sb);
        }
        if (j < board[0].length-1 && now.trees[board[i][j+1]-'a'] != null) {
            dfs(i,j+1,now.trees[board[i][j+1]-'a'],sb);
        }
        used[i][j] = 0;
        sb.deleteCharAt(sb.length()-1);

    }*/

    //不使用set，100多ms
    /*List<String> res;
    int[][] used;
    char[][] board;
    public List<String> findWords(char[][] board,String[] words){
        res = new ArrayList<>();
        used = new int[board.length][board[0].length];
        this.board = board;

        //构造前缀树
        preTree head = new preTree(),tree;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            tree = head;
            for (int j = 0; j < chars.length; j++) {
                int loc = chars[j] - 'a';
                if (tree.trees[loc] == null) {
                    tree.trees[loc] = new preTree();
                }
                tree = tree.trees[loc];
            }
            tree.end = true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int loc = board[i][j]-'a';
                if (head.trees[loc] != null) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i,j,head.trees[loc], sb);
                }
            }
        }

        return res;
    }

    public void dfs(int i,int j,preTree now,StringBuilder sb){
        if (used[i][j] == 1) {
            return;
        }
        sb.append(board[i][j]);
        if (now.end) {
            res.add(sb.toString());
            now.end = false;
        }

        used[i][j] = 1;
        if (i > 0 && now.trees[board[i-1][j]-'a'] != null) {
            dfs(i-1,j,now.trees[board[i-1][j]-'a'],sb);
        }
        if (j > 0 && now.trees[board[i][j-1]-'a'] != null) {
            dfs(i,j-1,now.trees[board[i][j-1]-'a'],sb);
        }
        if (i < board.length-1 && now.trees[board[i+1][j]-'a'] != null) {
            dfs(i+1,j,now.trees[board[i+1][j]-'a'],sb);
        }
        if (j < board[0].length-1 && now.trees[board[i][j+1]-'a'] != null) {
            dfs(i,j+1,now.trees[board[i][j+1]-'a'],sb);
        }
        used[i][j] = 0;
        sb.deleteCharAt(sb.length()-1);
    }*/

    //并没有提升，看来瓶颈不在标记数组上
    /*List<String> res;
    char[][] board;
    public List<String> findWords(char[][] board,String[] words){
        res = new ArrayList<>();
        this.board = board;

        //构造前缀树
        preTree head = new preTree(),tree;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            tree = head;
            for (int j = 0; j < chars.length; j++) {
                int loc = chars[j] - 'a';
                if (tree.trees[loc] == null) {
                    tree.trees[loc] = new preTree();
                }
                tree = tree.trees[loc];
            }
            tree.end = true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int loc = board[i][j]-'a';
                if (head.trees[loc] != null) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i,j,head, sb);
                }
            }
        }

        return res;
    }

    public void dfs(int i,int j,preTree now,StringBuilder sb){
        if (board[i][j] == '*' || now.trees[board[i][j]-'a'] == null) {
            return;
        }
        now = now.trees[board[i][j]-'a'];

        sb.append(board[i][j]);
        if (now.end) {
            res.add(sb.toString());
            now.end = false;
        }

        char temp = board[i][j];
        board[i][j] = '*';
        if (i > 0 ) {
            dfs(i-1,j,now,sb);
        }
        if (j > 0) {
            dfs(i,j-1,now,sb);
        }
        if (i < board.length-1) {
            dfs(i+1,j,now,sb);
        }
        if (j < board[0].length-1) {
            dfs(i,j+1,now,sb);
        }
        board[i][j] = temp;
        sb.deleteCharAt(sb.length()-1);
    }*/

    //瓶颈在于StringBuilder，将前缀树的结构做
    //一个改变就可以大幅提升速度，以空间换时间，90多ms
    /*List<String> res;
    char[][] board;
    public List<String> findWords(char[][] board,String[] words){
        res = new ArrayList<>();
        this.board = board;

        //构造前缀树
        preTree head = new preTree(),tree;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            tree = head;
            for (int j = 0; j < chars.length; j++) {
                int loc = chars[j] - 'a';
                if (tree.trees[loc] == null) {
                    tree.trees[loc] = new preTree();
                }
                tree = tree.trees[loc];
            }
            tree.end = true;
            tree.word = words[i];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int loc = board[i][j]-'a';
                if (head.trees[loc] != null) {
                    dfs(i,j,head);
                }
            }
        }

        return res;
    }

    public void dfs(int i,int j,preTree now){
        if (board[i][j] == '*' || now.trees[board[i][j]-'a'] == null) {
            return;
        }
        now = now.trees[board[i][j]-'a'];

        if (now.end) {
            res.add(now.word);
            now.end = false;
        }

        char temp = board[i][j];
        board[i][j] = '*';
        if (i > 0 ) {
            dfs(i-1,j,now);
        }
        if (j > 0) {
            dfs(i,j-1,now);
        }
        if (i < board.length-1) {
            dfs(i+1,j,now);
        }
        if (j < board[0].length-1) {
            dfs(i,j+1,now);
        }
        board[i][j] = temp;
    }*/

    //还可以增加剪枝策略，时间进一步缩小
    List<String> res;
    char[][] board;
    public List<String> findWords(char[][] board,String[] words){
        res = new ArrayList<>();
        this.board = board;

        //构造前缀树
        preTree head = new preTree(),tree;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            tree = head;
            for (int j = 0; j < chars.length; j++) {
                int loc = chars[j] - 'a';
                if (tree.trees[loc] == null) {
                    tree.trees[loc] = new preTree();
                }
                tree = tree.trees[loc];
            }
            tree.end = true;
            tree.word = words[i];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int loc = board[i][j]-'a';
                //剪枝策略在这里，时间更进一步缩减，39ms
                if (head.trees[loc] != null) {
                    dfs(i,j,head);
                }
            }
        }

        return res;
    }

    public void dfs(int i,int j,preTree now){
        if (board[i][j] == '*' || now.trees[board[i][j]-'a'] == null) {
            return;
        }
        preTree node = now.trees[board[i][j]-'a'];

        if (node.end) {
            res.add(node.word);
            node.end = false;
            if (isEmpty(node)) {
                now.trees[board[i][j]-'a'] = null;
            }
        }

        char temp = board[i][j];
        board[i][j] = '*';
        if (i > 0 ) {
            dfs(i-1,j,node);
        }
        if (j > 0) {
            dfs(i,j-1,node);
        }
        if (i < board.length-1) {
            dfs(i+1,j,node);
        }
        if (j < board[0].length-1) {
            dfs(i,j+1,node);
        }
        board[i][j] = temp;
    }

    public boolean isEmpty(preTree tree){
        for (int i = 0; i < 26; i++) {
            if (tree.trees[i] != null) {
                return false;
            }
        }
        return true;
    }
}

class preTree{
    preTree[] trees;
    boolean end;
    String word;
    public preTree(){
        trees = new preTree[26];
        end = false;
    }
}
