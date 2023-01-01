package contest.year2022.m7.m7d31;

public class No2 {
    /**
     *      分组的最大数量
     */

    //首先，学生数目得要符合要求
    public int maximumGroups(int[] grades) {
        int n = grades.length,m;
        for (m=1; m*(m+1)/2 <= n; m++) {

        }
        return m-1;
    }
}
