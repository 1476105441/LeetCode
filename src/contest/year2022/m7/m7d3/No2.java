package contest.year2022.m7.m7d3;

public class No2 {
    /**
     *      螺旋矩阵IV
     */

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        int total = m * n,c = 0;
        int[][] res = new int[m][n];
        //四个边界
        int up=0,down=m-1,l=0,r=n-1,i=0,j=0;
        while (c < total) {
            //先往右走
            while (c < total &&j <= r) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                }else
                    res[i][j] = -1;
                j++;
                c++;
            }
            up++;
            j--;
            i++;

            //往下走
            while (c < total && i <= down) {
                if (head != null){
                    res[i][j] = head.val;
                    head = head.next;
                }else
                    res[i][j] = -1;
                i++;
                c++;
            }
            r--;
            i--;
            j--;

            //往左走
            while (c < total &&j >= l) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                }else
                    res[i][j] = -1;
                j--;
                c++;
            }
            down--;
            j++;
            i--;

            //往上走
            while (c < total &&i >= up) {
                if (head != null) {
                    res[i][j] = head.val;
                    head = head.next;
                }else
                    res[i][j] = -1;
                i--;
                c++;
            }
            l++;
            i++;
            j++;
        }

        return res;
    }
}
