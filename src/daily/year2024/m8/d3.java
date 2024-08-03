package daily.year2024.m8;

/**
 * @author wjs 2024/8/3
 */
public class d3 {
    class Solution {
        //先在第一行进行二分，定位到哪行中
        //然后再在行中进行二分
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int low = 0, high = m-1, l = 0, r = n-1;
            while(low < high) {
                int c = low + ((high-low) >> 1);
                if(matrix[c][0] == target) {
                    return true;
                } else if(matrix[c][0] > target) {
                    high = c-1;
                } else {
                    low = c+1;
                }
            }
            if(matrix[low][0] > target) {
                if(low == 0) {
                    return false;
                }
                low--;
            }
            while(l < r) {
                int c = l + ((r-l) >> 1);
                if(matrix[low][c] >= target) {
                    r = c;
                } else {
                    l = c+1;
                }
            }
            return matrix[low][l] == target;
        }
    }
}
