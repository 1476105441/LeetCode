package contest.year2022.m10.m10d23;

public class No1 {
    public boolean haveConflict(String[] event1, String[] event2) {
        boolean res = false;
        int s1,e1,s2,e2;
        char[][] temp1 = new char[2][],temp2 = new char[2][];

        for (int i = 0; i < 2; i++) {
            temp1[i] = event1[i].toCharArray();
            temp2[i] = event2[i].toCharArray();
        }
        s1 = count(temp1[0]);
        e1 = count(temp1[1]);
        s2 = count(temp2[0]);
        e2 = count(temp2[1]);

        if ((s1 <= s2 && e1 >= s2) || (s1 >= s2 && e2 >= s1)) {
            res = true;
        }

        return res;
    }
    public int count(char[] chars){
        int res = 0;
        if (chars[0] > '0') {
            res += (chars[0]-'0') * 10 * 60;
        }
        if (chars[1] > '0') {
            res += chars[1]-'0' * 60;
        }
        if (chars[3] > '0') {
            res += chars[3] - '0' * 10;
        }
        if (chars[4] > '0') {
            res += chars[4] - '0';
        }

        return res;
    }
}
