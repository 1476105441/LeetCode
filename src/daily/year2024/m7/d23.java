package daily.year2024.m7;

public class d23 {
    boolean res;
    char[][] board;
    int m,n;
    char[] chars;
    int cl;
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        res = false;
        this.board = board;
        m = board.length;
        n = board[0].length;
        chars = word.toCharArray();
        cl = chars.length;
        visited = new boolean[m][n];
        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                dfsSearch(i,j,0);
                if(res) {
                    return true;
                }
            }
        }
        return res;
    }
    private void dfsSearch(int i,int j,int loc) {
        if(loc == cl) {
            res = true;
            return;
        }
        if(res || i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return;
        }
        if(board[i][j] == chars[loc]) {
            visited[i][j] = true;
            dfsSearch(i-1,j,loc+1);
            dfsSearch(i+1,j,loc+1);
            dfsSearch(i,j-1,loc+1);
            dfsSearch(i,j+1,loc+1);
            visited[i][j] = false;
        }
    }



    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
    }
    int[] nums;
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return binaryBuild(0,nums.length-1);
    }
    private TreeNode binaryBuild(int l, int r) {
        if(l > r) {
            return null;
        }
        if(l == r) {
            TreeNode newNode = new TreeNode(nums[l]);
            return newNode;
        }
        int c = l + ((r-l) >> 1);
        TreeNode left = binaryBuild(l,c-1);
        TreeNode right = binaryBuild(c+1,r);
        TreeNode cur = new TreeNode(nums[c]);
        cur.left = left;
        cur.right = right;
        return cur;
    }
}
