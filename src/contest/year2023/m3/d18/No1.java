package contest.year2023.m3.d18;

import java.util.Arrays;

public class No1 {
    public int distMoney(int money, int children) {
        int res = 0,i;
        if(children > money) return -1;
        money -= children;
        for(i = 0;i < children;i++){
            if(money >= 7){
                money -= 7;
                res++;
            } else if(money == 3){
                if(i < children-1){
                    break;
                }
                money = 0;
                res--;
            } else {
                break;
            }
        }
        if(money > 0 && i == children) res--;
        return res;
    }
}
