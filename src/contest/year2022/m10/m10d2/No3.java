package contest.year2022.m10.m10d2;

public class No3 {
    /**
     *      最小 XOR
     */

    //暴力求解超时
    /*public int minimizeXor(int num1, int num2) {
        int res = 0,min = Integer.MAX_VALUE,n = Integer.bitCount(num2);
        for (int i = 0; i <= 1000000000; i++) {
            if (Integer.bitCount(i) == n) {
                int temp = i ^ num1;
                if (temp < min) {
                    min = temp;
                    res = i;
                }
            }
        }
        return res;
    }*/

    public int minimizeXor(int num1, int num2) {
        int res = 0,min = Integer.MAX_VALUE,n = Integer.bitCount(num2),m = 0;
        int[] bitMap = new int[32],resMap = new int[32];

        //统计num1中位为1的情况
        for (int i = 0; i < 32; i++) {
            if ((num1 >> i & 1) == 1) {
                m++;
                bitMap[i] = 1;
            }
        }

        for (int i = 31; i > -1 && n > 0; i--) {
            if (bitMap[i] == 1) {
                n--;
                resMap[i] = 1;
            }
        }

        for (int i = 0; i < 31; i++) {
            if (resMap[i] == 1) {
                res += (1 << i);
            } else if (n > 0) {
                n--;
                res += (1 << i);
            }
        }

        return res;
    }
}
