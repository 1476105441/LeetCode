package contest.year2022.m7.m7d23;

import java.util.HashMap;
import java.util.Map;

public class No1 {
    /**
     *      最好的扑克手牌
     */

    public String bestHand(int[] ranks, char[] suits) {
        Map<Integer,Integer> map = new HashMap<>();
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if(suits[i] == suits[i-1])
                count++;
        }
        if (count == 5) {
            return "Flush";
        }
        int max = 0;
        for (int i = 0; i < 5; i++) {
            int temp = map.getOrDefault(ranks[i],0)+1;
            map.put(ranks[i],temp);
            if (temp > max) {
                max = temp;
            }
        }
        if(max >= 3)
            return "Three of a Kind";
        else if(max == 2)
            return "Pair";
        else
            return "High Card";
    }
}
