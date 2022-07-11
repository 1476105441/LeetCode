package 复习.中级算法复习.其他;

public class No1 {
    //          两整数之和

    //不使用加减法，计算两数之和
    public int getSum(int a, int b) {
        int tempA,tempB,s,c = 0,count = -2147483648,res = 0;

        while (count != 0) {
            tempA = a & 1;
            tempB = b & 1;

            s = (tempA ^ tempB ^ c);
            c = (tempA & tempB) | ((tempA ^ tempB) & c);
            res = (res >>> 1) | (s << 31);

            count = count >>> 1;
            a = a >> 1;
            b = b >> 1;
        }

        return res;
    }

}
