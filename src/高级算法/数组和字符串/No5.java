package 高级算法.数组和字符串;

public class No5 {
    /**
     *              生命游戏
     * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     *
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     *     如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     *     如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     *     如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     *     如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     *
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     *
     * 进阶：
     *     你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
     *     本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
     */

    /**
     * 思想：我采用两次遍历的方法可以解决原地算法的问题
     */
    public void gameOfLife(int[][] board){
        int count;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
                if (i > 0) {
                    if (board[i - 1][j] >= 1) {
                        count++;
                    }
                    if (j > 0 && board[i-1][j-1] >= 1) {
                       count++;
                    }
                    if (j < board[i].length - 1 && board[i - 1][j + 1] >= 1) {
                        count++;
                    }
                }
                if (j > 0 && board[i][j-1] >= 1) {
                    count++;
                }
                if (j < board[i].length - 1 && board[i][j + 1] >= 1) {
                    count++;
                }
                if (i < board.length - 1) {
                    if (board[i + 1][j] >= 1) {
                        count++;
                    }
                    if (j > 0 && board[i + 1][j - 1] >= 1) {
                        count++;
                    }
                    if (j < board[i].length - 1 && board[i + 1][j + 1] >= 1) {
                        count++;
                    }
                }

                if (board[i][j] == 1) {
                    if (count < 2) {
                        board[i][j] = 2;
                    } else if (count > 3) {
                        board[i][j] = 2;
                    }
                }else{
                    if (count == 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                } else if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }
    }
}
