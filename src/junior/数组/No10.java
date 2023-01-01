package junior.数组;

public class No10 {
    //                    有效的数独
    //请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
    //    数字 1-9 在每一行只能出现一次。
    //    数字 1-9 在每一列只能出现一次。
    //    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
    //
    //数独部分空格内已填入了数字，空白格用 '.' 表示。
    //
    //注意：
    //    一个有效的数独（部分已被填充）不一定是可解的。
    //    只需要根据以上规则，验证已经填入的数字是否有效即可。
    //
    //提示：
    //
    //    board.length == 9
    //    board[i].length == 9
    //    board[i][j] 是一位数字或者 '.'
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2f9gg/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //--------------------------------------------------------------------------------
    //暴力解法（6ms，被吊打）
//    public boolean isValidSudoku(char[][] board) {
//        //*寻找是否有相同的（每列每行，每个小宫格即9*9方块内）
//        //  优化的想法：遍历时有很多同一行或者同一列的已经确定过结果了但还是遍历进去，
//        //是否可以只遍历一次？
//        //con数组存放的是每一行或每一列的状态
//        boolean[][] con = new boolean[2][9];
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (i % 3 == 0 && j % 3 == 0 && !function(board,i,j)) {
//                    return false;
//                }
//                //找每行每列
//                if (con[0][i] == true && con[1][j] == true) {
//                    continue;
//                }
//                Map<Character,Boolean> map1 = new HashMap<>();
//                Map<Character,Boolean> map2 = new HashMap<>();
//                for (int k = 0; k < 9; k++) {
//                    //找一行上有无重复的
//                    if (board[i][k] != '.' && map1.get(board[i][k]) != null){
//                        return false;
//                    }
//                    map1.put(board[i][k],true);
//                    if (board[k][j] != '.' && map2.get(board[k][j]) != null) {
//                        return false;
//                    }
//                    map2.put(board[k][j],true);
//                }
//                con[1][j] = true;
//                con[0][i] = true;
//            }
//        }
//        return true;
//    }
//    public boolean function(char[][] board, int row, int column) {
//        Map<Character,Boolean> map = new HashMap<>();
//        for (int i = row; i < row + 3; i++) {
//            for (int j = column; j < column + 3; j++) {
//                if (board[i][j] != '.' && map.get(board[i][j]) != null) {
//                    return false;
//                }
//                map.put(board[i][j],true);
//            }
//        }
//        return true;
//    }
    //--------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------
    //看看别人的哈希表：2ms，又精简又快
//    public boolean isValidSudoku(char[][] board) {
//        for(int i = 0;i < 9;i++){
//            HashSet setLine = new HashSet();
//            HashSet setCol = new HashSet();
//            HashSet setBox = new HashSet();
//            for (int j = 0; j < 9; j++) {
//                if(board[i][j] != '.' &&!setLine.add(board[i][j])){
//                    return false;
//                }
//                if(board[j][i] != '.' && !setCol.add(board[j][i])){
//                    return false;
//                }
//                int a = (i/3)*3 + j/3;
//                int b = (i%3)*3 + j%3;
//                if(board[a][b] != '.' && !setBox.add(board[a][b])){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    //--------------------------------------------------------------------------------

    //位运算：1ms
    public boolean isValidSudoku(char[][] board) {
        int[] line = new int[9];
        int[] column = new int[9];
        int[] cell = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                shift = 1 << (board[i][j] - '0');
                //这一步是确定当前的字符在第几个九宫格
                int k = (i / 3) * 3 + j / 3;
                //如果对应的位置只要有一个大于0，说明有冲突，直接返回false
                if ((column[i] & shift) > 0 || (line[j] & shift) > 0 || (cell[k] & shift) > 0)
                    return false;
                column[i] |= shift;
                line[j] |= shift;
                cell[k] |= shift;
            }
        }
        return true;
    }


}
