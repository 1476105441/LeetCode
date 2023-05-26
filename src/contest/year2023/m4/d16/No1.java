package contest.year2023.m4.d16;

/**
 * 2643. 一最多的行
 *
 * @author wjs 2023/5/27
 */
public class No1 {

    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] res = new int[2];
        int n = mat.length,max = 0,index = 0;
        for(int i=0;i < n;i++){
            int c = 0;
            for(int j=0;j < mat[i].length;j++){
                if(mat[i][j] == 1) {
                    c++;
                }
            }
            if(max < c){
                max = c;
                index = i;
            }
        }
        res[0] = index;
        res[1] = max;
        return res;
    }
}
