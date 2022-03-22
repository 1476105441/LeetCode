package 高级算法.树和图;

import java.util.*;

public class No2 {
    /**
     *          被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符
     * 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
     * 并将这些区域里所有的 'O' 用 'X' 填充。
     */

    /**
     * 深度优先搜索 + 并查集
     * 想法：使用并查集，将连在一起的'O'放入一个并
     * 查集中，如果这个并查集的当前节点周围有边界，
     * 那么其所在的并集中一定不会被清除，若是当前节
     * 点周围有其它的'O'，且不在其所在的并集中，那
     * 么进入下一个'O'节点，进行判断，而当前节点的
     * 返回值根据下一节点的返回值做调整
     */

    //6ms
    /*int[] parent;
    Map<Integer, List<Element>> map;

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        parent = new int[m * n];
        //用于存放并集中的点，在需要把并集消灭时使用
        map = new HashMap<>();

        //初始化并集，每个元素单独为一个集合
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    int loc = i * n + j, ploc = parent[loc];
                    if (loc == ploc) {
                        boolean flag = search(board,i,j,ploc);
                        if (flag) {
                            List<Element> list = map.get(ploc);
                            for (Element e : list){
                                board[e.row][e.col] = 'X';
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean search(char[][] board,int row, int col, int p) {
        //1、将当前的节点加入并集之中
        List<Element> pList = map.get(p);
        if (pList == null) {
            pList = new ArrayList<>();
            map.put(p, pList);
        }
        pList.add(new Element(row, col));
        int n = board[row].length;
        boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true;
        int num1 = (row-1)*n + col,num2 = (row)*n + col - 1,num3 = (row+1) * n + col,num4 = row * n + col + 1;

        if (row == 0) {
            flag1 = false;
        }else if(board[row-1][col] == 'O' && parent[num1] != p){
            parent[num1] = p;
            flag1 = search(board,row-1,col,p);
        }

        if(col == 0){
            flag2 = false;
        } else if (board[row][col-1] == 'O' && parent[num2] != p) {
            parent[num2] = p;
            flag2 = search(board,row,col-1,p);
        }

        if (row == board.length - 1) {
            flag3 = false;
        } else if (board[row + 1][col] == 'O' && parent[num3] != p) {
            parent[num3] = p;
            flag3 = search(board,row+1,col,p);
        }

        if (col == board[row].length - 1) {
            flag4 = false;
        }else if(board[row][col+1] == 'O' && parent[num4] != p){
            parent[num4] = p;
            flag4 = search(board,row,col+1,p);
        }

        return flag1 && flag2 && flag3 && flag4;
    }*/

    boolean[][] parent;

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        parent = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    if (!parent[i][j]) {
                        List<int[]> list = new ArrayList<>();
                        boolean flag = search(board,i,j,list);
                        if (flag)
                            for (int[] e : list)
                                board[e[0]][e[1]] = 'X';
                    }
                }
            }
        }
    }

    public boolean search(char[][] board,int row, int col,List<int[]> pList) {
        //1、将当前的节点加入并集之中
        pList.add(new int[]{row, col});
        boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true;

        if (row == 0) {
            flag1 = false;
        }else if(board[row-1][col] == 'O' && !parent[row-1][col]){
            parent[row-1][col] = true;
            flag1 = search(board,row-1,col,pList);
        }

        if(col == 0){
            flag2 = false;
        } else if (board[row][col-1] == 'O' && !parent[row][col-1]) {
            parent[row][col-1] = true;
            flag2 = search(board,row,col-1,pList);
        }

        if (row == board.length - 1) {
            flag3 = false;
        } else if (board[row + 1][col] == 'O' && !parent[row+1][col]) {
            parent[row+1][col] = true;
            flag3 = search(board,row+1,col,pList);
        }

        if (col == board[row].length - 1) {
            flag4 = false;
        }else if(board[row][col+1] == 'O' && !parent[row][col+1]){
            parent[row][col+1] = true;
            flag4 = search(board,row,col+1,pList);
        }

        return flag1 && flag2 && flag3 && flag4;
    }
}

class Element {
    int row;
    int col;

    public Element(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
