package daily.year2024.m7;

import java.util.HashSet;
import java.util.Set;

public class d20 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.totalNQueens(4));
    }
}

//笨办法，100多ms
class Solution1 {
    int[][] flags;
    int n,res;
    public int totalNQueens(int n) {
        flags = new int[n][n];
        this.n = n;
        res = 0;
        for(int i=0;i < n;i++) {
            for(int j=0;j < n;j++) {
                dfsOccupy(i,j,n);
            }
        }
        return res;
    }
    private void dfsOccupy(int i,int j,int count) {
        /*if(count == 0) {
            res++;
            return;
        }*/
        if(i < 0 || i >= n || j < 0 || j >= n || flags[i][j] > 0) {
            return;
        }
        if (count == 1) {
            res++;
            return;
        }
        //没有被占领，可以放下一个皇后
        occupy(i,j,1);
        for(int ni=i+1;ni < n;ni++) {
            for(int nj=0;nj < n;nj++) {
                dfsOccupy(ni,nj,count-1);
            }
        }
        //返回之前释放占领的领地
        occupy(i,j,-1);
    }
    //每次放下一个queen，都需要调用occupy将所有“领地”占领
    private void occupy(int i,int j,int val) {
        flags[i][j] += val;
        //上
        changeVal(i,j,-1,0,val);
        //下
        changeVal(i,j,1,0,val);
        //左
        changeVal(i,j,0,-1,val);
        //右
        changeVal(i,j,0,1,val);
        //左上
        changeVal(i,j,-1,-1,val);
        //右上
        changeVal(i,j,-1,1,val);
        //左下
        changeVal(i,j,1,-1,val);
        //右下
        changeVal(i,j,1,1,val);
    }
    private void changeVal(int i,int j,int ichange,int jchange,int val) {
        int x = i + ichange, y = j + jchange;
        for(;x >= 0 && x < n && y >=0 && y < n;x += ichange,y+=jchange) {
            flags[x][y] += val;
        }
    }
}

//solution1的改进版，2ms
class Solution2 {
    int[][] flags;
    int n,res;
    public int totalNQueens(int n) {
        flags = new int[n][n];
        this.n = n;
        res = 0;
        for(int j=0;j < n;j++) {
            dfsOccupy(0,j,n);
        }
        return res;
    }
    private void dfsOccupy(int i,int j,int count) {
        /*if(count == 0) {
            res++;
            return;
        }*/
        if(i < 0 || i >= n || j < 0 || j >= n || flags[i][j] > 0) {
            return;
        }
        if (count == 1) {
            res++;
            return;
        }
        //没有被占领，可以放下一个皇后
        occupy(i,j,1);
        for(int nj=0;nj < n;nj++) {
            dfsOccupy(i+1,nj,count-1);
        }
        //返回之前释放占领的领地
        occupy(i,j,-1);
    }
    //每次放下一个queen，都需要调用occupy将所有“领地”占领
    private void occupy(int i,int j,int val) {
        flags[i][j] += val;
        //上
        changeVal(i,j,-1,0,val);
        //下
        changeVal(i,j,1,0,val);
        //左
        changeVal(i,j,0,-1,val);
        //右
        changeVal(i,j,0,1,val);
        //左上
        changeVal(i,j,-1,-1,val);
        //右上
        changeVal(i,j,-1,1,val);
        //左下
        changeVal(i,j,1,-1,val);
        //右下
        changeVal(i,j,1,1,val);
    }
    private void changeVal(int i,int j,int ichange,int jchange,int val) {
        int x = i + ichange, y = j + jchange;
        for(;x >= 0 && x < n && y >=0 && y < n;x += ichange,y+=jchange) {
            flags[x][y] += val;
        }
    }
}

//用哈希表，200多ms，更慢
class Solution3 {
    Set<Integer> colums;
    Set<Integer> diagonals1;
    Set<Integer> diagonals2;
    int n,res;
    public int totalNQueens(int n) {
        this.n = n;
        res = 0;
        colums = new HashSet<>();
        diagonals1 = new HashSet<>();
        diagonals2 = new HashSet<>();
        for(int i=0;i < n;i++) {
            for(int j=0;j < n;j++) {
                dfsOccupy(i,j,n);
            }
        }
        return res;
    }
    private void dfsOccupy(int i,int j,int count) {
        if(i < 0 || i >= n || j < 0 || j >= n) {
            return;
        }
        int d1 = i-j, d2 = i+j;
        if(colums.contains(j) || diagonals1.contains(d1) || diagonals2.contains(d2)) {
            return;
        }
        if (count == 1) {
            res++;
            return;
        }
        //没有被占领，可以放下一个皇后
        colums.add(j);
        diagonals1.add(d1);
        diagonals2.add(d2);
        for(int ni=i+1;ni < n;ni++) {
            for(int nj=0;nj < n;nj++) {
                dfsOccupy(ni,nj,count-1);
            }
        }
        //返回之前释放占领的领地
        colums.remove(j);
        diagonals1.remove(d1);
        diagonals2.remove(d2);
    }
}

//Solution3的改进版，4ms
//效率能够大幅提升是因为省去了很多不必要的遍历
class Solution4 {
    Set<Integer> colums;
    Set<Integer> diagonals1;
    Set<Integer> diagonals2;
    int n,res;
    public int totalNQueens(int n) {
        this.n = n;
        res = 0;
        colums = new HashSet<>();
        diagonals1 = new HashSet<>();
        diagonals2 = new HashSet<>();
        for(int j=0;j < n;j++) {
            dfsOccupy(0,j,n);
        }
        return res;
    }
    private void dfsOccupy(int i,int j,int count) {
        if(i < 0 || i >= n || j < 0 || j >= n) {
            return;
        }
        int d1 = i-j, d2 = i+j;
        if(colums.contains(j) || diagonals1.contains(d1) || diagonals2.contains(d2)) {
            return;
        }
        if (count == 1) {
            res++;
            return;
        }
        //没有被占领，可以放下一个皇后
        colums.add(j);
        diagonals1.add(d1);
        diagonals2.add(d2);
        //每行必须选一个位置来放皇后，不存在跳过当前行的情况，使用反证法可以证明
        for(int nj=0;nj < n;nj++) {
            dfsOccupy(i+1,nj,count-1);
        }
        //返回之前释放占领的领地
        colums.remove(j);
        diagonals1.remove(d1);
        diagonals2.remove(d2);
    }
}

//最优解决方案，使用bitSet替代HashSet
class Solution {
    //使用位运算
    int res,n;
    public int totalNQueens(int n) {
        res = 0;
        this.n = n;
        dfsOccupy(n,0,0,0);
        return res;
    }
    //参数分别表示：以及拜访的皇后的数量、列、斜边1（从左上到右下）、斜边2
    private void dfsOccupy(int c,int colums,int inclined1,int inclined2) {
        //int canOccupy = ~(colums | inclined1 | inclined2);
        //重点注意此处，不能直接进行取反，必须要限定二进制位最多只能代表n位
        int canOccupy = ((1 << n) - 1) & (~(colums | inclined1 | inclined2));
        while(canOccupy != 0) {
            //取出最低位
            int lowbit = canOccupy & (-canOccupy);
            //去掉最低位
            canOccupy = canOccupy ^ lowbit;
            if(c == 1) {
                res++;
            } else {
                dfsOccupy(c-1,colums | lowbit,(inclined1 | lowbit) << 1,(inclined2 | lowbit) >>> 1);
            }
        }
    }
}