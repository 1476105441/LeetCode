package contest.year2022.autumn;

import java.util.*;

public class No2 {
    /**
     *      交通枢纽
     */

    //首先，满足交通枢纽的条件：所有其他点都能到达
    public int transportationHub(int[][] path) {
        int m = path.length,length = 0;
        int[] count = new int[1000],set1 = new int[1000];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            count[path[i][1]]++;
            //记录下出边的信息
            set.add(path[i][0]);
            if (set1[path[i][0]] == 0) {
                set1[path[i][0]] = 1;
                length++;
            }
            if (set1[path[i][1]] == 0) {
                set1[path[i][1]] = 1;
                length++;
            }
        }
        for (int i = 0; i < 1000; i++) {
            if (count[i] == length-1 && !set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}
