package contest.year2022.oj.first;

import java.util.Scanner;

public class No1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] -= 32;
        }

        System.out.println(new String(chars));
    }
}
