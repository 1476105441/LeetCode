package 高级算法.排序和搜索;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    /**
     *          有序矩阵中第K小的元素
     * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     *
     * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
     *
     * 提示：
     *     n == matrix.length
     *     n == matrix[i].length
     *     1 <= n <= 300
     *     -109 <= matrix[i][j] <= 109
     *     题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
     *     1 <= k <= n2
     *
     * 进阶：
     *     你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
     *     你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
     */

    //想法：使用优先队列（最小堆）
    //从首位元素开始计数，将右边和下边的元素加入队列中
    //下一步行动从队列最小的元素开始，将左边和下边的元
    //素加入队列中，如此直到计数值为k
    /*int[][] que;
    boolean[][] flag;
    public int kthSmallest(int[][] matrix, int k) {
        if (k == 1) {
            return matrix[0][0];
        }
        int m = matrix.length,n = matrix[0].length,count = 1,queLength = 0,i,j;
        que = new int[m*n][3];
        flag = new boolean[m][n];

        que[0][0] = matrix[0][0];
        que[0][1] = 0;
        que[0][2] = 0;
        queLength = 1;
        flag[0][0] = true;
        while (count < k) {
            i = que[0][1];
            j = que[0][2];
            exchange(0,queLength-1);
            queLength--;
            if (i < m - 1 && !flag[i+1][j]) {
                que[queLength][0] = matrix[i+1][j];
                que[queLength][1] = i+1;
                que[queLength][2] = j;
                queLength++;
                flag[i+1][j] = true;
            }
            if(j < n-1 && !flag[i][j+1]){
                que[queLength][0] = matrix[i][j+1];
                que[queLength][1] = i;
                que[queLength][2] = j+1;
                queLength++;
                flag[i][j+1] = true;
            }
            merge(queLength);
            count++;
        }

        return que[0][0];
    }

    public void shift(int begin,int length){
        int i = begin,j = 2 * i + 1,temp;
        while (j < length) {
            if (j < length - 1 && que[j][0] > que[j + 1][0]) {
                j++;
            }
            if (que[i][0] > que[j][0]) {
                exchange(i,j);
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }

    public void merge(int length){
        int i = (length-2) / 2;
        for (; i >= 0; i--) {
            shift(i,length);
        }
    }

    public void exchange(int i,int j){
        int temp;
        temp = que[i][0];
        que[i][0] = que[j][0];
        que[j][0] = temp;
        temp = que[i][1];
        que[i][1] = que[j][1];
        que[j][1] = temp;
        temp = que[i][2];
        que[i][2] = que[j][2];
        que[j][2] = temp;
    }*/


    //可以使用与合并排序链表一样的方法
    //官方的普通排序竟然比归并排序还要快
    /*int[][] que;
    public int kthSmallest(int[][] matrix, int k){
        int m = matrix.length,n = matrix[0].length,count = 1,row,col,length = m;
        que = new int[length][];
        for (int i = 0; i < length; i++) {
            que[i] = new int[]{matrix[i][0],i,0};
        }
        for (int i = (length-2)/2; i >= 0; i--) {
            shift(i,length);
        }

        while (count < k) {
            row = que[0][1];
            col = que[0][2];
            if (col < n-1) {
                que[0] = new int[]{matrix[row][col+1],row,col+1};
            }else{
                que[0] = que[length-1];
                que[length-1] = null;
                length--;
            }
            shift(0,length);
            count++;
        }

        return que[0][0];
    }

    public void shift(int begin,int length){
        int i = begin,j = 2*i+1;
        int[] temp;
        while (j < length) {
            if (j < length - 1 && que[j][0] > que[j+1][0]) {
                j++;
            }

            if (que[i][0] > que[j][0]) {
                temp = que[i];
                que[i] = que[j];
                que[j] = temp;
                i = j;
                j = 2*i+1;
            }else
                break;
        }
    }*/


    //最优解法：有点像脑筋急转弯的二分查找
    //matrix[0][0]是最小的元素，而matrix[m-1][n-1]是最大的元素
    //在这两个元素之间进行二分查找，统计数在数组中小于它的个数，统计方
    //法是从左下角开始走，如果当前元素比该数小，则（小于它的数）加上这
    //一列的长度，否则往上走
    public int kthSmallest(int[][] matrix,int k){
        int left,right,m = matrix.length,n = matrix[0].length,center,i,j,count,res = 0;
        left = matrix[0][0];
        right = matrix[m-1][n-1];

        while (left <= right) {
            center = left + (right-left)/2;
            count = 0;
            //统计小于center的元素个数
            i = m-1;
            j = 0;
            while (j < n && i > -1) {
                if (matrix[i][j] <= center) {
                    count += i+1;
                    j++;
                }else{
                    i--;
                }
            }

            if (count >= k) {
                right = center-1;
                res = center;
            }else{
                left = center + 1;
            }
        }

        return res;
    }
}
