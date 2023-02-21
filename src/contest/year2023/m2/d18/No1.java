package contest.year2023.m2.d18;

import java.util.ArrayList;
import java.util.List;

public class No1 {
    public int minMaxDifference(int num) {
        List<Integer> list = new ArrayList<>();
        int temp = num;
        while (temp > 0) {
            list.add(temp % 10);
            temp /= 10;
        }
        int max = 0,min = 0,maxReplace = -1,minReplace = -1;
        for (int i = list.size()-1; i > -1; i--) {
            if (maxReplace == -1 && list.get(i) < 9) {
                maxReplace = list.get(i);
                max = max * 10 + 9;
            } else if (list.get(i) == maxReplace) {
                max = max * 10 + 9;
            } else {
                max = max * 10 + list.get(i);
            }
            if (minReplace == -1) {
                minReplace = list.get(i);
            } else if (minReplace == list.get(i)) {
                min = min * 10;
            } else {
                min = min * 10 + list.get(i);
            }
        }
        return max - min;
    }
}
