package middle.排序和搜索;

public class No8 {
    //              搜索二维矩阵 II
    //编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
    //    每行的元素从左到右升序排列。
    //    每列的元素从上到下升序排列。

    //-------------------------------------------------------------------------------------------
    //失败，超时了，很尴尬
    //想法是先对列进行二分查找，若是大于，则舍弃下面的行，若是小于，则在下面行中先查找，若是查找失败，舍弃当前这一列
    /*public boolean searchMatrix(int[][] matrix, int target) {
        return recursion(matrix,0,matrix.length-1,0,matrix[0].length-1,target);
    }

    public boolean recursion(int[][] matrix,int rowL,int rowH,int colL,int colH,int target){
        //在同一行时，对此行进行二分查找
        if (rowL > rowH) {
            return false;
        }
        if (colL > colH) {
            return false;
        }

        if (rowL == rowH) {

            int center = colL + (colH - colL) / 2;
            if (matrix[rowH][center] > target) {
                return recursion(matrix,rowL,rowH,colL,center-1,target);
            }else if(matrix[rowH][center] < target){
                return recursion(matrix,rowL,rowH,center+1,colH,target);
            }else{
                return true;
            }
        }

        //不在同一行时对一列二分查找
        int center = rowL + (rowH - rowL) / 2;

        //中间值比目标值大，直接舍弃下半边的行
        if (matrix[center][colL] > target) {
            return recursion(matrix,rowL,center-1,colL,colH,target);
        } else if (matrix[center][colL] < target) {
            boolean flag;
            flag = recursion(matrix,center+1,rowH,colL,colH,target);
            if (!flag) {
                //查找失败，舍弃这一列
                return recursion(matrix,rowL,rowH,colL+1,colH,target);
            }else {
                return true;
            }
        }else{
            return true;
        }
    }*/
    //--------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    //
    //更高效的解法？看到评论说可以使用二叉搜索树？
    //脑子没转过来啊，从第一行最后一个开始遍历，可以看做二叉搜索树，若是值比当前值小，去上一列找
    //若是值比当前值大，去下一行找，因为第一行的最后一个元素是第一行最大的，如果比其大，则一定比
    // 当前行的所有元素都大，往下一行找，舍弃当前行。如果比其小，该元素（由于后续会更新，所以用
    // 该元素代称）是当前列最小的元素，往前一列找，舍弃当前列
    //
    //时间复杂度为O(m + n) ，m是行数，n是列数
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;

        while (row < matrix.length && col > -1) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            }else{
                return true;
            }
        }

        return false;
    }
}
