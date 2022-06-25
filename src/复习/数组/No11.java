package 复习.数组;

public class No11 {
    /**
     *      旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
     *
     * 提示：
     *     n == matrix.length == matrix[i].length
     *     1 <= n <= 20
     *     -1000 <= matrix[i][j] <= 1000
     */

    //分治法
    int[][] temp;
    public void rotate(int[][] matrix){
        temp = matrix;
        function(0,matrix.length);
    }

    public void function(int begin,int length){
        if (length == 0 || length == 1) {
            return;
        }
        int i,l,box1,box2,end = begin+length-1;
        for (i = begin; i < end; i++) {
            l = i - begin;

            box1 = temp[begin][i];

            box2 = temp[i][end];
            temp[i][end] = box1;
            box1 = box2;

            box2 = temp[end][end-l];
            temp[end][end-l] = box1;
            box1 = box2;

            box2 = temp[end-l][begin];
            temp[end-l][begin] = box1;
            box1 = box2;

            temp[begin][i] = box1;
        }

        function(begin+1,length-2);
    }
}
