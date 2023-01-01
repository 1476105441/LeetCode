package review.树和图;

import java.util.LinkedList;
import java.util.Queue;

public class No13 {
    /**
     *      被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找
     * 到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     */

    /*boolean[][] flags;
    public void solve(char[][] board){
        flags = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O' && !flags[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    if (!search(board, i, j, list)) {
                        for (int k = 0; k < list.size()-1; k+=2) {
                            board[list.get(k)][list.get(k+1)] = 'X';
                        }
                    }
                }
            }
        }
    }

    public boolean search(char[][] board, int i, int j, List<Integer> list){
        if(i == 0 || j == 0|| i == board.length-1 || j == board[0].length-1){
            //flags[i][j] = true;
            return true;
        }

        boolean flag1,flag2,flag3,flag4;
        flags[i][j] = true;
        if(board[i - 1][j] == 'O' && !flags[i-1][j]){
            flag1 = search(board,i-1,j,list);
        }else{
            flag1 = false;
        }

        if(board[i][j-1] == 'O' && !flags[i][j-1]){
            flag2 = search(board,i,j-1,list);
        }else {
            flag2 = false;
        }

        if(board[i][j+1] == 'O' && !flags[i][j+1]){
            flag3 = search(board,i,j+1,list);
        }else{
            flag3 = false;
        }

        if(board[i+1][j] == 'O' && !flags[i+1][j]){
            flag4 = search(board,i+1,j,list);
        }else{
            flag4 = false;
        }

        if (flag1 || flag2 || flag3 || flag4) {
            return true;
        }else{
            list.add(i);
            list.add(j);
            return false;
        }
    }*/

    //此题的最优解：逆向思维，先将不用改变值的O标记出来，然
    //后将剩下的O全部改变值
    /*public void solve(char[][] board){
        int n = board.length,m = board[0].length;

        for (int i = 0; i < n; i++) {
            dfs(board,i,0);
            dfs(board,i,m-1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board,0,i);
            dfs(board,n-1,i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board,int i,int j){
        if(i < 0 || j < 0 || i == board.length || j ==board[0].length || board[i][j] != 'O')
            return;

        board[i][j] = 'A';
        dfs(board,i-1,j);
        dfs(board,i,j-1);
        dfs(board,i+1,j);
        dfs(board,i,j+1);
    }*/

    //逆向思维的广度优先遍历版
    public void solve(char[][] board){
        int m = board.length,n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                bfs(board,i,0);
            }
            if(board[i][n-1] == 'O'){
                bfs(board,i,n-1);
            }
        }
        for (int i = 1; i < n-1; i++) {
            if (board[0][i] == 'O') {
                bfs(board,0,i);
            }
            if (board[m - 1][i] == 'O') {
                bfs(board,m-1,i);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(char[][] board,int i,int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        board[i][j] = 'A';

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            addQue(board,temp,queue);
        }
    }

    public void addQue(char[][] board,int[] val,Queue<int[]> queue){
        if (val[0] > 0 && board[val[0]-1][val[1]] == 'O') {
            queue.add(new int[]{val[0]-1,val[1]});
            board[val[0]-1][val[1]] = 'A';
        }
        if (val[1] > 0 && board[val[0]][val[1]-1] == 'O') {
            queue.add(new int[]{val[0],val[1]-1});
            board[val[0]][val[1]-1] = 'A';
        }
        if (val[0] < board.length-1 && board[val[0]+1][val[1]] == 'O') {
            queue.add(new int[]{val[0]+1,val[1]});
            board[val[0]+1][val[1]] = 'A';
        }
        if (val[1] < board[0].length-1 && board[val[0]][val[1]+1] == 'O') {
            queue.add(new int[]{val[0],val[1]+1});
            board[val[0]][val[1]+1] = 'A';
        }
    }
}
