package contest.year2023.m5.d7;

import java.util.HashMap;
import java.util.Map;

/**
 * 2671.频率跟踪器
 *
 * @author wjs 2023/7/24
 */
public class No2 {
    class FrequencyTracker {

        Map<Integer,Integer> nc, cc;

        public FrequencyTracker() {
            nc = new HashMap<>();
            cc = new HashMap<>();
        }

        public void add(int number) {
            int count = nc.getOrDefault(number,0);
            if(count != 0) {
                int c = cc.getOrDefault(count,0);
                if(c != 0) {
                    cc.put(count,c-1);
                }
            }
            nc.put(number,count+1);
            cc.put(count+1,cc.getOrDefault(count+1,0)+1);
        }

        public void deleteOne(int number) {
            int count = nc.getOrDefault(number,0);
            if(count != 0) {
                int c = cc.getOrDefault(count,0);
                if(c != 0) {
                    cc.put(count,c-1);
                }
                nc.put(number,count-1);
            }
            if(count > 1) {
                cc.put(count-1,cc.getOrDefault(count-1,0)+1);
            }
        }

        public boolean hasFrequency(int frequency) {
            int c = cc.getOrDefault(frequency,0);
            return c != 0;
        }
    }
}
