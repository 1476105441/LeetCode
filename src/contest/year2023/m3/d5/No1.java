package contest.year2023.m3.d5;

public class No1 {
    public int passThePillow(int n, int time) {
        int loc = 1,flag = 0;
        while(time > 0){
            if(flag == 0){
                if(loc == n){
                    flag = 1;
                    loc--;
                }else{
                    loc++;
                }
            } else {
                if(loc == 1){
                    flag = 0;
                    loc++;
                } else {
                    loc--;
                }
            }
            time--;
        }
        return loc;
    }
}
