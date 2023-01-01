package contest.year2022.m7.m7d10;

public class No3 {
    /**
     *      移动片段得到字符串
     */

    //失败，原本比赛的时候是可以通过测试用例的
    /*public boolean canChange(String start, String target) {
        char[] s = start.toCharArray(),t = target.toCharArray();
        List<Integer> sR = new ArrayList<>(),sL = new ArrayList<>(),tR = new ArrayList<>(),tL = new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'R') {
                sR.add(i);
            } else if (s[i] == 'L') {
                sL.add(i);
            }
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
    }*/

    //大神的解法，如果可以通过移动片段得到字符
    //串，那么把空格去掉后，两个字符串一定是相
    //同的
    public boolean canChange(String start, String target){
        StringBuilder s1 = new StringBuilder(),s2 = new StringBuilder();
        char[] s = start.toCharArray();
        char[] t = target.toCharArray();

        for (int i = 0; i < s.length; i++) {
            if (s[i] != '_') {
                s1.append(s[i]);
            }
            if (t[i] != '_') {
                s2.append(t[i]);
            }
        }
        if(!s1.toString().equals(s2.toString()))
            return false;

        int j = 0;
        for (int i = 0; i < s.length; i++) {
            if(s[i] == '_')
                continue;
            while(j < s.length && t[j] == '_')
                j++;
            if((s[i] == 'L' && i < j) || (s[i] == 'R' && i > j))
                return false;
            j++;
        }

        return true;
    }
}
