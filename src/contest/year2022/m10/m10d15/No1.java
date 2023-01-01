package contest.year2022.m10.m10d15;

public class No1 {
    public int countTime(String time) {
        char[] c = time.toCharArray();
        int n = c.length,res = 1;
        boolean a = c[0] == '?',b = c[1] == '?',d = c[3] == '?',e = c[4] == '?';

        if (a) {
            if (b) {
                res *= 24;
            }else if(c[1] >= '4'){
                res *= 2;
            }else{
                res *= 3;
            }
        } else if (b) {
            if (c[0] != '2') {
                res *= 10;
            }else
                res *= 4;
        }
        if (d) {
            if (e) {
                res *= 60;
            } else if (c[4] != '0') {
                res *= 6;
            }else{
                res *= 7;
            }
        } else if (e) {
            if (c[3] != '6') {
                res *= 10;
            }
        }

        return res;
    }
}
