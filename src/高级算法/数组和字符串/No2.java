package 高级算法.数组和字符串;

import java.util.ArrayList;
import java.util.List;

public class No2 {
    //                          螺旋矩阵
    //给你一个m行n列的矩阵matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。

    //--------------------------------------------------------------------------------------
    //              失败，已经是无语了（经过修改，成功了）
    public static List<Integer> spiralOrder(int[][] matrix) {
        //left是左边界，right是右边界，up是上边界，down是下边界，i和j是遍历数组用的下标，flag是表示状态的标志
        int left = 0,right = matrix[0].length - 1,up = 0,down = matrix.length - 1,i = -1,j = 0,flag = 1;
        List<Integer> res = new ArrayList<>();

        while (true) {
            if (flag == 1) {
                i++;
                while (j <= right) {
                    res.add(matrix[i][j++]);
                }
                i++;
                up++;
                flag = 2;
            } else if (flag == 2) {
                j--;
                while (i <= down) {
                    res.add(matrix[i++][j]);
                }
                j--;
                right--;
                flag = 3;
            } else if (flag == 3) {
                i--;
                while (j >= left) {
                    res.add(matrix[i][j--]);
                }
                i--;
                down--;
                flag = 4;
            }else{
                j++;
                while (i >= up) {
                    res.add(matrix[i--][j]);
                }
                j++;
                left++;
                flag = 1;
            }

            if (res.size() == matrix.length * matrix[0].length) {
                break;
            }
        }

        /*if ((matrix[0].length & 1) == 1) {

            res.add(matrix[i][j]);
        }*/

        return res;
    }
    //---------------------------------------------------------------------------------------

    //错误总结：一开始，我是以边界条件为判断结束循环的依据，但是这样的操作并不适当，因为，边界条件有多种情况存在
    //当行数和列数变化时，这个边界情况的判断也要做出改变，这样子就会导致处理边界十分的麻烦，不具有统一性，所以后
    //来改为统一的判断集合元素个数来确定退出循环条件，这个条件具有统一性

    //---------------------------------------------------------------------------------------

}
