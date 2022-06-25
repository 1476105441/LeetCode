package oj周赛.软通杯;

import java.math.BigInteger;

public class No5 {
    /*public static void main(String[] args) {
        java.util.Scanner scanner = new Scanner(System.in);
        String a , b;
        a = scanner.nextLine();
        b = scanner.nextLine();
        char[] aChar = a.toCharArray(),bChar = b.toCharArray();
        int[] temp;
        int i,flag = 0,A,B;

        if (aChar.length > bChar.length) {
            i = aChar.length-1;
        }else{
            i = bChar.length-1;
            flag = 1;
        }
        temp = new int[i+1];

        for (; i >= 0; i--) {
            if (flag == 0) {
                A = aChar[i] - '0';
                if (i > bChar.length) {
                    B = 0;
                }else{
                    B = bChar[i] - '0';
                }
            }else{
                B = bChar[i] - '0';
                if (i > aChar.length) {
                    A = 0;
                }else{
                    A = aChar[i] - '0';
                }
            }
            temp[i] = A-B;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < temp.length; j++) {
            if (j < temp.length-1 && temp[j] < 0) {
                temp[j] += 10;
                temp[j+1]--;
            }
        }
        for (int j = temp.length-1; j >= 0; j--) {
            sb.append(temp[j]);
        }

        System.out.println(sb);
    }*/

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        BigInteger a = new BigInteger(scanner.next()),b = new BigInteger(scanner.next());
        System.out.println(a.add(b.negate()));
    }
}
