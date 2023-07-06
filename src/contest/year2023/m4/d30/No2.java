package contest.year2023.m4.d30;

/**
 * 2661.找出叠涂元素
 *
 * @author wjs 2023/7/7
 */
public class No2 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int total = m*n;
        int[][] map = new int[total+1][2];
        for(int i=0;i < m;i++) {
            for(int j=0;j < n;j++) {
                map[mat[i][j]][0] = i;
                map[mat[i][j]][1] = j;
            }
        }
        int[] rowCnt = new int[m], colCnt = new int[n];
        for(int i=0;i < m;i++) {
            rowCnt[i] = n;
        }
        for(int i=0;i < n;i++) {
            colCnt[i] = m;
        }
        int res = 0;
        for(int i=0;i < total;i++) {
            int[] tmp = map[arr[i]];
            rowCnt[tmp[0]]--;
            colCnt[tmp[1]]--;
            if(rowCnt[tmp[0]] == 0 || colCnt[tmp[1]] == 0) {
                res = i;
                break;
            }
        }
        return res;
    }
}
