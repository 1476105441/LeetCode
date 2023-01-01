package review.其他;

public class No2 {
    /**
     *      汉明距离
     */

    public int hammingDistance(int x, int y) {
        int z = x^y,res = 0;
        for (int i = 0; i < 32; i++) {
            if ((z & 1) == 1) {
                res++;
            }
            z = z >> 1;
        }
        return res;
    }
}
