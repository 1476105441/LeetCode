package oj周赛.第二周;

import java.util.Scanner;

public class No4 {
    public static void main(String[] args) {

        double sum = 0,i,j;
        for (i = 1; i <= 123456;i++) {
            for (j = 1; j <= 123456; j++) {
                sum += j;
            }
        }

        sum = 940827768293376L;
    }
}
