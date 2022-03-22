package oj周赛;

public class No4 {
    //爬楼梯
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);

        double n;
        int k;
        n = s.nextDouble();
        k = s.nextInt();

        double[] res = new double[k];
        double temp = 0;

        if (n == 1) {
            System.out.println(1);
            return;
        } else if (n == 2) {
            System.out.println(2);
            return;
        }

        res[0] = 1;
        res[1] = 1;
        int loc = 0,i;

        for (i = 2; i < k; i++) {
            res[i] = 0;
            for (int j = 0; j < i; j++) {
                res[i] += res[j];
            }
        }

        for (; i <= n; i++) {
            temp = 0;
            for (int j = 0; j < k; j++) {
                temp += res[j];
            }

            res[loc++] = temp;
            loc = loc % k;
        }

        int count = (int)(temp % 20211114);

        System.out.println(count);
    }
}
