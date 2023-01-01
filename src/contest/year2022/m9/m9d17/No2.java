package contest.year2022.m9.m9d17;

import java.util.Arrays;

public class No2 {
    /**
     *      运动员和训练师的最大匹配数
     */

    //排序+双指针
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int m = players.length,n = trainers.length;
        Arrays.sort(players);
        Arrays.sort(trainers);
        int i = 0,j = 0,res = 0;
        while(i < m && j < n){
            if (players[i] <= trainers[j]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}
