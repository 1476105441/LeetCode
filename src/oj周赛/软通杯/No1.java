package oj周赛.软通杯;

import java.math.BigInteger;
import java.util.Scanner;

public class No1 {
    static int[][] food;
    static int survive,escape;
    static int height;
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        height = scanner.nextInt();
        int count = scanner.nextInt();
        food = new int[count][3];
        survive = 0;

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 3; j++) {
                food[i][j] = scanner.nextInt();
            }
        }

        if(find(10,0,0)){
            System.out.println(escape);
        }else{
            System.out.println(survive);
        }
    }

    public static boolean find(int preLife,int preHeight,int loc){
        if (loc == food.length || preLife < food[loc][0]) {
            if (preLife > survive) {
                survive = preLife;
            }
            return false;
        }
        boolean flag;
        int thisHeight = preHeight;

        //不吃
        thisHeight += food[loc][2];
        if (thisHeight >= height) {
            escape = food[loc][0];
            return true;
        }
        flag = find(preLife,thisHeight,loc+1);
        if (flag) {
            return true;
        }

        //吃
        return find(preLife+food[loc][1],preHeight,loc+1);
    }
}
