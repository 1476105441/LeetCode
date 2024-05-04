package daily.year2024.m5;

/**
 * @author wjs 2024/5/4
 */
public class d4 {
    //130、Surrounded Regions
    //解法1：并查集+dfs
    /*public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        visited = new boolean[m][n];
        parent = new int[m*n];
        needChange = new boolean[m*n];
        initParent();
        //遍历矩阵，为了找出应该要转换的并集
        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    //第一次传递自己为preIdx进去
                    int idx = countIndex(i,j);
                    boolean res = dfs(i,j,idx);
                    if (!res) {
                        needChange[idx] = true;
                    }
                }
            }
        }

        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                if (board[i][j] == 'O') {
                    int idx = countIndex(i,j);
                    if (needChange[findParent(idx)]) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    private char[][] board;
    private boolean[][] visited;
    private boolean dfs(int i,int j,int preIdx) {
        //走到边界了
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return true;
        }
        if (visited[i][j] || board[i][j] == 'X') {
            return false;
        }
        visited[i][j] = true;
        //归到一个并集里
        int idx = countIndex(i,j);
        parent[idx] = findParent(preIdx);

        boolean f1,f2,f3,f4;
        f1 = dfs(i+1,j,idx);
        f2 = dfs(i-1,j,idx);
        f3 = dfs(i,j+1,idx);
        f4 = dfs(i,j-1,idx);
        return f1 || f2 || f3 || f4;
    }

    //并查集处理部分
    private int[] parent;
    private boolean[] needChange;
    private int m,n;
    private int countIndex(int i,int j) {
        return i*n + j;
    }
    private void initParent() {
        for(int i=0;i < parent.length;i++) {
            parent[i] = i;
        }
    }
    private int findParent(int idx) {
        if (idx == parent[idx]) {
            return idx;
        }
        parent[idx] = findParent(parent[idx]);
        return parent[idx];
    }*/

    //解法2：逆向思维
    private boolean[][] notNeedChange;
    private boolean[][] visited;
    private int m,n;
    private char[][] board;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        notNeedChange = new boolean[m][n];
        visited = new boolean[m][n];
        for(int i=0;i < n;i++) {
            if (board[0][i] == 'O' && !visited[0][i]) {
                dfs(0,i);
            }
            if (board[m-1][i] == 'O' && !visited[m-1][i]) {
                dfs(m-1,i);
            }
        }
        for(int i=0;i < m;i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                dfs(i,0);
            }
            if (board[i][n-1] == 'O' && !visited[i][n-1]) {
                dfs(i,n-1);
            }
        }
        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                if (board[i][j] == 'O' && !notNeedChange[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void dfs(int i,int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] == 'X') {
            return;
        }
        notNeedChange[i][j] = true;
        visited[i][j] = true;
        dfs(i-1,j);
        dfs(i+1,j);
        dfs(i,j-1);
        dfs(i,j+1);
    }
}
