package 周赛.m7d10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No3 {
    /**
     *      移动片段得到字符串
     */

    public boolean canChange(String start, String target) {
        char[] s = start.toCharArray(),t = target.toCharArray();
        List<Integer> sR = new ArrayList<>(),sL = new ArrayList<>(),tR = new ArrayList<>(),tL = new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'R') {
                sR.add(i);
            } else if (s[i] == 'L') {
                sL.add(i);
            }
        }
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 'R') {
                tR.add(i);
            } else if (t[i] == 'L') {
                tL.add(i);
            }
        }

        if (sR.size() != tR.size() || sL.size() != tL.size()) {
            return false;
        }
        for (int i = sR.size()-1; i > -1 ; i--) {
            if (sR.get(i) > tR.get(i)) {
                return false;
            }
            for (int j = sR.get(i)+1; j <= tR.get(i); j++) {
                if (s[j] == 'L') {
                    return false;
                }
            }
        }
        for (int i = 0; i < sL.size() ; i++) {
            if (sL.get(i) < tL.get(i)) {
                return false;
            }
        }
        return true;
    }
}
