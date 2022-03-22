package 高级算法.回溯算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No2 {
    /**
     *          单词搜索 II
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * 提示：
     *     m == board.length
     *     n == board[i].length
     *     1 <= m, n <= 12
     *     board[i][j] 是一个小写英文字母
     *     1 <= words.length <= 3 * 104
     *     1 <= words[i].length <= 10
     *     words[i] 由小写英文字母组成
     *     words 中的所有字符串互不相同
     */

    /**
     * 初步的想法：
     * 将单词以首字母进行区分，分成26个区，
     * 每次遍历到当前字符，从当前字符的分
     * 区中拿出字符串
     */

    //失败，超出时间限制
    /*List<char[]>[] lists;
    List<String> res;
    Map<char[],Integer> isOk;
    public List<String> findWords(char[][] board, String[] words) {
        lists = new List[26];
        res = new ArrayList<>();
        isOk = new HashMap<>();
        if (board.length == 1 && board[0].length == 1) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() == 1 && words[i].charAt(0) == board[0][0]) {
                    res.add(words[i]);
                    return res;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            lists[chars[0]-'a'].add(chars);
            isOk.put(chars,0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                List<char[]> temp = lists[board[i][j]-'a'];
                if(!temp.isEmpty()){
                    char c = board[i][j];
                    board[i][j] = '*';
                    dfs(temp,i-1,j,1,board);
                    dfs(temp,i,j-1,1,board);
                    dfs(temp,i+1,j,1,board);
                    dfs(temp,i,j+1,1,board);
                    board[i][j] = c;
                }
            }
        }
        return res;
    }

    public void dfs(List<char[]> temp,int i,int j,int loc,char[][] board){
        if (temp.isEmpty() || i<0 || j<0 || i==board.length || j==board[0].length || board[i][j]=='*') {
            return;
        }
        char c = board[i][j];
        board[i][j] = '*';
        List<char[]> temp1 = new ArrayList<>();
        for (int k = 0; k < temp.size(); k++) {
            char[] ctemp = temp.get(k);
            if (isOk.get(ctemp) != 1) {
                if (loc == ctemp.length) {
                    res.add(new String(ctemp));
                    isOk.put(ctemp, 1);
                }else if(c == ctemp[loc]){
                    if (loc == ctemp.length - 1) {
                        res.add(new String(ctemp));
                        isOk.put(ctemp, 1);
                    }else{
                        temp1.add(ctemp);
                    }
                }
            }
        }
        dfs(temp1,i-1,j,loc+1,board);
        dfs(temp1,i+1,j,loc+1,board);
        dfs(temp1,i,j-1,loc+1,board);
        dfs(temp1,i,j+1,loc+1,board);

        board[i][j] = c;
    }*/

    //使用前缀树实现我第一次的想法
    List<String> res;
    public List<String> findWords(char[][] board, String[] words) {
        res = new ArrayList<>();
        //1、将字符串装入到前缀树中
        Trie root = new Trie();
        for (int i = 0; i < words.length; i++) {
            root.insert(words[i]);
        }

        //2、深度优先遍历
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isEmpty(root)) {
                    dfs(board,root,i,j);
                }
            }
        }

        return res;
    }

    public void dfs(char[][] board,Trie now,int i,int j){
        if(i==board.length||i<0||j<0||j==board[0].length||board[i][j]=='*'){
            return;
        }
        int loc = board[i][j] - 'a';
        if (now.next[loc] == null) {
            return;
        }
        char c = board[i][j];
        board[i][j] = '*';

        if (now.next[loc].isEnd) {
            res.add(now.next[loc].word);
            now.next[loc].isEnd = false;
            if (isEmpty(now.next[loc])) {
                now.next[loc] = null;
            }
        }

        if (now.next[loc] != null) {
            dfs(board,now.next[loc],i-1,j);
            dfs(board,now.next[loc],i+1,j);
            dfs(board,now.next[loc],i,j-1);
            dfs(board,now.next[loc],i,j+1);
        }

        board[i][j] = c;
    }

    public boolean isEmpty(Trie now){
        for (int k = 0; k < 26; k++) {
            if (now.next[k] != null) {
                return false;
            }
        }
        return true;
    }
}
