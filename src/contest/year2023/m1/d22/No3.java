package contest.year2023.m1.d22;

import java.util.ArrayList;
import java.util.List;

public class No3 {
    //这个解法不行
    /*public boolean makeStringsEqual(String s, String target) {
        char[] sc = s.toCharArray(),tc = target.toCharArray();
        int n = sc.length,count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (sc[i] == '1') {
                count++;
            }
            if (sc[i] != tc[i]) {
                list.add(i);
            }
        }
        for (Integer i : list) {
            if (sc[i] == '1') {
                if (count == 1) {
                    return false;
                }
                count--;
            } else {
                if (count == 0) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }*/

    //通过
    public boolean makeStringsEqual(String s, String target) {
        char[] sc = s.toCharArray(), tc = target.toCharArray();
        int n = sc.length;
        boolean hasZero = false, hasOne = false, flag = false;
        for (int i = 0; i < n; i++) {
            if (sc[i] != tc[i]) {
                flag = true;
                if (sc[i] == '1') {
                    hasOne = true;
                    if (hasZero) {
                        return true;
                    }
                } else {
                    hasZero = true;
                    if (hasOne) {
                        return true;
                    }
                }
            } else if (sc[i] == '1') {
                return true;
            }
        }
        return !flag;
    }
}
