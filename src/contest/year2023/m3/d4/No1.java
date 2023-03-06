package contest.year2023.m3.d4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No1 {
    public int splitNum(int num) {
        int temp = num;
        List<Integer> list = new ArrayList<>();
        while(temp > 0){
            list.add(temp % 10);
            temp /= 10;
        }
        Collections.sort(list);
        int x = 0,y = 0;
        for(int i = 0;i < list.size();i++){
            if((i & 1) == 1){
                x = x * 10 + list.get(i);
            } else {
                y = y * 10 + list.get(i);
            }
        }
        return x + y;
    }
}
