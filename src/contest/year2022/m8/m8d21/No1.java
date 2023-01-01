package contest.year2022.m8.m8d21;

public class No1 {
    /**
     *      赢得比赛需要的最少训练时长
     */

    //统计经验和精力需要加多少
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int res = 0,c1 = 0,c2 = 0,temp = initialExperience;
        int n = energy.length;

        for (int i = 0; i < n; i++) {
            c1 += energy[i];
            if (temp <= experience[i]) {
                c2 += experience[i] - temp + 1;
                temp = experience[i] + 1;
            }
            temp += experience[i];
        }
        if(initialEnergy < c1 + 1)
            res = (c1+1-initialEnergy) + c2;
        else
            res = c2;

        return res;
    }
}
