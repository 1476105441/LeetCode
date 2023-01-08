package contest.year2023.m1.d7;

import java.util.LinkedList;
import java.util.List;

public class No2 {

}
class DataStream {
    int value,k,now;
    List<Integer> tmp;
    public DataStream(int value, int k) {
        this.k = k;
        this.value = value;
        now = 0;
        tmp = new LinkedList<>();
    }

    public boolean consec(int num) {
        tmp.add(num);
        if (num != value) {
            now = 0;
            return false;
        }
        now++;
        return now >= k;
    }
}
