package review.其他;

public class No7 {
    /**
     *      两整数之和
     */

    //还是使用了加法
    /*public int getSum(int a, int b) {
        int x,y,c = 0,res = 0;
        for (int i = 0; i < 32; i++) {
            x = a&1;
            y = b&1;
            res = res|((x^y^c) << i);
            c = x&y | (x^y)&c;
            a = a >>>1;
            b = b >>>1;
        }
        return res;
    }*/

    public int getSum(int a,int b){
        int temp;
        while (b != 0) {
            temp = (a&b)<<1;
            a = a^b;
            b = temp;
        }
        return a;
    }
}
