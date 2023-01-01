package contest.year2022.autumn;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    /**
     *      弹珠游戏
     */

    //首先规定好四个方向：0是从下面过来，1是从上面过来，2是从左边过来，3是从右边过来
    char[][] template;
    public int[][] ballGame(int num, String[] plate) {
        int n = plate.length,m;
        template = new char[n][];
        //把每个字符串转换成数组
        for (int i = 0; i < n; i++) {
            template[i] = plate[i].toCharArray();
        }
        m = template[0].length;
        List<int[]> list = new ArrayList<>();
        //从四个边遍历
        for (int i = 1; i < m-1; i++) {
            if(find(num+1,1,0,i)) list.add(new int[]{0,i});
        }
        for (int i = 1; i < n-1; i++) {
            if(find(num+1,2,i,0)) list.add(new int[]{i,0});
        }
        for (int i = 1; i < n-1; i++) {
            if(find(num+1,3,i,m-1)) list.add(new int[]{i,m-1});
        }
        for (int i = 1; i < m-1; i++) {
            if(find(num+1,0,n-1,i)) list.add(new int[]{n-1,i});
        }

        int[][] res = new int[list.size()][];
        int i = 0;
        for (int[] nums : list){
            res[i++] = nums;
        }
        return res;
    }
    //c统计剩余可走的步数，pre代表过来的方向
    public boolean find(int c,int pre,int i,int j){
        if(template[i][j] != '.')
            return false;
        while (c > 0) {
            if(i < 0 || j < 0 || j >= template[0].length || i >= template.length)
                break;
            if (template[i][j] == 'O') {
                return true;
            } else if (template[i][j] == 'W') {
                if (pre == 0) {
                    pre = 3;
                    j--;
                } else if (pre == 1) {
                    pre = 2;
                    j++;
                } else if (pre == 2) {
                    pre = 0;
                    i--;
                } else {
                    pre = 1;
                    i++;
                }
            } else if (template[i][j] == 'E') {
                if (pre == 0) {
                    pre = 2;
                    j++;
                } else if (pre == 1) {
                    pre = 3;
                    j--;
                } else if (pre == 2) {
                    pre = 1;
                    i++;
                } else {
                    pre = 0;
                    i--;
                }
            } else{
                if (pre == 0) i--;
                else if (pre == 1) i++;
                else if (pre == 2) j++;
                else j--;
            }
            c--;
        }
        return false;
    }
}
