package contest.year2022.m9.m9d17;

public class No1 {
    /**
     * 统计共同度过的日子数
     */

    int[] map;
    {
        map = new int[13];
        map[1] = 31;
        map[2] = 28;
        map[3] = 31;
        map[4] = 30;
        map[5] = 31;
        map[6] = 30;
        map[7] = 31;
        map[8] = 31;
        map[9] = 30;
        map[10] = 31;
        map[11] = 30;
        map[12] = 31;
    }
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[][] date = new int[4][2];
        String[] split = arriveAlice.split("-");
        date[0][0] = Integer.parseInt(split[0]);
        date[0][1] = Integer.parseInt(split[1]);
        split = leaveAlice.split("-");
        date[1][0] = Integer.parseInt(split[0]);
        date[1][1] = Integer.parseInt(split[1]);
        split = arriveBob.split("-");
        date[2][0] = Integer.parseInt(split[0]);
        date[2][1] = Integer.parseInt(split[1]);
        split = leaveBob.split("-");
        date[3][0] = Integer.parseInt(split[0]);
        date[3][1] = Integer.parseInt(split[1]);
        int res = 0,start,end;

        if(date[0][0] > date[3][0] || date[2][0] > date[1][0] || (date[0][0] == date[3][0] && date[0][1] > date[3][1]) || (date[2][0] == date[1][0] && date[2][1] > date[1][1])) {
            return res;
        }
        start = date[0][0] > date[2][0] ? 0 : 2;
        if(date[0][0] == date[2][0])
            start = date[0][1] > date[2][1] ? 0 : 2;
        end = date[1][0] > date[3][0] ? 3 : 1;
        if(date[1][0] == date[3][0])
            end = date[1][1] > date[3][1] ? 3 : 1;
        //月份相同，直接返回差值
        if(date[start][0] == date[end][0])
            return date[end][1] - date[start][1] + 1;

        res += map[date[start][0]] - date[start][1] + 1;

        res += date[end][1];
        for (int i = date[start][0]+1; i < date[end][0]; i++) {
            res += map[i];
        }
        return res;
    }
}
