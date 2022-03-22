package 中级算法复习.回溯算法;

public class No5 {
    //          单词搜索

    int[][] flags;
    int count;
    char[] chars;

    public boolean exist(char[][] board, String word) {
        flags = new int[board.length][board[0].length];
        chars = word.toCharArray();
        count = 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0]) {
                    count++;
                    if (find(board,i,j,1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean find(char[][] board, int row, int col, int loc) {
        if (loc == chars.length) {
            return true;
        }

        boolean flag;
        flags[row][col] = count;
        if (row > 0 && flags[row - 1][col] != count && board[row - 1][col] == chars[loc]) {
            flag = find(board, row - 1, col, loc + 1);
            if (flag) {
                return true;
            }
            flags[row-1][col] = 0;
        }
        if (col > 0 && flags[row][col - 1] != count && board[row][col - 1] == chars[loc]) {
            flag = find(board, row, col - 1, loc + 1);
            if (flag) {
                return true;
            }
            flags[row][col-1] = 0;
        }
        if (row < board.length - 1 && flags[row + 1][col] != count && board[row + 1][col] == chars[loc]) {
            flag = find(board, row + 1, col, loc + 1);
            if (flag) {
                return true;
            }
            flags[row+1][col] = 0;
        }
        if (col < board[row].length - 1 && flags[row][col + 1] != count && board[row][col + 1] == chars[loc]) {
            flag = find(board,row,col+1,loc+1);
            flags[row][col+1] = 0;
            return flag;
        }

        return false;
    }
}
