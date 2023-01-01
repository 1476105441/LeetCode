package contest.year2022.autumn;

public class No1 {
    /**
     *      气温变化趋势
     */

    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int m = temperatureA.length;
        int[] temp1 = new int[m],temp2 = new int[m];
        for (int i = 1; i < m; i++) {
            if (temperatureA[i] < temperatureA[i - 1]) {
                temp1[i] = -1;
            } else if (temperatureA[i] > temperatureA[i - 1]) {
                temp1[i] = 1;
            }else{
                temp1[i] = 0;
            }

            if (temperatureB[i] < temperatureB[i - 1]) {
                temp2[i] = -1;
            } else if (temperatureB[i] > temperatureB[i - 1]) {
                temp2[i] = 1;
            }else{
                temp2[i] = 0;
            }
        }

        int res = 0,temp = 0;
        for (int i = 1; i < m; i++) {
            if(temp1[i] == temp2[i]){
                temp++;
            }else
                temp = 0;
            if(res < temp)
                res = temp;
        }
        return res;
    }
}
