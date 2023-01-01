package contest.year2022.m10.m10d1;

public class No1 {
    /**
     * 删除字符使频率相同
     */

    public boolean equalFrequency(String word) {
        char[] chars = word.toCharArray();
        int[] set = new int[26];
        int min = Integer.MAX_VALUE,c = 1;

        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i]-'a';
            set[loc]++;
        }

        for (int i = 0; i < 26; i++) {
            if (set[i] > 0) {
                set[i]--;
                int pre=0;
                boolean flag = true;
                for (int j = 0; j < 26; j++) {
                    if (set[j] != 0) {
                        if (pre == 0) {
                            pre = set[j];
                        } else{
                            if (pre != set[j]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                if(flag)
                    return true;
                set[i]++;
            }
        }
        return false;
    }
}
