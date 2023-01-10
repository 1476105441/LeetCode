package contest.year2023.m1.d8;

import java.util.HashSet;
import java.util.Set;

public class No3 {
    /*public boolean isItPossible(String word1, String word2) {
        char[] c1 = word1.toCharArray(),c2 = word2.toCharArray();
        int n1 = c1.length,n2 = c2.length;
        int[] count1,count2;
        count1 = new int[26];
        count2 = new int[26];
        for (int i = 0; i < n1; i++) {
            count1[c1[i] - 'a']++;
        }
        for (int i = 0; i < n2; i++) {
            count2[c2[i] - 'a']++;
        }
        int dif1 = 0,dif2 = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0) {
                dif1++;
            }
            if (count2[i] > 0) {
                dif2++;
            }
        }
        int abs = Math.abs(dif1 - dif2);
        if (abs == 0) {
            return n1 > 1 && n2 > 1;
        }
        if (abs > 2) {
            return false;
        }
        boolean flag = dif1 > dif2;
        boolean flag1 = false,flag2 = false;
        if (abs == 2) {
            if (flag) {
                for (int i = 0; i < 26; i++) {
                    if (count1[i] == 1 && count2[i] == 0) {
                        flag1 = true;
                    }
                    if (count2[i] > 1 && count1[i] > 1) {
                        flag2 = true;
                    }
                }
            } else {
                for (int i = 0; i < 26; i++) {
                    if (count2[i] == 1 && count1[i] == 0) {
                        flag1 = true;
                    }
                    if (count1[i] > 1 && count2[i] > 1) {
                        flag2 = true;
                    }
                }
            }
            return flag1 && flag2;
        }

        return flag ? abs1(count1,count2) : abs1(count2,count1);
    }
    public boolean abs1(int[] count1,int[] count2){
        Set<Integer> set = new HashSet<>();
        boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false,inc1 = false,inc2 = false;
        for (int i = 0; i < 26; i++) {
            if (count1[i] == 1) {
                if (count2[i] > 0) {
                    flag1 = true;
                    set.add(i);
                } else {
                    //我有一个，你没有
                    flag3 = true;
                }
            }
            //让另外一个数组加一
            if (count1[i] > 1 && count2[i] == 0) {
                inc1 = true;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (count2[i] > 1 && count1[i] > 0 && (set.size() > 1 || !set.contains(i))) {
                flag2 = true;
                break;
            }
            //我有一个，你也有
            if (count1[i] > 0) {
                if (count2[i] == 1) {
                    flag4 = true;
                } else if (count2[i] > 1) {
                    inc2 = true;
                }
            }
        }
        return (flag1 && flag2) || (flag3 && flag4) || (inc1 && inc2);
    }*/

    public boolean isItPossible(String word1, String word2) {
        char[] c1 = word1.toCharArray(),c2 = word2.toCharArray();
        int n1 = c1.length,n2 = c2.length;
        int[] count1,count2;
        count1 = new int[26];
        count2 = new int[26];
        for (int i = 0; i < n1; i++) {
            count1[c1[i] - 'a']++;
        }
        for (int i = 0; i < n2; i++) {
            count2[c2[i] - 'a']++;
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (!(count1[i] > 0 && count2[i] > 0)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return true;
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (!(count1[i] > 0 && count2[i] > 0)) {
                    continue;
                }
                count1[i]--;
                count2[i]++;
                count2[j]--;
                count1[j]++;
                int sum1 = 0,sum2 = 0;
                for (int k = 0; k < 26; k++) {
                    if (count1[k] > 0) {
                        sum1++;
                    }
                    if (count2[k] > 0) {
                        sum2++;
                    }
                }
                if (sum1 == sum2) {
                    return true;
                }
                count1[i]++;
                count2[i]--;
                count2[j]++;
                count1[j]--;
            }
        }
        return false;
    }
}
