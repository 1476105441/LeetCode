package review.回溯;

public class No7 {
    /**
     *      单词搜索
     */

    //未剪枝版本
    /*int[][] used;
    char[][] board;
    char[] chars;
    public boolean exist(char[][] board,String word){
        chars = word.toCharArray();
        this.board = board;
        used = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0]) {
                    if (find(i,j,0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean find(int row,int col,int loc){
        if (loc == chars.length) {
            return true;
        }
        if (row < 0 || col < 0 || row == board.length || col == board[0].length || used[row][col] == 1) {
            return false;
        }

        boolean flag = false;
        if (board[row][col] == chars[loc]) {
            used[row][col] = 1;
            flag = find(row+1,col,loc+1) || find(row,col+1,loc+1) ||
                    find(row-1,col,loc+1) ||find(row,col-1,loc+1);
            used[row][col] = 0;
        }

        return flag;
    }*/

    //换种写法提升速度
    char[][] board;
    char[] chars;
    int[][] used;
    public boolean exist(char[][] board,String word){
        chars = word.toCharArray();
        used = new int[board.length][board[0].length];
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (find(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(int row,int col,int loc){
        if (board[row][col] != chars[loc] || used[row][col] == 1) {
            return false;
        }
        if (loc == chars.length - 1) {
            return true;
        }

        used[row][col] = 1;
        boolean f1=false,f2=false,f3=false,f4=false;
        if (row > 0 && used[row-1][col] == 0) {
            f1 = find(row-1,col,loc+1);
        }
        if (row < board.length - 1 && used[row+1][col] == 0) {
            f2 = find(row+1,col,loc+1);
        }
        if (col > 0 && used[row][col-1] == 0) {
            f3 = find(row,col-1,loc+1);
        }
        if (col < board[0].length-1 && used[row][col+1] == 0) {
            f4 = find(row,col+1,loc+1);
        }
        used[row][col] = 0;
        return f1||f2||f3||f4;
    }
}
