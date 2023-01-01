package contest.year2022.oj.first;

import java.util.Scanner;

public class No5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(),b = scanner.nextInt(),c = scanner.nextInt(),d = scanner.nextInt();

        if (a < c) {
            System.out.println("fwhnb");
        } else if (a > c) {
            System.out.println("qlnb");
        } else{
            if (b > d) {
                System.out.println("qlnb");
            }else
                System.out.println("fwhnb");
        }
    }
}
