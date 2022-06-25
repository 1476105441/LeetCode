package 复习.数组;

public class No10 {
    /**
     *      有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     *     数字 1-9 在每一行只能出现一次。
     *     数字 1-9 在每一列只能出现一次。
     *     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *
     * 注意：
     *     一个有效的数独（部分已被填充）不一定是可解的。
     *     只需要根据以上规则，验证已经填入的数字是否有效即可。
     *     空白格用 '.' 表示。
     *
     * 提示：
     *     board.length == 9
     *     board[i].length == 9
     *     board[i][j] 是一位数字（1-9）或者 '.'
     */

    //使用数组存放
    /*public boolean isValidSudoku(char[][] board){
        int[][] col = new int[9][9],zero = new int[9][9];
        int loc;

        for (int i = 0; i < board.length; i++) {
            int[] row = new int[9];
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    loc = board[i][j] - '1';
                    //检查行的
                    if (row[loc] > 0) {
                        return false;
                    }else{
                        row[loc]++;
                    }

                    //检查列的
                    if (col[j][loc] > 0) {
                        return false;
                    }else {
                        col[j][loc]++;
                    }

                    //检查九宫格的，映射地址是(row/3)+(col/3)*3
                    int tempLoc = (i/3)+(j/3)*3;
                    if (zero[tempLoc][loc] > 0) {
                        return false;
                    }else{
                        zero[tempLoc][loc]++;
                    }
                }
            }
        }

        return true;
    }*/

    //使用位数来表示数组，只用一维数组就可以了
    public boolean isValidSudoku(char[][] board){
        int[] row = new int[9];
        int[] col = new int[9];
        int[] zero = new int[9];
        int tempLoc,temp;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    temp = 1 << (board[i][j] - '0');
                    tempLoc = (i/3) + (j/3)*3;

                    //判断行的
                    if ((row[i]&temp) > 0 || (col[j]&temp) > 0 || (zero[tempLoc]&temp) > 0){
                        return false;
                    }
                    row[i] |= temp;
                    col[j] |= temp;
                    zero[tempLoc] |= temp;
                }
            }
        }

        return true;
    }
}
