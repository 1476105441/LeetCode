package middle.回溯算法;

public class No5 {
    //                          单词搜索
    //给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    //单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    //深度优先算法DFS
    //想法：如果当前矩阵中的元素与当前下标为loc的元素匹配成功，就从当前元素
    //成功：60多ms，击败百分之90+
    boolean exists;

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '*') {
                    boolean[][] isUse= new boolean[board.length][board[0].length];
                    recursion(board,chars,0,i,j,isUse);
                }
            }
        }
        return exists;
    }

    //需要哪些参数？board就是要遍历的数组，chars是字符串拆分成的字符数组，loc是当前
    // 已经匹配到的字符的下标，row是当前元素的行号，col是当前元素的列号，pre是前一个
    // 元素的位置（从哪里传来）
    //递归出口是什么？匹配失败，或者被标记过
    public void recursion(char[][] board,char[] chars,int loc,int row,int col,boolean[][] isUse){
        //已经不可能匹配成功
        if ('*' == board[row][col] || exists) {
            return;
        }

        //匹配失败
        if (board[row][col] != chars[loc]) {
            boolean flag = true;
            for (int i = 0; i < chars.length; i++) {
                if (board[row][col] == chars[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                board[row][col] = '*';
            }
            return;
        } else if (loc == chars.length-1) {
            exists = true;
            return;
        }

        //来到这里就说明匹配成功了，从上下左右继续匹配
        isUse[row][col] = true;
        if (row > 0 && !isUse[row-1][col]) {
            recursion(board,chars,loc+1,row-1,col,isUse);
            //为了避免对后续的搜索造成影响，每次搜索完一条分支之后就要将当前元素的状态调整回未被遍历状态
            isUse[row-1][col] = false;
        }
        if (row < board.length - 1 && !isUse[row+1][col]) {
            recursion(board,chars,loc+1,row+1,col,isUse);
            isUse[row+1][col] = false;
        }
        if (col > 0 && !isUse[row][col-1]) {
            recursion(board,chars,loc+1,row,col-1,isUse);
            isUse[row][col-1] = false;
        }
        if (col < board[0].length - 1 && !isUse[row][col+1]) {
            recursion(board,chars,loc+1,row,col+1,isUse);
            isUse[row][col+1] = false;
        }
    }

}
