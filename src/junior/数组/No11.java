package junior.数组;

public class No11 {
    //               旋转图像
    //  给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    //你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
    //  提示：
    //    matrix.length == n
    //    matrix[i].length == n
    //    1 <= n <= 20
    //    -1000 <= matrix[i][j] <= 1000
    //
    //作者：力扣 (LeetCode)
    //链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhhkv/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //看到题目的大概思路：双指针加分治法
    //每个外圈用双指针进行遍历翻转，然后进入小一圈的递归
    public void rotate(int[][] matrix) {
        rotate1(matrix,0,matrix.length);
    }
    //进行递归的函数
    //size是每一次矩阵的长/宽
    public void rotate1(int[][] matrix,int start,int size){
        //递归出口
        if (size == 1 || size == 0) {
            return;
        }
        //慢指针和快指针
        int slow = start,temp1,temp2;
        for(;slow < start+size-1;slow++){
            //先换第一个
            temp1 = matrix[slow][start+size-1];
            matrix[slow][start+size-1] = matrix[start][slow];
            //第二个
            temp2 = matrix[start+size-1][2*start+size-1-slow];
            matrix[start+size-1][2*start+size-1-slow] = temp1;
            //第三个
            temp1 = matrix[2*start+size-1-slow][start];
            matrix[2*start+size-1-slow][start] = temp2;
            //第四个
            matrix[start][slow] = temp1;
        }
        rotate1(matrix,start+1,size-2);
    }
}
