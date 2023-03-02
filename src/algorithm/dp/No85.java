package algorithm.dp;

public class No85 {
    //最大矩形
    //解法一：暴力优化，使用dp优化，12ms
    //思想：计算以每个元素为矩形的右下端点的最大矩形面积
    public int maximalRectangle1(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] left = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = j > 0 ? left[i][j-1] + 1 : 1;
                }
            }
        }
        int res = 0;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                int h = 1,w = 1000;
                for(int k = i;k >= 0;k--){
                    w = Math.min(w,left[k][j]);
                    int val = h * w;
                    if(val == 0){
                        break;
                    }
                    res = Math.max(res,val);
                    h++;
                }
            }
        }
        return res;
    }

    //单调栈，6ms
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int[][] left = new int[m][n],right = new int[m][n],height = new int[m][n];
        for(int i = 0;i < m;i++){
            int[][] stack = new int[n][];
            int l = 0;
            for(int j = 0;j < n;j++){
                if(matrix[i][j] == '1'){
                    height[i][j] = i > 0 ? height[i-1][j] + 1 : 1;
                }
                while(l != 0 && height[stack[l-1][0]][stack[l-1][1]] >= height[i][j]){
                    l--;
                    right[stack[l][0]][stack[l][1]] = j;
                }
                if(l == 0){
                    left[i][j] = -1;
                } else {
                    left[i][j] = stack[l-1][1];
                }
                stack[l++] = new int[]{i,j};
            }
            while(l != 0){
                l--;
                right[stack[l][0]][stack[l][1]] = n;
            }
        }
        int res = 0;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                int val = height[i][j] * (right[i][j] - left[i][j] - 1);
                res = Math.max(res,val);
            }
        }
        return res;
    }
}
